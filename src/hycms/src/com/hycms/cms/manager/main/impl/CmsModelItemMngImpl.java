package com.hycms.cms.manager.main.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hycms.cms.dao.main.CmsModelItemDao;
import com.hycms.cms.entity.main.CmsModel;
import com.hycms.cms.entity.main.CmsModelItem;
import com.hycms.cms.manager.main.CmsModelItemMng;
import com.hycms.cms.manager.main.CmsModelMng;
import com.hycms.common.hibernate3.Updater;

@Service
@Transactional
public class CmsModelItemMngImpl implements CmsModelItemMng {
	@Transactional(readOnly = true)
	public List<CmsModelItem> getList(Integer modelId, boolean isChannel,
			boolean hasDisabled) {
		return dao.getList(modelId, isChannel, hasDisabled);
	}

	@Transactional(readOnly = true)
	public CmsModelItem findById(Integer id) {
		CmsModelItem entity = dao.findById(id);
		return entity;
	}

	public CmsModelItem save(CmsModelItem bean, Integer modelId) {
		bean.setModel(cmsModelMng.findById(modelId));
		return save(bean);
	}

	public CmsModelItem save(CmsModelItem bean) {
		bean.init();
		dao.save(bean);
		return bean;
	}

	public void saveList(List<CmsModelItem> list) {
		for (CmsModelItem item : list) {
			save(item);
		}
	}

	public void updatePriority(Integer[] wids, Integer[] priority,
			String[] label, Boolean[] single, Boolean[] display) {
		CmsModelItem item;
		for (int i = 0, len = wids.length; i < len; i++) {
			item = findById(wids[i]);
			item.setLabel(label[i]);
			item.setPriority(priority[i]);
			item.setSingle(single[i]);
			item.setDisplay(display[i]);
		}
	}

	public CmsModelItem update(CmsModelItem bean) {
		Updater<CmsModelItem> updater = new Updater<CmsModelItem>(bean);
		CmsModelItem entity = dao.updateByUpdater(updater);
		entity.emptyToNull();
		return entity;
	}

	public CmsModelItem deleteById(Integer id) {
		CmsModelItem bean = dao.deleteById(id);
		return bean;
	}

