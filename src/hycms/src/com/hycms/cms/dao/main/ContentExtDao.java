package com.hycms.cms.dao.main;

import com.hycms.cms.entity.main.ContentExt;
import com.hycms.common.hibernate3.Updater;

public interface ContentExtDao {
	public ContentExt findById(Integer id);

	public ContentExt save(ContentExt bean);

	public ContentExt updateByUpdater(Updater<ContentExt> updater);
}