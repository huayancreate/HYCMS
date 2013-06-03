package com.hycms.cms.dao.assist;

import com.hycms.cms.entity.assist.CmsSiteFlow;

public interface CmsSiteFlowDao {
	public CmsSiteFlow save(CmsSiteFlow cmsSiteFlow);

	public CmsSiteFlow findUniqueByProperties(Integer siteId, String accessDate,
			String sessionId, String page);
}
