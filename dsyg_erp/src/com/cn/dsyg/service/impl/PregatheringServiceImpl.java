package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.PregatheringDao;
import com.cn.dsyg.dto.PregatheringDto;
import com.cn.dsyg.service.PregatheringService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class PregatheringServiceImpl implements PregatheringService {
	
	private PregatheringDao pregatheringDao;

	@Override
	public PregatheringDto queryAllEtbPregatheringByID(String ID) {
		return pregatheringDao.queryAllEtbPregatheringByID(ID);
	}

	@Override
	public Page queryEtbPregatheringByPage(Page page, String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName) {
		pregatheringNoLow = StringUtil.replaceDatabaseKeyword_mysql(pregatheringNoLow);
		//查询总记录数
		int totalCount = pregatheringDao.queryEtbPregatheringCountByPage(pregatheringNoLow, pregatheringNoHigh, pregatheringName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PregatheringDto> list = pregatheringDao.queryEtbPregatheringByPage(pregatheringNoLow, pregatheringNoHigh,
				pregatheringName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public PregatheringDto queryEtbPregatheringByID(String pregatheringNo) {
		return pregatheringDao.queryEtbPregatheringByID(pregatheringNo);
	}

	@Override
	public List<PregatheringDto> queryAllEtbPregathering() {
		return pregatheringDao.queryAllEtbPregathering();
	}

	@Override
	public String insertEtbPregathering(PregatheringDto pregathering) {
		String pregatheringno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		pregathering.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		pregatheringno = Constants.PREGEATHERING_NO_PRE + belongto + sdf.format(date) + uuid;
		pregathering.setPregatheringno(pregatheringno);
		
		pregatheringDao.insertEtbPregathering(pregathering);
		return pregatheringno;
	}

	@Override
	public void updateEtbPregathering(PregatheringDto pregathering) {
		pregatheringDao.updateEtbPregathering(pregathering);
	}

	@Override
	public void deleteEtbPregathering(String pregatheringNo, String username) {
		PregatheringDto pregathering = pregatheringDao.queryEtbPregatheringByID(pregatheringNo);
		if(pregathering != null) {
			//状态=已删除
			pregathering.setStatus(Constants.STATUS_DEL);
			pregathering.setUpdateuid(username);
			pregatheringDao.updateEtbPregathering(pregathering);
		}
	}

	@Override
	public List<PregatheringDto> queryAllEtbPregatheringExport(String pregatheringNoLow,
			String pregatheringNoHigh) {
		return pregatheringDao.queryAllEtbPregatheringExport(pregatheringNoLow, pregatheringNoHigh);
	}

	public PregatheringDao getPregatheringDao() {
		return pregatheringDao;
	}

	public void setPregatheringDao(PregatheringDao pregatheringDao) {
		this.pregatheringDao = pregatheringDao;
	}
}
