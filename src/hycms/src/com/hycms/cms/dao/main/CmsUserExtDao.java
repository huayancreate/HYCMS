package com.hycms.cms.dao.main;

import com.hycms.cms.entity.main.CmsUserExt;
import com.hycms.common.hibernate3.Updater;

public interface CmsUserExtDao {
	public CmsUserExt findById(Integer id);

	public CmsUserExt save(CmsUserExt bean);

	public CmsUserExt updateByUpdater(Updater<CmsUserExt> updater);
}