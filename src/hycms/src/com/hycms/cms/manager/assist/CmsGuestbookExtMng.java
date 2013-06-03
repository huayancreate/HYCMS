package com.hycms.cms.manager.assist;

import com.hycms.cms.entity.assist.CmsGuestbook;
import com.hycms.cms.entity.assist.CmsGuestbookExt;

public interface CmsGuestbookExtMng {
	public CmsGuestbookExt save(CmsGuestbookExt ext, CmsGuestbook guestbook);

	public CmsGuestbookExt update(CmsGuestbookExt ext);
}