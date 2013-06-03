package com.hycms.cms.manager.main;

import com.hycms.cms.entity.main.Content;
import com.hycms.cms.entity.main.ContentCheck;

/**
 * 内容审核Manager接口
 * 
 * '内容'数据存在，则'内容审核'数据必须存在。
 * 
 * @author 
 * 
 */
public interface ContentCheckMng {
	public ContentCheck save(ContentCheck check, Content content);

	public ContentCheck update(ContentCheck bean);
}