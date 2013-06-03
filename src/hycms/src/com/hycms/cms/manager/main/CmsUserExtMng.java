package com.hycms.cms.manager.main;

import com.hycms.cms.entity.main.CmsUser;
import com.hycms.cms.entity.main.CmsUserExt;

public interface CmsUserExtMng {
	public CmsUserExt save(CmsUserExt ext, CmsUser user);

	public CmsUserExt update(CmsUserExt ext, CmsUser user);
}