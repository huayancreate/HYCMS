package com.hycms.cms.manager.assist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hycms.cms.dao.assist.CmsDataDao;
import com.hycms.cms.entity.back.CmsConstraints;
import com.hycms.cms.entity.back.CmsField;
import com.hycms.cms.entity.back.CmsTable;
import com.hycms.cms.manager.assist.CmsDataMng;

@Service
@Transactional
public class CmsDataMngImpl implements CmsDataMng {

	@Transactional(readOnly = true)
	public List<CmsTable> listTabels() {
		return dao.listTables();
	}

	@Transactional(readOnly = true)
	public CmsTable findTable(String tablename) {
		return dao.findTable(tablename);
	}

	@Transactional(readOnly = true)
	public List<CmsField> listFields(String tablename) {
		return dao.listFields(tablename);
	}

	@Transactional(readOnly = true)
	public List<CmsConstraints> listConstraints(String tablename) {
		return dao.listConstraints(tablename);
	}


	private CmsDataDao dao;

	@Autowired
	public void setDao(CmsDataDao dao) {
		this.dao = dao;
	}


}