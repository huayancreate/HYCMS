package com.hycms.cms.dao.main;

import com.hycms.cms.entity.main.ContentTxt;
import com.hycms.common.hibernate3.Updater;

public interface ContentTxtDao {
	public ContentTxt findById(Integer id);

	public ContentTxt save(ContentTxt bean);

	public ContentTxt updateByUpdater(Updater<ContentTxt> updater);
}