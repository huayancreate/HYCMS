package com.hycms.cms.dao.assist;

import java.util.List;

import com.hycms.cms.entity.assist.CmsAcquisitionHistory;
import com.hycms.common.hibernate3.Updater;
import com.hycms.common.page.Pagination;

public interface CmsAcquisitionHistoryDao {
	public List<CmsAcquisitionHistory> getList(Integer siteId,Integer acquId);

	public Pagination getPage(Integer siteId,Integer acquId, Integer pageNo, Integer pageSize);

	public CmsAcquisitionHistory findById(Integer id);

	public CmsAcquisitionHistory save(CmsAcquisitionHistory bean);

	public CmsAcquisitionHistory updateByUpdater(Updater<CmsAcquisitionHistory> updater);

	public CmsAcquisitionHistory deleteById(Integer id);

	public Boolean checkExistByProperties(Boolean title, String value);
}