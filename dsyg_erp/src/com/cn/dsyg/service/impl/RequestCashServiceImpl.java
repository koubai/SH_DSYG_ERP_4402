package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.RequestCashDao;
import com.cn.dsyg.dto.RequestCashDto;
import com.cn.dsyg.service.RequestCashService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class RequestCashServiceImpl implements RequestCashService {
	
	private RequestCashDao requestcashDao;

	@Override
	public RequestCashDto queryAllRequestCashByID(String ID) {
		return requestcashDao.queryAllRequestCashByID(ID);
	}

	@Override
	public Page queryRequestCashByPage(Page page, String requestcashNoLow,
			String requestcashNoHigh, String requestcashName) {
		requestcashNoLow = StringUtil.replaceDatabaseKeyword_mysql(requestcashNoLow);
		//查询总记录数
		int totalCount = requestcashDao.queryRequestCashCountByPage(requestcashNoLow, requestcashNoHigh, requestcashName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<RequestCashDto> list = requestcashDao.queryRequestCashByPage(requestcashNoLow, requestcashNoHigh,
				requestcashName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		for (RequestCashDto RequestCashReport : list){
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			RequestCashReport.setReporturl(pdfurl);
		}
		page.setItems(list);
		return page;
	}

	@Override
	public RequestCashDto queryRequestCashByID(String requestcashNo) {
		RequestCashDto RequestCashreport = requestcashDao.queryRequestCashByID(requestcashNo);
		if(RequestCashreport != null) {
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			RequestCashreport.setReporturl(pdfurl);
			return RequestCashreport;
		}
		return null;
	}

	@Override
	public List<RequestCashDto> queryAllRequestCash() {
		return requestcashDao.queryAllRequestCash();
	}

	@Override
	public String insertRequestCash(RequestCashDto requestcash) {
		String requestcashno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		requestcash.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		requestcashno = Constants.REQUESTCASH_NO_PRE + belongto + sdf.format(date) + uuid;
		requestcash.setRequestcashno(requestcashno);
		
		requestcashDao.insertRequestCash(requestcash);
		return requestcashno;
	}

	@Override
	public void updateRequestCash(RequestCashDto requestcash) {
		requestcashDao.updateRequestCash(requestcash);
	}

	@Override
	public void deleteRequestCash(String requestcashNo, String username) {
		RequestCashDto requestcash = requestcashDao.queryRequestCashByID(requestcashNo);
		if(requestcash != null) {
			//状态=已删除
			requestcash.setStatus(Constants.STATUS_DEL);
			requestcash.setUpdateuid(username);
			requestcashDao.updateRequestCash(requestcash);
		}
	}

	@Override
	public List<RequestCashDto> queryAllRequestCashExport(String requestcashNoLow,
			String requestcashNoHigh) {
		return requestcashDao.queryAllRequestCashExport(requestcashNoLow, requestcashNoHigh);
	}

	public RequestCashDao getRequestCashDao() {
		return requestcashDao;
	}

	public void setRequestCashDao(RequestCashDao requestcashDao) {
		this.requestcashDao = requestcashDao;
	}

	public RequestCashDao getRequestcashDao() {
		return requestcashDao;
	}

	public void setRequestcashDao(RequestCashDao requestcashDao) {
		this.requestcashDao = requestcashDao;
	}
}
