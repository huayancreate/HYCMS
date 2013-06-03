package com.hycms.core.manager.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hycms.common.page.Pagination;
import com.hycms.common.security.BadCredentialsException;
import com.hycms.common.security.UsernameNotFoundException;
import com.hycms.common.web.session.SessionProvider;
import com.hycms.core.dao.AuthenticationDao;
import com.hycms.core.entity.Authentication;
import com.hycms.core.entity.UnifiedUser;
import com.hycms.core.manager.AuthenticationMng;
import com.hycms.core.manager.UnifiedUserMng;

@Service
@Transactional
public class AuthenticationMngImpl implements AuthenticationMng {
	private Logger log = LoggerFactory.getLogger(AuthenticationMngImpl.class);

	public Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) throws UsernameNotFoundException,
			BadCredentialsException {
		UnifiedUser user = unifiedUserMng.login(username, password, ip);
		Authentication auth = new Authentication();
		auth.setUid(user.getId());
		auth.setUsername(user.getUsername());
		auth.setEmail(user.getEmail());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getId());
		return auth;
	}
	
	public Authentication activeLogin(UnifiedUser user, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) {
		unifiedUserMng.activeLogin(user, ip);
		Authentication auth = new Authentication();
		auth.setUid(user.getId());
		auth.setUsername(user.getUsername());
		auth.setEmail(user.getEmail());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getId());
		return auth;
	}

	public Authentication retrieve(String authId) {
		long current = System.currentTimeMillis();
		// 鏄惁鍒锋柊鏁版嵁搴�
		if (refreshTime < current) {
			refreshTime = getNextRefreshTime(current, interval);
			int count = dao.deleteExpire(new Date(current - timeout));
			log.info("refresh Authentication, delete count: {}", count);
		}
		Authentication auth = findById(authId);
		if (auth != null && auth.getUpdateTime().getTime() + timeout > current) {
			auth.setUpdateTime(new Timestamp(current));
			return auth;
		} else {
			return null;
		}
	}

	public Integer retrieveUserIdFromSession(SessionProvider session,
			HttpServletRequest request) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId == null) {
			return null;
		}
		Authentication auth = retrieve(authId);
		if (auth == null) {
			return null;
		}
		return auth.getUid();
	}

	public void storeAuthIdToSession(SessionProvider session,
			HttpServletRequest request, HttpServletResponse response,
			String authId) {
		session.setAttribute(request, response, AUTH_KEY, authId);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Authentication findById(String id) {
		Authentication entity = dao.findById(id);
		return entity;
	}

	public Authentication save(Authentication bean) {
		bean.setId(StringUtils.remove(UUID.randomUUID().toString(), '-'));
		bean.init();
		dao.save(bean);
		return bean;
	}

	public Authentication deleteById(String id) {
		Authentication bean = dao.deleteById(id);
		return bean;
	}

	public Authentication[] deleteByIds(String[] ids) {
		Authentication[] beans = new Authentication[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	// 杩囨湡鏃堕棿
	private int timeout = 30 * 60 * 1000; // 30鍒嗛挓

	// 闂撮殧鏃堕棿
	private int interval = 4 * 60 * 60 * 1000; // 4灏忔椂

	// 鍒锋柊鏃堕棿銆�
	private long refreshTime = getNextRefreshTime(System.currentTimeMillis(),
			this.interval);

	private UnifiedUserMng unifiedUserMng;
	private AuthenticationDao dao;

	@Autowired
	public void setDao(AuthenticationDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setUserMng(UnifiedUserMng unifiedUserMng) {
		this.unifiedUserMng = unifiedUserMng;
	}

	/**
	 * 璁剧疆璁よ瘉杩囨湡鏃堕棿銆傞粯璁�0鍒嗛挓銆�
	 * 
	 * @param timeout
	 *            鍗曚綅鍒嗛挓
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout * 60 * 1000;
	}

	/**
	 * 璁剧疆鍒锋柊鏁版嵁搴撴椂闂淬�榛樿4灏忔椂銆�
	 * 
	 * @param interval
	 *            鍗曚綅鍒嗛挓
	 */
	public void setInterval(int interval) {
		this.interval = interval * 60 * 1000;
		this.refreshTime = getNextRefreshTime(System.currentTimeMillis(),
				this.interval);
	}

	/**
	 * 鑾峰緱涓嬩竴涓埛鏂版椂闂淬�
	 * 
	 * 
	 * 
	 * @param current
	 * @param interval
	 * @return 闅忔満闂撮殧鏃堕棿
	 */
	private long getNextRefreshTime(long current, int interval) {
		return current + interval;
		// 涓轰簡闃叉澶氫釜搴旂敤鍚屾椂鍒锋柊锛岄棿闅旀椂闂�interval+RandomUtils.nextInt(interval/4);
		// return current + interval + RandomUtils.nextInt(interval / 4);
	}
}