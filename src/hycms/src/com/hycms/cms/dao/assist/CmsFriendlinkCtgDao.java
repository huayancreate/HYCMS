package com.hycms.cms.dao.assist;

import java.util.List;

import com.hycms.cms.entity.assist.CmsFriendlinkCtg;
import com.hycms.common.hibernate3.Updater;

public interface CmsFriendlinkCtgDao {
	public List<CmsFriendlinkCtg> getList(Integer siteId);

	public int countBySiteId(Integer siteId);

	public CmsFriendlinkCtg findById(Integer id);

	public CmsFriendlinkCtg save(CmsFriendlinkCtg bean);

	public CmsFriendlinkCtg updateByUpdater(Updater<CmsFriendlinkCtg> updater);

	public CmsFriendlinkCtg deleteById(Integer id);
}