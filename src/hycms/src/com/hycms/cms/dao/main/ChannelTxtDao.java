package com.hycms.cms.dao.main;

import com.hycms.cms.entity.main.ChannelTxt;
import com.hycms.common.hibernate3.Updater;

public interface ChannelTxtDao {
	public ChannelTxt findById(Integer id);

	public ChannelTxt save(ChannelTxt bean);

	public ChannelTxt updateByUpdater(Updater<ChannelTxt> updater);
}