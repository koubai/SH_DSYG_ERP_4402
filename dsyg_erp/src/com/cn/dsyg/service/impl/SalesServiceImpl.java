package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.service.SalesService;

/**
 * @name SalesServiceImpl.java
 * @author Frank
 * @time 2015-6-16下午9:58:24
 * @version 1.0
 */
public class SalesServiceImpl implements SalesService {
	
	private SalesDao salesDao;
	private SalesItemDao salesItemDao;
	private WarehouseDao warehouseDao;
	private Dict01Dao dict01Dao;
	private FinanceDao financeDao;
	private UserDao userDao;
	
	@Override
	public Page queryFinanceSalesByPage(String bookdateLow,
			String bookdateHigh, String status, Page page) {
		//查询总记录数
		int totalCount = salesDao.queryFinanceSalesCountByPage(bookdateLow, bookdateHigh, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesDto> list = salesDao.queryFinanceSalesByPage(bookdateLow, bookdateHigh, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(SalesDto sales : list) {
				UserDto user = userDao.queryUserByID(sales.getHandler());
				if(user != null) {
					sales.setHandlername(user.getUsername());
				}
			}
		}
		page.setItems(list);
		return page;
	}
	
	@Override
	public String addSales(SalesDto sales, List<SalesItemDto> listSalesItem,
			String userid) {
		//生成销售单号
		String salesno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		sales.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		salesno = Constants.SALES_NO_PRE + belongto + sdf.format(date) + uuid;
		sales.setSalesno(salesno);
		
		String theme2 = "";
		if(!"0".equals(sales.getRes02())) {
			//销售方式为询价询样时，订单号自动生成
			uuid = uuid.substring(uuid.length() - 8, uuid.length());
			theme2 = Constants.SALES_DD_PRE + belongto + sdf.format(date) + uuid;
			sales.setTheme2(theme2);
		}
		//status
		sales.setStatus(Constants.SALES_STATUS_NEW);
		//rank
		sales.setRank(Constants.ROLE_RANK_OPERATOR);
		
		//经手人默认为当前用户
		sales.setHandler(userid);
		
		//仓库
		sales.setWarehouse(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_WAREHOUSE_NAME));
		
		sales.setUpdateuid(userid);
		sales.setCreateuid(userid);
		salesDao.insertSales(sales);
		//保存销售单对应的货物数据
		if(listSalesItem != null) {
			for(SalesItemDto salesItem : listSalesItem) {
				//销售单号
				salesItem.setSalesno(salesno);
				//用户自己输入的订单号
				salesItem.setTheme2(sales.getTheme2());
				salesItem.setHandler(userid);
				salesItem.setUpdateuid(userid);
				salesItem.setCreateuid(userid);
				salesItem.setStatus(Constants.STATUS_NORMAL);
				salesItem.setBelongto(belongto);
				salesItem.setRank(Constants.ROLE_RANK_OPERATOR);
				salesItem.setCustomerid("" + sales.getCustomerid());
				salesItem.setPlandate(sales.getPlandate());
				
				//销售方式
				salesItem.setRes02(sales.getRes02());
				
				//判断预出库数量是否大于0，若大于0则生产库存记录
				if(salesItem.getBeforequantity() != null && salesItem.getBeforequantity().floatValue() > 0) {
					//新增库存记录
					addWarehouse(sales, salesItem);
					//已出库数=预出库数+之前已出库数
					if(salesItem.getOutquantity() == null) {
						salesItem.setOutquantity(salesItem.getBeforequantity());
					} else {
						salesItem.setOutquantity(salesItem.getBeforequantity().add(salesItem.getOutquantity()));
					}
				}
				//特殊订单号
				salesItem.setRes09(salesItem.getRes09());
				//预出库数重置为0
				salesItem.setBeforequantity(new BigDecimal(0));
				salesItemDao.insertSalesItem(salesItem);
			}
		}
		return theme2;
	}

