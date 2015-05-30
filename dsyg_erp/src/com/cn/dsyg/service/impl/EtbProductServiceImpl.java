package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.EtbProductDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.EtbProductDto;
import com.cn.dsyg.service.EtbProductService;

/**
 * @name EtbProductServiceImpl.java
 * @author Frank
 * @time 2015-5-21下午10:58:31
 * @version 1.0
 */
public class EtbProductServiceImpl implements EtbProductService {
	
	private EtbProductDao etbProductDao;
	private Dict01Dao dict01Dao;

	@Override
	public Page queryEtbProductByPage(String fieldno, String keyword, String tradename,
				String typeno, String color, String supplierId, String status, Page page) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		
		//查询总记录数
		int totalCount = etbProductDao.queryEtbProductCountByPage(fieldno, keyword, tradename, typeno, color, supplierId, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbProductDto> list = etbProductDao.queryEtbProductByPage(fieldno, keyword, tradename, typeno, color, supplierId, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbProductDto queryEtbProductByID(String id) {
		return etbProductDao.queryEtbProductByID(id);
	}

	@Override
	public void insertEtbProduct(EtbProductDto product) {
		product.setRank(Constants.ROLE_RANK_OPERATOR);
		product.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
		product.setStatus(Constants.STATUS_NORMAL);
		
		//keyword
		product.setKeyword(getKeyword(product));
		
		//productname
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
		//包装
		String packaging = "";
		if(StringUtil.isNotBlank(product.getPackaging())) {
			if(Constants.DICT_PACKAGING_TYPE_CODE_1.equals(product.getPackaging())) {
				packaging = "乱尺";
			} else if(Constants.DICT_PACKAGING_TYPE_CODE_0.equals(product.getPackaging())) {
				packaging = "整箱";
			}
		}
		//类型
		String type = "";
		Dict01Dto dictType = dict01Dao.queryDict01ByLogicId(Constants.DICT_GOODS_TYPE, product.getFieldno(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(dictType != null) {
			type = dictType.getFieldname();
		}
		//productname=品牌-类型-品名-规格-颜色-包装-是否样品		
		String productname = product.getBrand() + "-" + type + "-" + product.getTradename() + "-" + product.getTypeno() + "-" + color + "-" + packaging;
		product.setProductname(productname);
		
		etbProductDao.insertEtbProduct(product);
	}
	
	@Override
	public void updateEtbProduct(EtbProductDto product) {
		//keyword
		product.setKeyword(getKeyword(product));
		
		//productname
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
		//包装
		String packaging = "";
		if(StringUtil.isNotBlank(product.getPackaging())) {
			if(Constants.DICT_PACKAGING_TYPE_CODE_1.equals(product.getPackaging())) {
				packaging = "乱尺";
			} else if(Constants.DICT_PACKAGING_TYPE_CODE_0.equals(product.getPackaging())) {
				packaging = "整箱";
			}
		}
		//类型
		String type = "";
		Dict01Dto dictType = dict01Dao.queryDict01ByLogicId(Constants.DICT_GOODS_TYPE, product.getFieldno(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(dictType != null) {
			type = dictType.getFieldname();
		}
		//productname=品牌-类型-品名-规格-颜色-包装-是否样品		
		String productname = product.getBrand() + "-" + type + "-" + product.getTradename() + "-" + product.getTypeno() + "-" + color + "-" + packaging;
		product.setProductname(productname);
		etbProductDao.updateEtbProduct(product);
	}
	
	/**
	 * 关键字
	 * @param product
	 * @return
	 */
	private String getKeyword(EtbProductDto product) {
		String keyword = "";
		if(product == null) {
			return keyword;
		}
		//类型
		Dict01Dto dict = dict01Dao.queryDict01ByLogicId(Constants.DICT_GOODS_TYPE, product.getFieldno(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(dict != null) {
			keyword += dict.getFieldname() + "," + dict.getNote() + ",";
		}
		//品牌
		if(StringUtil.isNotBlank(product.getBrand())) {
			keyword += product.getBrand()+ ",";
		}
		//类型1
		if(StringUtil.isNotBlank(product.getItem1())) {
			keyword += product.getItem1() + ",";
		}
		//品名
		if(StringUtil.isNotBlank(product.getTradename())) {
			keyword += product.getTradename() + ",";
		}
		//规格
		if(StringUtil.isNotBlank(product.getTypeno())) {
			keyword += product.getTypeno() + ",";
		}
		//颜色
		if(StringUtil.isNotBlank(product.getColor())) {
			dict = dict01Dao.queryDict01ByLogicId(Constants.DICT_COLOR_TYPE,
					product.getColor(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			if(dict != null) {
				keyword += dict.getFieldname() + ",";
			}
		}
		//包装
		if(StringUtil.isNotBlank(product.getPackaging())) {
			if(Constants.DICT_PACKAGING_TYPE_CODE_1.equals(product.getPackaging())) {
				keyword += "乱尺,";
			} else if(Constants.DICT_PACKAGING_TYPE_CODE_0.equals(product.getPackaging())) {
				keyword += "整箱,";
			}
		}
		//产地
		if(StringUtil.isNotBlank(product.getMakearea())) {
			dict = dict01Dao.queryDict01ByLogicId(Constants.DICT_MAKEAREA,
					product.getMakearea(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			if(dict != null) {
				keyword += dict.getFieldname() + ",";
			}
		}
		return keyword;
	}
	
	@Override
	public void deleteEtbProduct(String productId, String userid) {
		EtbProductDto product = etbProductDao.queryEtbProductByID(productId);
		if(product != null) {
			//逻辑删除
			product.setStatus(Constants.STATUS_DEL);
			product.setUpdateuid(userid);
			etbProductDao.updateEtbProduct(product);
		}
	}

	public EtbProductDao getEtbProductDao() {
		return etbProductDao;
	}

	public void setEtbProductDao(EtbProductDao etbProductDao) {
		this.etbProductDao = etbProductDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
