package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.WarehouserptService;

/**
 * @name WarehouserptServiceImpl.java
 * @author Frank
 * @time 2015-6-3下午9:50:32
 * @version 1.0
 */
public class WarehouserptServiceImpl implements WarehouserptService {
	
	private WarehouserptDao warehouserptDao;
	private ProductDao productDao;
	private WarehouseDao warehouseDao;
	private PurchaseItemDao purchaseItemDao;
	private PurchaseDao purchaseDao;
	private Dict01Dao dict01Dao;

	@Override
	public Page queryWarehouserptByPage(String status, String warehousetype,
			String warehouseno, String theme1, String parentid,
			String supplierid, String productid, Page page) {
		
		//查询总记录数
		int totalCount = warehouserptDao.queryWarehouserptCountByPage(status, warehousetype, warehouseno, theme1, parentid, supplierid, productid);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouserptDto> list = warehouserptDao.queryWarehouserptByPage(status, warehousetype, warehouseno, theme1, parentid, supplierid, productid,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}
	
	@Override
	public WarehouserptDto queryWarehouserptByID(String id) {
		WarehouserptDto rpt = warehouserptDao.queryWarehouserptByID(id);
		if(rpt != null) {
			//查询对应的库存记录列表
			if(StringUtil.isNotBlank(rpt.getProductinfo())) {
				List<ProductDto> list = new ArrayList<ProductDto>();
				String[] infos = rpt.getProductinfo().split("#");
				for(String info : infos) {
					if(StringUtil.isNotBlank(info)) {
						String[] ll = info.split(",");
						ProductDto product = productDao.queryProductByID(ll[0]);
						if(product != null) {
							//货物数量
							product.setNum(ll[1]);
							//货物金额
							product.setAmount(ll[2]);
							product.setHasbroken("0");
							product.setBrokennum("0");
							list.add(product);
						}
					}
				}
				rpt.setListProduct(list);
			}
		}
		return rpt;
	}

	@Override
	public void insertWarehouserpt(WarehouserptDto warehouserpt) {
		warehouserptDao.insertWarehouserpt(warehouserpt);
	}

	@Override
	public void updateWarehouserpt(WarehouserptDto warehouserpt, Integer type) {
		if(warehouserpt.getListProduct() != null && warehouserpt.getListProduct().size() > 0) {
			String productinfo = "";
			for(ProductDto product : warehouserpt.getListProduct()) {
				//判断是否需要添加入库记录
				if("1".equals(product.getHasbroken())) {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
					//需要新增一条新的入出库记录
					WarehouseDto warehouse = new WarehouseDto();
					//数据来源单号=当前入库单号
					warehouse.setParentid(warehouserpt.getWarehouseno());
					//类型
					warehouse.setWarehousetype(type);
					//入库单号
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.substring(uuid.length() - 8, uuid.length());
					String warehouseno = "warehouse" + belongto + sdf.format(date) + uuid;
					warehouse.setWarehouseno(warehouseno);
					
					warehouse.setProductid("" + product.getId());
					
					//仓库名===========？
					//warehouse.setWarehousename(warehousename);
					//单价
					warehouse.setUnitprice(product.getPurchaseprice());
					
					warehouse.setBelongto(belongto);
					//主题
					warehouse.setTheme1(product.getFieldno());
					
					//入出库金额（含税）=入库金额*（1+税率）
					List<Dict01Dto> listRate = dict01Dao.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
					//默认为0
					BigDecimal rate = new BigDecimal(0);
					BigDecimal amount = new BigDecimal(0);
					BigDecimal taxamount = new BigDecimal(0);
					
					if(type == Constants.WAREHOUSE_TYPE_IN) {
						//采购单价
						taxamount = new BigDecimal(product.getBrokennum()).multiply(product.getPurchaseprice());
						amount = new BigDecimal(product.getBrokennum()).multiply(product.getPurchaseprice());
						//入出库数量=损毁数
						//入库单，数量为负数
						warehouse.setQuantity(-1 * Integer.valueOf(product.getBrokennum()));
					} else {
						//销售单价
						taxamount = new BigDecimal(product.getBrokennum()).multiply(product.getSalesprice());
						amount = new BigDecimal(product.getBrokennum()).multiply(product.getSalesprice());
						//出库单，数量为正数
						warehouse.setQuantity(Integer.valueOf(product.getBrokennum()));
					}
					
					if(listRate != null && listRate.size() > 0) {
						rate = new BigDecimal(listRate.get(0).getCode());
						rate = rate.add(new BigDecimal(1));
						taxamount = taxamount.multiply(rate);
						taxamount = taxamount.setScale(2, BigDecimal.ROUND_HALF_UP);
					}
					warehouse.setAmount(amount);
					warehouse.setTaxamount(taxamount);
					//入库日期=当天
					warehouse.setWarehousedate(DateUtil.dateToShortStr(new Date()));
					//供应商ID
					warehouse.setSupplierid(Long.valueOf(warehouserpt.getSupplierid()));
					//收货人
					warehouse.setHandler(warehouserpt.getHandler());
					warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
					//入库单数据状态=退货OR损毁
					warehouse.setStatus(Constants.WAREHOUSE_STATUS_REFUND);
					//备注
					warehouse.setNote(warehouserpt.getNote());
					
					warehouse.setUpdateuid(warehouserpt.getUpdateuid());
					warehouse.setCreateuid(warehouserpt.getUpdateuid());
					
					warehouseDao.insertWarehouse(warehouse);
					
					//添加入出库单记录
					WarehouserptDto newwarehouserpt = new WarehouserptDto();
					//数据来源类型=退换货
					newwarehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_REFUND);
					newwarehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
					
					//入出库单号
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");
					String warehouserptno = "warehouserpt" + belongto + sdf1.format(date);
					newwarehouserpt.setWarehouseno(warehouserptno);
					
					//货物信息：产品ID,产品数量,产品金额#产品ID,产品数量,产品金额
					newwarehouserpt.setProductinfo("" + product.getId() + "," + product.getBrokennum() + "," + taxamount + "#");
					
					//主题
					newwarehouserpt.setTheme1(product.getFieldno());
					//入出库单RPT日期
					newwarehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
					//入库数量，这里不需要乘以-1
					newwarehouserpt.setTotalnum(Integer.valueOf(product.getBrokennum()));
					//含税金额
					newwarehouserpt.setTotaltaxamount(taxamount);
					//收货人
					newwarehouserpt.setHandler("");
					
					//查询供应商信息
					newwarehouserpt.setSupplierid(warehouserpt.getSupplierid());
					newwarehouserpt.setSuppliername(warehouserpt.getSuppliername());
					newwarehouserpt.setSupplieraddress(warehouserpt.getSupplieraddress());
					newwarehouserpt.setSuppliermail(warehouserpt.getSuppliermail());
					newwarehouserpt.setSuppliermanager(warehouserpt.getSuppliermanager());
					newwarehouserpt.setSuppliertel(warehouserpt.getSuppliertel());
					newwarehouserpt.setSupplierfax(warehouserpt.getSupplierfax());
					
					newwarehouserpt.setNote(warehouserpt.getNote());
					//快递公司ID==============================这里不需要快递信息
					
					newwarehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
					newwarehouserpt.setStatus(Constants.STATUS_NORMAL);
					//入出库单单号集合
					newwarehouserpt.setParentid(warehouseno);
					newwarehouserpt.setCreateuid(warehouserpt.getUpdateuid());
					newwarehouserpt.setUpdateuid(warehouserpt.getUpdateuid());
					
					warehouserptDao.insertWarehouserpt(newwarehouserpt);
					
					//更新之前入出库单的产品信息
					int num = Integer.valueOf(product.getNum()) - Integer.valueOf(product.getBrokennum());
					BigDecimal bb = new BigDecimal(product.getAmount());
					bb = bb.subtract(taxamount);
					
					productinfo += "" + product.getId() + "," + num + "," + bb + "#";
				} else {
					productinfo += "" + product.getId() + "," + product.getNum() + "," + product.getAmount() + "#";
				}
			}
			warehouserpt.setProductinfo(productinfo);
		}
		warehouserptDao.updateWarehouserpt(warehouserpt);
	}

	public WarehouserptDao getWarehouserptDao() {
		return warehouserptDao;
	}

	public void setWarehouserptDao(WarehouserptDao warehouserptDao) {
		this.warehouserptDao = warehouserptDao;
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
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
