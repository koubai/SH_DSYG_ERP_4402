package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.ProductCostDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.service.ProductService;

/**
 * @name ProductServiceImpl.java
 * @author Frank
 * @time 2015-5-21下午10:58:31
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;
	private Dict01Dao dict01Dao;
	
	@Override
	public ProductDto queryProductByLogicId(String tradename, String typeno,
			String color, String item10, String packaging, String makearea ) {
		ProductDto product = productDao.queryProductByLogicId(tradename, typeno, color, item10, packaging, makearea );
		if(product != null) {
			//图片和PDF文件显示地址
			String imageurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_IMAGES_URL);
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			product.setImageurl(imageurl);
			product.setPdfurl(pdfurl);
			return product;
		}
		return null;
	}

	@Override
	public Page queryProductByPage(String fieldno, String item01, String keyword, String packaging, String tradename,
				String typeno, String color, String supplierId, String status, Page page) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		item01 = StringUtil.replaceDatabaseKeyword_mysql(item01);
		
		//查询总记录数
		int totalCount = productDao.queryProductCountByPage(fieldno, item01, keyword, packaging, tradename, typeno, color, supplierId, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<ProductDto> list = productDao.queryProductByPage(fieldno, item01, keyword, packaging, tradename, typeno, color, supplierId, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	
	
	@Override
	public Page queryProductCostCheckByPage(String fieldno, String item01, String keyword, String tradename,
				String typeno, String color, String supplierId, String belongto, String status, Page page) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		item01 = StringUtil.replaceDatabaseKeyword_mysql(item01);
		
		//查询总记录数
		int totalCount = productDao.queryProductCostCountByPage(fieldno, item01, keyword, tradename, typeno, color, supplierId, belongto, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<ProductDto> list = productDao.queryProductCostCheckByPage(fieldno, item01, keyword, tradename, typeno, color, supplierId, belongto, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}
	
	
	@Override
	public ProductDto queryProductByID(String id) {
		ProductDto product = productDao.queryProductByID(id);
		if(product != null) {
			//图片和PDF文件显示地址
			String imageurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_IMAGES_URL);
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			product.setImageurl(imageurl);
			product.setPdfurl(pdfurl);
			return product;
		}
		return null;
	}

	@Override
	public void insertProduct(ProductDto product) {
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
		
		productDao.insertProduct(product);
	}
	
	@Override
	public void updateProduct(ProductDto product) {
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
		productDao.updateProduct(product);
	}
	
	/**
	 * 关键字
	 * @param product
	 * @return
	 */
	private String getKeyword(ProductDto product) {
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
		//形式
		if(StringUtil.isNotBlank(product.getPackaging())) {
			if(Constants.DICT_PACKAGING_TYPE_CODE_1.equals(product.getPackaging())) {
				keyword += "乱尺,";
			} else if(Constants.DICT_PACKAGING_TYPE_CODE_0.equals(product.getPackaging())) {
				keyword += "整箱,";
			}
		}
		//包装
		if(StringUtil.isNotBlank(product.getItem10())) {
			keyword += product.getItem10() + ",";
		}
		//产地
		if(StringUtil.isNotBlank(product.getMakearea())) {
			dict = dict01Dao.queryDict01ByLogicId(Constants.DICT_MAKEAREA,
					product.getMakearea(), PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			if(dict != null) {
				keyword += dict.getFieldname() + ",";
			}
		}
		//UL型号/编号
		if(StringUtil.isNotBlank(product.getItem09())) {
			keyword += product.getItem09() + ",";
		}
		return keyword;
	}
	
	@Override
	public void deleteProduct(String productId, String userid) {
		ProductDto product = productDao.queryProductByID(productId);
		if(product != null) {
			//逻辑删除
			product.setStatus(Constants.STATUS_DEL);
			product.setUpdateuid(userid);
			productDao.updateProduct(product);
		}
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<ProductDto> queryProductCostToExport(String fieldno,
			String item01, String keyword, String tradename, String typeno,
			String color, String supplierId, String belongto, String status) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		item01 = StringUtil.replaceDatabaseKeyword_mysql(item01);
		
		//查询记录
		return productDao.queryProductCostToExport(fieldno, item01, keyword, tradename, typeno, color, supplierId, belongto, status);
	}
}