	@Override
	public Page querySalesByPage(String bookdateLow, String bookdateHigh, String theme2,
			String type, String customername, Page page) {
		customername = StringUtil.replaceDatabaseKeyword_mysql(customername);
		
		//查询总记录数
		int totalCount = salesDao.querySalesCountByPage(bookdateLow, bookdateHigh, theme2, type, customername);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesDto> list = salesDao.querySalesByPage(bookdateLow, bookdateHigh, theme2, type, customername,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(SalesDto sales : list) {
				UserDto user = userDao.queryUserByID(sales.getHandler());
				if(user != null) {
					sales.setHandlername(user.getUsername());
				}
			}
		}
		page.setItems(list);
		return page;
	}
	
	@Override
	public void updateSales(SalesDto sales, List<SalesItemDto> listSalesItem, String userid) {
		salesDao.updateSales(sales);
		//根据销售单号删除所有的货物数据
		salesItemDao.deleteSalesItemBySalesno(sales.getSalesno(), userid);
		//保存销售单对应的货物数据
		if(listSalesItem != null) {
			for(SalesItemDto salesItem : listSalesItem) {
				if(salesItem.getId() == null) {
					//新增
					//销售单号
					salesItem.setSalesno(sales.getSalesno());
					//用户自己输入的订单号
					salesItem.setTheme2(sales.getTheme2());
					salesItem.setUpdateuid(userid);
					salesItem.setCreateuid(userid);
					salesItem.setStatus(Constants.STATUS_NORMAL);
					salesItem.setBelongto(sales.getBelongto());
					salesItem.setCustomerid("" + sales.getCustomerid());
					
					//销售方式
					salesItem.setRes02(sales.getRes02());
					
					//判断预出库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(salesItem.getBeforequantity() != null && salesItem.getBeforequantity().floatValue() != 0) {
						//新增库存记录
						addWarehouse(sales, salesItem);
						//已出库数=预出库数+之前已出库数
						if(salesItem.getOutquantity() == null) {
							salesItem.setOutquantity(salesItem.getBeforequantity());
						} else {
							salesItem.setOutquantity(salesItem.getBeforequantity().add(salesItem.getOutquantity()));
						}
					}
					//特殊订单号
					salesItem.setRes09(salesItem.getRes09());

					//预出库数重置为0
					salesItem.setBeforequantity(new BigDecimal(0));
					salesItemDao.insertSalesItem(salesItem);
				} else {
					//修改
					salesItem.setUpdateuid(userid);
					salesItem.setStatus(Constants.STATUS_NORMAL);
					
					//销售方式
					salesItem.setRes02(sales.getRes02());
					
					//判断预出库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(salesItem.getBeforequantity() != null && salesItem.getBeforequantity().floatValue() != 0) {
						//新增库存记录
						addWarehouse(sales, salesItem);
						//已出库数=预出库数+之前已出库数
						if(salesItem.getOutquantity() == null) {
							salesItem.setOutquantity(salesItem.getBeforequantity());
						} else {
							salesItem.setOutquantity(salesItem.getBeforequantity().add(salesItem.getOutquantity()));
						}
					}
					//特殊订单号
					salesItem.setRes09(salesItem.getRes09());

					//预出库数重置为0
					salesItem.setBeforequantity(new BigDecimal(0));
					salesItemDao.updateSalesItem(salesItem);
				}
			}
		}
		//删除status=0的数据
		salesItemDao.deleteNoUseSalesItemBySalesno(sales.getSalesno());
	}
	
	@Override
	public void updateFinanceSales(String id, String userid, String status) {
		SalesDto sales = salesDao.querySalesByID(id);
		if(sales != null) {
			sales.setUpdateuid(userid);
			//更新采购单状态
			sales.setStatus(Integer.valueOf(status));
			salesDao.updateSales(sales);
			
			//更新财务数据状态
			FinanceDto finance = financeDao.queryFinanceByInvoiceid(sales.getSalesno(), "" + Constants.FINANCE_TYPE_SALES);
			if(finance != null) {
				if(status.equals("" + Constants.FINANCE_STATUS_PAY_INVOICE)) {
					//开票日期=当天
					finance.setReceiptdate(DateUtil.dateToShortStr(new Date()));
				}
				//确认者=当前用户
				finance.setApproverid(userid);
				finance.setStatus(Integer.valueOf(status));
				financeDao.updateFinance(finance);
			}
		}
	}
	
	@Override
	public void updateSales(SalesDto sales) {
		salesDao.updateSales(sales);
	}
	
	/**
	 * 新增库存记录
	 * @param sales
	 * @param salesItem
	 */
	private void addWarehouse(SalesDto sales, SalesItemDto salesItem) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		
		WarehouseDto warehouse = new WarehouseDto();
		//数据来源单号=销售单
		warehouse.setParentid(sales.getSalesno());
		//库存类型=出库单
		warehouse.setWarehousetype(Constants.WAREHOUSE_TYPE_OUT);
		//仓库
		warehouse.setWarehousename(sales.getWarehouse());
		
		//支付方式
		warehouse.setRes01(sales.getRes01());
		
		//出库单号
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		String warehouseno = Constants.WAREHOUSE_NO_PRE + belongto + sdf.format(date) + uuid;
		warehouse.setWarehouseno(warehouseno);
		
		warehouse.setPlandate(sales.getPlandate());
		
		warehouse.setBelongto(belongto);
		//主题
		warehouse.setTheme1(sales.getTheme1());
		//用户自己输入的订单号
		warehouse.setTheme2(sales.getTheme2());
		//产品ID
		warehouse.setProductid("" + salesItem.getProductid());
		//出库数量=预出库数（这里是出库，所以是负数）
		warehouse.setQuantity(new BigDecimal(-1).multiply(salesItem.getBeforequantity()));
		//单价
		warehouse.setUnitprice(salesItem.getUnitprice());
		
		//出库金额=出库数量*单价
		BigDecimal amount = salesItem.getUnitprice().multiply(salesItem.getBeforequantity());
		//出库金额含税=出库金额*税率
		BigDecimal taxamount = new BigDecimal(0);
		
		//出库金额（含税）=出库金额*（1+税率）
		List<Dict01Dto> listRate = dict01Dao.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//默认为0
		BigDecimal rate = new BigDecimal(0);
		if(listRate != null && listRate.size() > 0) {
			rate = new BigDecimal(listRate.get(0).getCode());
			rate = rate.add(new BigDecimal(1));
			taxamount = amount.multiply(rate);
			taxamount = taxamount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		//入出库金额
		warehouse.setAmount(amount);
		//入出库金额（含税）
		warehouse.setTaxamount(taxamount);
		//出库日期=当天
		warehouse.setWarehousedate(DateUtil.dateToShortStr(new Date()));
		//客户ID
		warehouse.setSupplierid(sales.getCustomerid());
		//收货人
		warehouse.setHandler(sales.getHandler());
		warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
		//出库单数据状态=新增
		warehouse.setStatus(Constants.WAREHOUSE_STATUS_NEW);
		//特殊订单号
		warehouse.setRes09(salesItem.getRes09());

		warehouse.setUpdateuid(sales.getCreateuid());
		warehouse.setCreateuid(sales.getCreateuid());
		
		warehouseDao.insertWarehouse(warehouse);
	}

	@Override
	public SalesDto querySalesByID(String id) {
		SalesDto sales = salesDao.querySalesByID(id);
		if(sales != null) {
			UserDto user = userDao.queryUserByID(sales.getHandler());
			if(user != null) {
				sales.setHandlername(user.getUsername());
			}
		}
		return sales;
	}

	@Override
	public SalesDto querySalesByNo(String salesno) {
		return salesDao.querySalesByNo(salesno);
	}

	public SalesDto querySalesByTheme2(String theme2){
		return salesDao.querySalesByTheme2(theme2);
	}

	
	@Override
	public void deleteSales(String id, String userid) {
		SalesDto sales = salesDao.querySalesByID(id);
		if(sales != null) {
			//根据订单号，物理删除item
			salesItemDao.deleteAllSalesItemBySalesno(sales.getSalesno());
			//物理删除订单
			salesDao.deleteSales(id);
		}
		
		//逻辑删除
//		if(sales != null) {
//			sales.setStatus(Constants.STATUS_DEL);
//			sales.setUpdateuid(userid);
//			salesDao.updateSales(sales);
//		}
	}

	@Override
	public void insertSales(SalesDto sales) {
		salesDao.insertSales(sales);
	}

	public SalesDao getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	public SalesItemDao getSalesItemDao() {
		return salesItemDao;
	}

	public void setSalesItemDao(SalesItemDao salesItemDao) {
		this.salesItemDao = salesItemDao;
	}

	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
