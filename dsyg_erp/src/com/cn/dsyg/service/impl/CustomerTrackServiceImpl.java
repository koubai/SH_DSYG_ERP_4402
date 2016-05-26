package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerTrackDao;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dto.CustomerTrackDto;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.service.CustomerTrackService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerTrackServiceImpl implements CustomerTrackService {
	
	private CustomerTrackDao customerTrackDao;
	private ProductDao productDao;
	private Dict01Dao dict01Dao;

	@Override
	public CustomerTrackDto queryAllCustomerTrackByID(String id) {
		return customerTrackDao.queryAllCustomerTrackByID(id);
	}

	@Override
	public Page queryCustomerTrackByPage(Page page, String idLow,
			String idHigh, String customerName) {
		idLow = StringUtil.replaceDatabaseKeyword_mysql(idLow);
		//查询总记录数
		int totalCount = customerTrackDao.queryCustomerTrackCountByPage(idLow, idHigh, customerName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<CustomerTrackDto> list = customerTrackDao.queryCustomerTrackByPage(idLow, idHigh,
				customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		for(CustomerTrackDto customertrack : list){
			List<ProductDto> listproduct = new ArrayList<ProductDto>();
			ProductDto product = null;
			String[] productids = customertrack.getProduct().split(",");
			for(int i = 0; i < productids.length; i++) {
				product = productDao.queryProductByID(productids[i]);
				if(product != null){
					listproduct.add(product);
				}
			}
			customertrack.setListProduct(listproduct);
		}
		page.setItems(list);
		return page;
	}

	@Override
	public CustomerTrackDto queryCustomerTrackByID(String id) {
		CustomerTrackDto customerTrackDto = customerTrackDao.queryCustomerTrackByID(id);
		if(customerTrackDto != null){
			List<ProductDto> listproduct = new ArrayList<ProductDto>();
			String productinfo = "";
			ProductDto product = null;
			String[] productids = customerTrackDto.getProduct().split(",");
			for(int i = 0; i < productids.length; i++) {
				product = productDao.queryProductByID(productids[i]);
				if(product != null){
					listproduct.add(product);
					Dict01Dto dict = null;
					String color = "";
					//颜色
					if(StringUtil.isNotBlank(product.getColor())) {
						dict = dict01Dao.queryDict01ByLogicId(Constants.DICT_COLOR_TYPE,
								product.getColor(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
						if(dict != null) {
							color = dict.getFieldname();
						}
					}
					productinfo = productinfo + product.getTradename() + " " + product.getTypeno() + " " + color + " " + product.getItem10() + "\n";
				}
			}
			customerTrackDto.setListProduct(listproduct);
			customerTrackDto.setProductinfo(productinfo);
		}
		
		return customerTrackDto;
	}

	@Override
	public List<CustomerTrackDto> queryAllCustomerTrack() {
		return customerTrackDao.queryAllCustomerTrack();
	}

	@Override
	public String insertCustomerTrack(CustomerTrackDto customerTrack) {
		String id = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		customerTrack.setBelongto(belongto);
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		id = Constants.PERSONAL_NO_PRE + belongto + sdf.format(date) + uuid;
		
		customerTrack.setId(id);*/
		customerTrackDao.insertCustomerTrack(customerTrack);
		return id;
	}

	@Override
	public void updateCustomerTrack(CustomerTrackDto customerTrack) {
		customerTrackDao.updateCustomerTrack(customerTrack);
	}

	@Override
	public void deleteCustomerTrack(String id, String userName) {
		CustomerTrackDto customerTrack = customerTrackDao.queryCustomerTrackByID(id);
		if(customerTrack != null) {
			//状态=已删除
			customerTrack.setStatus(Constants.STATUS_DEL);
			customerTrack.setUpdateuid(userName);
			customerTrackDao.updateCustomerTrack(customerTrack);
		}
	}

	@Override
	public List<CustomerTrackDto> queryAllCustomerTrackExport(String idLow,
			String idHigh, String customerName) {
		return customerTrackDao.queryAllCustomerTrackExport(idLow, idHigh, customerName);
	}

	public CustomerTrackDao getcustomerTrackDao() {
		return customerTrackDao;
	}

	public void setcustomerTrackDao(CustomerTrackDao customerTrackDao) {
		this.customerTrackDao = customerTrackDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
