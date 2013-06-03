package com.hycms.cms.dao.assist;

import com.hycms.cms.entity.assist.CmsVoteItem;
import com.hycms.common.hibernate3.Updater;
import com.hycms.common.page.Pagination;

public interface CmsVoteItemDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsVoteItem findById(Integer id);

	public CmsVoteItem save(CmsVoteItem bean);

	public CmsVoteItem updateByUpdater(Updater<CmsVoteItem> updater);

	public CmsVoteItem deleteById(Integer id);
}