	public CmsModelItem[] deleteByIds(Integer[] ids) {
		CmsModelItem[] beans = new CmsModelItem[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsModelMng cmsModelMng;
	private CmsModelItemDao dao;

	@Autowired
	public void setCmsModelMng(CmsModelMng cmsModelMng) {
		this.cmsModelMng = cmsModelMng;
	}

	@Autowired
	public void setDao(CmsModelItemDao dao) {
		this.dao = dao;
	}

	@Override
	public void initializeList(CmsModel model) {
		// TODO Auto-generated method stub
		List<CmsModelItem> list = new ArrayList<CmsModelItem>();
		// 栏目名称
		CmsModelItem name = new CmsModelItem();
		name.setCustom(false);
		name.setModel(model);
		name.setChannel(true);
		name.setField("name");
		name.setLabel("栏目名称");
		name.setPriority(10);
		name.setDataType(1);
		name.setSingle(false);
		name.setDisplay(true);
		list.add(name);
		
		// 访问路径
		CmsModelItem path = new CmsModelItem();
		path.setCustom(false);
		path.setModel(model);
		path.setChannel(true);
		path.setField("path");
		path.setLabel("访问路径");
		path.setPriority(10);
		path.setDataType(2);
		path.setSingle(false);
		path.setDisplay(true);
		list.add(path);
		
		// meta标题
		CmsModelItem title = new CmsModelItem();
		title.setCustom(false);
		title.setModel(model);
		title.setChannel(true);
		title.setField("title");
		title.setLabel("meta标题");
		title.setPriority(10);
		title.setDataType(1);
		title.setSingle(false);
		title.setDisplay(true);
		list.add(title);
		
		// meta关键字
		CmsModelItem keywords = new CmsModelItem();
		keywords.setCustom(false);
		keywords.setModel(model);
		keywords.setChannel(true);
		keywords.setField("keywords");
		keywords.setLabel("meta关键字");
		keywords.setPriority(10);
		keywords.setDataType(1);
		keywords.setSingle(false);
		keywords.setDisplay(true);
		list.add(keywords);
		
		// meta描述
		CmsModelItem description = new CmsModelItem();
		description.setCustom(false);
		description.setModel(model);
		description.setChannel(true);
		description.setField("description");
		description.setLabel("meta描述");
		description.setPriority(10);
		description.setDataType(4);
		description.setSingle(true);
		description.setDisplay(true);
		list.add(description);
		
		// 栏目模板
		CmsModelItem tplChannel = new CmsModelItem();
		tplChannel.setCustom(false);
		tplChannel.setModel(model);
		tplChannel.setChannel(true);
		tplChannel.setField("tplChannel");
		tplChannel.setLabel("栏目模板");
		tplChannel.setPriority(10);
		tplChannel.setDataType(6);
		tplChannel.setSingle(false);
		tplChannel.setDisplay(true);
		list.add(tplChannel);
		
		// 内容模板
		CmsModelItem tplContent = new CmsModelItem();
		tplContent.setCustom(false);
		tplContent.setModel(model);
		tplContent.setChannel(true);
		tplContent.setField("tplContent");
		tplContent.setLabel("内容模板");
		tplContent.setPriority(10);
		tplContent.setDataType(6);
		tplContent.setSingle(false);
		tplContent.setDisplay(true);
		list.add(tplContent);
		
		// 栏目静态化
		CmsModelItem channelStatic = new CmsModelItem();
		channelStatic.setCustom(false);
		channelStatic.setModel(model);
		channelStatic.setChannel(true);
		channelStatic.setField("channelStatic");
		channelStatic.setLabel("栏目静态化");
		channelStatic.setPriority(10);
		channelStatic.setDataType(4);
		channelStatic.setSingle(true);
		channelStatic.setDisplay(true);
		list.add(channelStatic);
		
		// 内容静态化
		CmsModelItem contentStatic = new CmsModelItem();
		contentStatic.setCustom(false);
		contentStatic.setModel(model);
		contentStatic.setChannel(true);
		contentStatic.setField("contentStatic");
		contentStatic.setLabel("内容静态化");
		contentStatic.setPriority(10);
		contentStatic.setDataType(4);
		contentStatic.setSingle(true);
		contentStatic.setDisplay(true);
		list.add(contentStatic);
		
		// 排列顺序
		CmsModelItem priority = new CmsModelItem();
		priority.setCustom(false);
		priority.setModel(model);
		priority.setChannel(true);
		priority.setField("priority");
		priority.setLabel("排列顺序");
		priority.setPriority(10);
		priority.setDataType(2);
		priority.setSingle(false);
		priority.setDisplay(true);
		list.add(priority);
		
		// 显示
		CmsModelItem display = new CmsModelItem();
		display.setCustom(false);
		display.setModel(model);
		display.setChannel(true);
		display.setField("display");
		display.setLabel("显示");
		display.setPriority(10);
		display.setDataType(8);
		display.setSingle(false);
		display.setDisplay(true);
		list.add(display);
		
		// 文档图片
		CmsModelItem docImg = new CmsModelItem();
		docImg.setCustom(false);
		docImg.setModel(model);
		docImg.setChannel(true);
		docImg.setField("docImg");
		docImg.setLabel("文档图片");
		docImg.setPriority(10);
		docImg.setDataType(8);
		docImg.setSingle(true);
		docImg.setDisplay(true);
		list.add(docImg);
		
		// 终审级别
		CmsModelItem finalStep = new CmsModelItem();
		finalStep.setCustom(false);
		finalStep.setModel(model);
		finalStep.setChannel(true);
		finalStep.setField("finalStep");
		finalStep.setLabel("终审级别");
		finalStep.setPriority(10);
		finalStep.setDataType(2);
		finalStep.setSingle(false);
		finalStep.setDisplay(true);
		list.add(finalStep);
		
		// 审核后
		CmsModelItem afterCheck = new CmsModelItem();
		afterCheck.setCustom(false);
		afterCheck.setModel(model);
		afterCheck.setChannel(true);
		afterCheck.setField("afterCheck");
		afterCheck.setLabel("审核后");
		afterCheck.setPriority(10);
		afterCheck.setDataType(6);
		afterCheck.setSingle(false);
		afterCheck.setDisplay(true);
		list.add(afterCheck);
		
		// 浏览权限
		CmsModelItem viewGroupIds = new CmsModelItem();
		viewGroupIds.setCustom(false);
		viewGroupIds.setModel(model);
		viewGroupIds.setChannel(true);
		viewGroupIds.setField("viewGroupIds");
		viewGroupIds.setLabel("浏览权限");
		viewGroupIds.setPriority(10);
		viewGroupIds.setDataType(7);
		viewGroupIds.setSingle(false);
		viewGroupIds.setDisplay(true);
		list.add(viewGroupIds);
		
		// 投稿权限
		CmsModelItem contriGroupIds = new CmsModelItem();
		contriGroupIds.setCustom(false);
		contriGroupIds.setModel(model);
		contriGroupIds.setChannel(true);
		contriGroupIds.setField("contriGroupIds");
		contriGroupIds.setLabel("投稿权限");
		contriGroupIds.setPriority(10);
		contriGroupIds.setDataType(7);
		contriGroupIds.setSingle(false);
		contriGroupIds.setDisplay(true);
		list.add(contriGroupIds);
		
		// 管理权限
		CmsModelItem userIds = new CmsModelItem();
		userIds.setCustom(false);
		userIds.setModel(model);
		userIds.setChannel(true);
		userIds.setField("userIds");
		userIds.setLabel("管理权限");
		userIds.setPriority(10);
		userIds.setDataType(7);
		userIds.setSingle(true);
		userIds.setDisplay(true);
		list.add(userIds);
		
		// 外部链接
		CmsModelItem link = new CmsModelItem();
		link.setCustom(false);
		link.setModel(model);
		link.setChannel(true);
		link.setField("link");
		link.setLabel("外部链接");
		link.setPriority(10);
		link.setDataType(1);
		link.setSingle(true);
		link.setDisplay(true);
		list.add(link);
		
		// 标题图
		CmsModelItem titleImg = new CmsModelItem();
		titleImg.setCustom(false);
		titleImg.setModel(model);
		titleImg.setChannel(true);
		titleImg.setField("titleImg");
		titleImg.setLabel("标题图");
		titleImg.setPriority(10);
		titleImg.setDataType(1);
		titleImg.setSingle(true);
		titleImg.setDisplay(true);
		list.add(titleImg);
		
		// 内容图
		CmsModelItem contentImg = new CmsModelItem();
		contentImg.setCustom(false);
		contentImg.setModel(model);
		contentImg.setChannel(true);
		contentImg.setField("contentImg");
		contentImg.setLabel("内容图");
		contentImg.setPriority(10);
		contentImg.setDataType(1);
		contentImg.setSingle(true);
		contentImg.setDisplay(true);
		list.add(contentImg);
		
		// 内容
		CmsModelItem txt = new CmsModelItem();
		txt.setCustom(false);
		txt.setModel(model);
		txt.setChannel(true);
		txt.setField("txt");
		txt.setLabel("内容");
		txt.setPriority(10);
		txt.setDataType(4);
		txt.setSingle(true);
		txt.setDisplay(true);
		list.add(txt);
		
		saveList(list);
	}
}