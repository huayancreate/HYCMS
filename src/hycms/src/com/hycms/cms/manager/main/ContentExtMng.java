package com.hycms.cms.manager.main;

import com.hycms.cms.entity.main.Content;
import com.hycms.cms.entity.main.ContentExt;

public interface ContentExtMng {
	public ContentExt save(ContentExt ext, Content content);

	public ContentExt update(ContentExt ext);
}