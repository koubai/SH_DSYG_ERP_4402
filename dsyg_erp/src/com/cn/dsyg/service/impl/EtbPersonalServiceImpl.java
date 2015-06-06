package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbPersonalDao;
import com.cn.dsyg.dto.EtbPersonalDto;
import com.cn.dsyg.service.PersonalService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbPersonalServiceImpl implements PersonalService {
	
	private EtbPersonalDao etbPersonalDao;

	@Override
	public EtbPersonalDto queryAllEtbPersonalByID(String ID) {
		return etbPersonalDao.queryAllEtbPersonalByID(ID);
	}

	public EtbPersonalDao getEtbPersonalDao() {
		return etbPersonalDao;
	}

	public void setEtbPersonalDao(EtbPersonalDao etbPersonalDao) {
		this.etbPersonalDao = etbPersonalDao;
	}

	@Override
	public Page queryEtbPersonalByPage(Page page, String userNoLow,
			String userNoHigh, String userName) {
		userNoLow = StringUtil.replaceDatabaseKeyword_mysql(userNoLow);
		//查询总记录数
		int totalCount = etbPersonalDao.queryEtbPersonalCountByPage(userNoLow, userNoHigh, userName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbPersonalDto> list = etbPersonalDao.queryEtbPersonalByPage(userNoLow, userNoHigh,
				userName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbPersonalDto queryEtbPersonalByID(String userNo) {
		return etbPersonalDao.queryEtbPersonalByID(userNo);
	}

	@Override
	public List<EtbPersonalDto> queryAllEtbPersonal() {
		return etbPersonalDao.queryAllEtbPersonal();
	}

	@Override
	public void insertEtbPersonal(EtbPersonalDto personal) {
		etbPersonalDao.insertEtbPersonal(personal);
	}

	@Override
	public void updateEtbPersonal(EtbPersonalDto personal) {
		etbPersonalDao.updateEtbPersonal(personal);
	}

	@Override
	public void deleteEtbPersonal(String userNo, String username) {
		EtbPersonalDto personal = etbPersonalDao.queryEtbPersonalByID(userNo);
		if(personal != null) {
			//状态=已删除
			personal.setStatus(Constants.STATUS_DEL);
			personal.setUpdateuid(username);
			etbPersonalDao.updateEtbPersonal(personal);
		}
	}

	@Override
	public List<EtbPersonalDto> queryAllEtbPersonalExport(String userNoLow,
			String userNoHigh) {
		return etbPersonalDao.queryAllEtbPersonalExport(userNoLow, userNoHigh);
	}
}
