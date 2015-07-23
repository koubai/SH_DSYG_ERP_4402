package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.PersonalDao;
import com.cn.dsyg.dto.PersonalDto;
import com.cn.dsyg.service.PersonalService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class PersonalServiceImpl implements PersonalService {
	
	private PersonalDao personalDao;

	@Override
	public PersonalDto queryAllEtbPersonalByID(String ID) {
		return personalDao.queryAllEtbPersonalByID(ID);
	}

	@Override
	public Page queryEtbPersonalByPage(Page page, String userNoLow,
			String userNoHigh, String userName) {
		userNoLow = StringUtil.replaceDatabaseKeyword_mysql(userNoLow);
		//查询总记录数
		int totalCount = personalDao.queryEtbPersonalCountByPage(userNoLow, userNoHigh, userName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PersonalDto> list = personalDao.queryEtbPersonalByPage(userNoLow, userNoHigh,
				userName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public PersonalDto queryEtbPersonalByID(String userNo) {
		return personalDao.queryEtbPersonalByID(userNo);
	}

	@Override
	public List<PersonalDto> queryAllEtbPersonal() {
		return personalDao.queryAllEtbPersonal();
	}

	@Override
	public String insertEtbPersonal(PersonalDto personal) {
		String userno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		personal.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		userno = Constants.PERSONAL_NO_PRE + belongto + sdf.format(date) + uuid;
		
		personal.setUserno(userno);
		personalDao.insertEtbPersonal(personal);
		return userno;
	}

	@Override
	public void updateEtbPersonal(PersonalDto personal) {
		personalDao.updateEtbPersonal(personal);
	}

	@Override
	public void deleteEtbPersonal(String userNo, String username) {
		PersonalDto personal = personalDao.queryEtbPersonalByID(userNo);
		if(personal != null) {
			//状态=已删除
			personal.setStatus(Constants.STATUS_DEL);
			personal.setUpdateuid(username);
			personalDao.updateEtbPersonal(personal);
		}
	}

	@Override
	public List<PersonalDto> queryAllEtbPersonalExport(String userNoLow,
			String userNoHigh) {
		return personalDao.queryAllEtbPersonalExport(userNoLow, userNoHigh);
	}

	public PersonalDao getPersonalDao() {
		return personalDao;
	}

	public void setPersonalDao(PersonalDao personalDao) {
		this.personalDao = personalDao;
	}
}
