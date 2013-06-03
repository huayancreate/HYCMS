package com.hycms.cms.dao.main;

import com.hycms.cms.entity.main.CmsConfig;
import com.hycms.common.hibernate3.Updater;

public interface CmsConfigDao {
	public CmsConfig findById(Integer id);

	public CmsConfig updateByUpdater(Updater<CmsConfig> updater);
}