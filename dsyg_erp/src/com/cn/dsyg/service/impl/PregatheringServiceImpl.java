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
	public PregatheringDto queryAllPregatheringByID(String ID) {
		return pregatheringDao.queryAllPregatheringByID(ID);
	}

	@Override
	public Page queryPregatheringByPage(Page page, String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName, String customerName) {
		pregatheringNoLow = StringUtil.replaceDatabaseKeyword_mysql(pregatheringNoLow);
		//查询总记录数
		int totalCount = pregatheringDao.queryPregatheringCountByPage(pregatheringNoLow, pregatheringNoHigh, pregatheringName, customerName);
		System.out.println("totalcount:" + String.valueOf(totalCount));
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PregatheringDto> list = pregatheringDao.queryPregatheringByPage(pregatheringNoLow, pregatheringNoHigh,
				pregatheringName, customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public PregatheringDto queryPregatheringByID(String pregatheringNo) {
		return pregatheringDao.queryPregatheringByID(pregatheringNo);
	}

	@Override
	public List<PregatheringDto> queryAllPregathering() {
		return pregatheringDao.queryAllPregathering();
	}

	@Override
	public String insertPregathering(PregatheringDto pregathering) {
		String pregatheringno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		pregathering.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		pregatheringno = Constants.PREGEATHERING_NO_PRE + belongto + sdf.format(date) + uuid;
		pregathering.setPregatheringno(pregatheringno);
		
		pregatheringDao.insertPregathering(pregathering);
		return pregatheringno;
	}

	@Override
	public void updatePregathering(PregatheringDto pregathering) {
		pregatheringDao.updatePregathering(pregathering);
	}

	@Override
	public void deletePregathering(String pregatheringNo, String username) {
		PregatheringDto pregathering = pregatheringDao.queryPregatheringByID(pregatheringNo);
		if(pregathering != null) {
			//状态=已删除
			pregathering.setStatus(Constants.STATUS_DEL);
			pregathering.setUpdateuid(username);
			pregatheringDao.updatePregathering(pregathering);
		}
	}

	@Override
	public List<PregatheringDto> queryAllPregatheringExport(String pregatheringNoLow,
			String pregatheringNoHigh) {
		return pregatheringDao.queryAllPregatheringExport(pregatheringNoLow, pregatheringNoHigh);
	}

	public PregatheringDao getPregatheringDao() {
		return pregatheringDao;
	}

	public void setPregatheringDao(PregatheringDao pregatheringDao) {
		this.pregatheringDao = pregatheringDao;
	}
}
