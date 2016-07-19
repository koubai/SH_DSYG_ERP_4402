package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerDao;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.PositionDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.PositionDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.ProductQuantityDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.dto.WarehouseDetailDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseInOutOkDto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.dto.WarehouseProductDto;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.WarehouseService;

/**
 * @name WarehouseServiceImpl.java
 * @author Frank
 * @time 2015-6-7下午9:05:51
 * @version 1.0
 */
public class WarehouseServiceImpl implements WarehouseService {
	
	private PurchaseDao purchaseDao;
	private PurchaseItemDao purchaseItemDao;
	private SalesDao salesDao;
	private SalesItemDao salesItemDao;
	private WarehouseDao warehouseDao;
	private WarehouserptDao warehouserptDao;
	private SupplierDao supplierDao;
	private CustomerDao customerDao;
	private FinanceDao financeDao;
	private ProductDao productDao;
	private PositionDao positionDao;
	private UserDao userDao;
	private Dict01Dao dict01Dao;
	
	@Override
	public String checkProductAmount(String productInfo) {
		String result = "";
		if(StringUtil.isNotBlank(productInfo)) {
			String[] list = productInfo.split("#");
			ProductDto product = null;
			int index = 1;
			for(String info : list) {
				if(StringUtil.isNotBlank(info)) {
					String ss[] = info.split(",");
					Double dd = warehouseDao.queryAmountByProductId(ss[0]);
					BigDecimal warehouseAmount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal salesAmount = new BigDecimal(ss[1]);
					//只CHECK预出库数量!=0的
					if(salesAmount.compareTo(new BigDecimal(0)) != 0) {
						//数量为空，则默认为0
						if(dd != null) {
							warehouseAmount = new BigDecimal(dd).setScale(2, BigDecimal.ROUND_HALF_UP);
						}
						if(warehouseAmount.compareTo(salesAmount) < 0) {
							product = productDao.queryProductByID(ss[0]);
							//tradename typeno packaging item10 
							//说明库存数量不够
							result += "NO" + index + "【" + StringUtil.getStr(product.getTradename()) + " "
									+ StringUtil.getStr(product.getTypeno()) + " "
									+ StringUtil.getStr(product.getPackaging()) + " "
									+ StringUtil.getStr(product.getItem10()) + "】库存不足" + ss[1] + "，现库存" + warehouseAmount + "，\\n";
						}
					}
					index++;
				}
			}
			if(StringUtil.isNotBlank(result)) {
				result = result.substring(0, result.length() - 3) + "。";
			}
		}
		return result;
	}
	
	@Override
	public boolean checkProductQuantity(String productid, String num, String productposition, String userid) {
		//查询原始库存
		ProductQuantityDto p = warehouseDao.queryProductQuantityById(productid);
		//仓库
		String warehousename = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_WAREHOUSE_NAME);
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		if(p != null) {
			if(p.getQuantity() != null && !"".equals(p.getQuantity())) {
				//有库存数据
				//判断库存数量和输入的数量是否相等
				BigDecimal oldNum = new BigDecimal(p.getQuantity());
				BigDecimal newNum = new BigDecimal(num);
				if(oldNum.compareTo(newNum) != 0) {
					//需要新增库存数据
					BigDecimal addNum = newNum.subtract(oldNum);
					
					ProductDto product = productDao.queryProductByID(productid);
					
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					
//					WarehouseDto warehouse = new WarehouseDto();
//					//数据来源单号=盘点
//					warehouse.setParentid("PD");
//					//库存类型=盘点
//					warehouse.setWarehousetype(Constants.WAREHOUSE_TYPE_PD);
//					//仓库
//					warehouse.setWarehousename(warehousename);
//					//预入库时间
//					warehouse.setPlandate(DateUtil.dateToShortStr(date));
//					
//					//库存单号
//					String uuid = UUID.randomUUID().toString();
//					uuid = uuid.substring(uuid.length() - 8, uuid.length());
//					String warehouseno = Constants.WAREHOUSE_NO_PRE + belongto + sdf.format(date) + uuid;
//					warehouse.setWarehouseno(warehouseno);
//					
//					//支付方式
//					warehouse.setRes01("");
//					
//					warehouse.setBelongto(belongto);
//					//主题
//					warehouse.setTheme1(product.getFieldno());
//					//产品ID
//					warehouse.setProductid("" + productid);
//					//入库数量=预入库数
//					warehouse.setQuantity(addNum);
//					
//					//入库日期=当天
//					warehouse.setWarehousedate(DateUtil.dateToShortStr(new Date()));
//					warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
//					//入库单数据状态=新增
//					warehouse.setStatus(Constants.WAREHOUSE_STATUS_NEW);
//					
//					warehouse.setUpdateuid(userid);
//					warehouse.setCreateuid(userid);
//					
//					warehouseDao.insertWarehouse(warehouse);
				}
				String checkday = DateUtil.dateToShortStr(new Date());
				//查询盘点表的数据
				List<PositionDto> list = positionDao.queryPositionByLogicId("", productid, checkday);
				if(list != null && list.size() > 0) {
					PositionDto position = list.get(0);
					//更新数据
					position.setAmount(newNum);
					position.setBeforeamount(oldNum);
					position.setHandler(userid);
					position.setUpdateuid(userid);
					position.setProductposition(productposition);
					positionDao.updatePosition(position);
				} else {
					//没有位置数据，则新增一条记录
					//新增盘点记录
					PositionDto position = new PositionDto();
					position = new PositionDto();
					position.setAmount(newNum);
					position.setBeforeamount(oldNum);
					position.setBelongto(belongto);
					position.setCreateuid(userid);
					position.setUpdateuid(userid);
					position.setProductid(productid);
					position.setCheckday(checkday);
					position.setProductposition(productposition);
					position.setRank(Constants.ROLE_RANK_OPERATOR);
					position.setStatus(Constants.STATUS_NORMAL);
					position.setWarehousename(warehousename);
					position.setHandler(userid);
					positionDao.insertPosition(position);
				}
				return true;
			}
		} else {
			//没有库存数据
		}
		return false;
	}
	
	@Override
	public ProductQuantityDto queryProductQuantityById(String productid) {
		return warehouseDao.queryProductQuantityById(productid);
	}
	
	@Override
	public Page queryWarehouseRefundByPage(String warehousetype, String theme1,
			String warehousename, Page page) {
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseRefundCountByPage(warehousetype, theme1, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDto> list = warehouseDao.queryWarehouseRefundByPage(warehousetype, theme1, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(WarehouseDto warehouseDto : list) {
				ProductDto product = productDao.queryProductByID(warehouseDto.getProductid());
				if(product != null) {
					warehouseDto.setProductname(product.getTradename());
					warehouseDto.setTypeno(product.getTypeno());
					warehouseDto.setColor(product.getColor());
					warehouseDto.setPackaging(product.getPackaging());
					warehouseDto.setUnit(product.getUnit());
					warehouseDto.setItem10(product.getItem10());
					warehouseDto.setMakearea(product.getMakearea());
				}
			}
		}
		page.setItems(list);
		return page;
	}
	
	@Override
	public Page queryWarehouseCheckByPage(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseCheckCountByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseCheckDto> list = warehouseDao.queryWarehouseCheckByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(WarehouseCheckDto warehouseCheck : list) {
				List<PositionDto> listPosition = positionDao.queryPositionByLogicId("", warehouseCheck.getProductid(), "");
				if(listPosition != null && listPosition.size() > 0) {
					PositionDto position = listPosition.get(0);
					warehouseCheck.setWarehouseposition(position.getProductposition());
					warehouseCheck.setCheckAmount(position.getAmount());
					UserDto user = userDao.queryUserByID(position.getHandler());
					if(user != null) {
						warehouseCheck.setHandlename(user.getUsername());
					}
				} else {
					warehouseCheck.setWarehouseposition("");
				}
			}
		}
		page.setItems(list);
		return page;
	}
	
	@Override
	public Page queryWarehouseProductByPage(
			String parentid, String warehousetype, String warehouseno,
			String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseProductCountByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseProductDto> list = warehouseDao.queryWarehouseProductByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}
	
	@Override
	public void warehouseInOk(String ids, String userid) throws RuntimeException {
		if(StringUtil.isNotBlank(ids)) {
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			
			String[] idList = ids.split(",");
			WarehouseDto warehouse = null;
			
			//验证是否是同一个仓库，相同的预入库时间
			String warehousename = "";
			String plandate = "";
			String suppid = "";
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						//数据check
						if(warehouse.getStatus() != Constants.WAREHOUSE_STATUS_NEW) {
							throw new RuntimeException("当前记录状态已更改，不能生成入库单！");
						}
						if(StringUtil.isBlank(warehousename)) {
							warehousename = warehouse.getWarehousename();
						}
						if(StringUtil.isBlank(plandate)) {
							plandate = warehouse.getPlandate();
						}
						if(StringUtil.isBlank(suppid)) {
							suppid = "" + warehouse.getSupplierid();
						}
						
						if(!warehousename.equals(warehouse.getWarehousename())) {
							throw new RuntimeException("不同仓库记录不能合并成一个入库单！");
						}
						if(!suppid.equals("" + warehouse.getSupplierid())) {
							throw new RuntimeException("不同供应商的记录不能合并成一个入库单！");
						}
//						if(!plandate.equals(warehouse.getPlandate())) {
//							throw new RuntimeException("不同预入库时间记录不能合并成一个入库单！");
//						}
					}
				}
			}
			
			//供应商ID
			String supplierid = "";
			//产品合集
			Map<String, BigDecimal> quantityMap = new HashMap<String, BigDecimal>();
			Map<String, BigDecimal> amountMap = new HashMap<String, BigDecimal>();
			//入库单号集合
			String warehousenos = "";
			BigDecimal count = new BigDecimal(0);
			//产品信息
			String productinfo = "";
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						supplierid = "" + warehouse.getSupplierid();
						
						if(quantityMap.get(warehouse.getProductid()) != null) {
							quantityMap.put(warehouse.getProductid(), quantityMap.get(warehouse.getProductid()).add(warehouse.getQuantity()));
							amountMap.put(warehouse.getProductid(), amountMap.get(warehouse.getProductid()).add(warehouse.getTaxamount()));
						} else {
							quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							amountMap.put(warehouse.getProductid(), warehouse.getTaxamount());
						}
						
						warehouse.setUpdateuid(userid);
						warehouse.setApproverid(userid);
						warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
						
						//计算当前集集的库存数量
						count = count.add(warehouse.getQuantity());
						warehousenos += warehouse.getWarehouseno() + ",";
						
						productinfo += warehouse.getProductid() + "," + warehouse.getQuantity() + "," + warehouse.getTaxamount() + "," + StringUtil.getStr(warehouse.getRes09()) + "," + StringUtil.getStr(warehouse.getRes02()) + "#";
						
						//计算含税金额
						totaltaxamount = totaltaxamount.add(warehouse.getTaxamount());
						
						warehouseDao.updateWarehouse(warehouse);
						
						List<PurchaseItemDto> purchaseItemList = purchaseItemDao.queryPurchaseItemByPurchaseno(warehouse.getParentid());
						if(purchaseItemList != null && purchaseItemList.size() > 0) {
							boolean b = true;
							//判断当前的采购单对应的货物是否都已入库：采购数量=入库数量
							for(PurchaseItemDto item : purchaseItemList) {
								if(item.getQuantity() != null && item.getQuantity().floatValue() > item.getInquantity().floatValue()) {
									b = false;
									break;
								}
							}
							if(b) {
								//判断所有的库存记录均为已确认
								List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByParentid(warehouse.getParentid(), "");
								for(WarehouseDto warehouseDto : listWarehouse) {
									if(warehouseDto.getStatus() <= Constants.WAREHOUSE_STATUS_NEW) {
										b = false;
										break;
									}
								}
							}
							//以上2个条件均满足，则更新采购单状态
							if(b) {
								PurchaseDto purchaseDto = purchaseDao.queryPurchaseByNo(warehouse.getParentid());
								//需要更新采购单状态=入库确认
								purchaseDto.setStatus(Constants.PURCHASE_STATUS_WAREHOUSE_OK);
								purchaseDto.setUpdateuid(userid);
								purchaseDao.updatePurchase(purchaseDto);
							} else {
								PurchaseDto purchaseDto = purchaseDao.queryPurchaseByNo(warehouse.getParentid());
								//需要更新采购单状态=部分入库
								purchaseDto.setStatus(Constants.PURCHASE_STATUS_WAREHOUSE_PART);
								purchaseDto.setUpdateuid(userid);
								purchaseDao.updatePurchase(purchaseDto);
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=入库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_IN);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			
			//入库单号
			//String warehouseno = Constants.WAREHOUSERPT_IN_NO_PRE + belongto + sdf.format(date);
			int newVal = 1;
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			String year = sdfYear.format(date);
			//根据入库单+年份查询入库单当前番号
			List<Dict01Dto> dictList = dict01Dao.queryDict01ByFieldcode(Constants.WAREHOUSERPT_IN_NO_PRE + year, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			if(dictList != null && dictList.size() > 0) {
				Dict01Dto dict = dictList.get(0);
				//入库单番号+1
				newVal = Integer.valueOf(dict.getCode()) + 1;
				dict.setCode("" + newVal);
				//更新入库单番号
				dict01Dao.updateDict01(dict);
			} else {
				//新增入库单番号
				Dict01Dto dict = new Dict01Dto();
				dict.setCode("1");
				dict.setCreateuid("admin");
				dict.setUpdateuid("admin");
				dict.setFieldcode(Constants.WAREHOUSERPT_IN_NO_PRE + year);
				dict.setFieldname(year + "入库单番号");
				dict.setNote(year + "入库单番号");
				dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
				dict.setMean(Constants.WAREHOUSERPT_IN_NO_PRE + year);
				dict.setStatus(Constants.STATUS_NORMAL);
				dict01Dao.insertDict01(dict);
			}
			String warehouseno = Constants.WAREHOUSERPT_IN_NO_PRE + belongto + year.substring(2, 4) + StringUtil.replenishStr("" + newVal, 6);
			
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			warehouserpt.setWarehousename(warehousename);
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
			/*
			String productinfo = "";


			Set<?> key = quantityMap.keySet();
			for (Iterator<?> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				productinfo += k + "," + quantityMap.get(k) + "," + amountMap.get(k) + "#";
			} */
			
			warehouserpt.setProductinfo(productinfo);
			//入库单RPT日期
			warehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
			//入库数量
			warehouserpt.setTotalnum(count);
			//含税金额
			warehouserpt.setTotaltaxamount(totaltaxamount);
			//收货人
			warehouserpt.setHandler("");
			
			//查询供应商信息
			SupplierDto supplier = supplierDao.queryAllSupplierByID(supplierid);
			//获得采购单的供应商
			warehouserpt.setSupplierid(supplierid);
			if(supplier != null) {
				warehouserpt.setSuppliername(supplier.getSuppliername());
				warehouserpt.setSupplieraddress(supplier.getSupplieraddress1());
				warehouserpt.setSuppliermail(supplier.getSuppliermail1());
				warehouserpt.setSuppliermanager(supplier.getSuppliermanager1());
				warehouserpt.setSuppliertel(supplier.getSuppliertel1());
				warehouserpt.setSupplierfax(supplier.getSupplierfax1());
			}
			//快递公司ID==============================这里不做填充，等发货单时填充
			
			warehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
			warehouserpt.setStatus(Constants.FINANCE_STATUS_NEW);
			//warehouserpt.setProductid(Long.valueOf(productid));
			//入库单单号集合
			warehouserpt.setParentid(warehousenos);
			warehouserpt.setCreateuid(userid);
			warehouserpt.setUpdateuid(userid);
			
			warehouserptDao.insertWarehouserpt(warehouserpt);
			
			//新增一条财务记录（这里财务记录和入库单关联）
			FinanceDto finance = new FinanceDto();
			//类型=入库单
			finance.setFinancetype(Constants.FINANCE_TYPE_PURCHASE);
			//采购单（付款）
			finance.setMode("2");
			finance.setBelongto(belongto);
			//单据号=入库单号
			finance.setInvoiceid(warehouseno);
			//发票号
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String receiptid = Constants.FINANCE_NO_PRE + belongto + sdf1.format(date);
			finance.setReceiptid(receiptid);
			//开票日期
			//finance.setReceiptdate(receiptdate);
			//结算日期=当天
			finance.setAccountdate(DateUtil.dateToShortStr(date));
			//金额=采购金额含税
			finance.setAmount(totaltaxamount);
			//负责人
			finance.setHandler(userid);
			//供应商信息
			finance.setCustomerid(Long.valueOf(supplierid));
			finance.setCustomername(supplier.getSuppliername());
			finance.setCustomertel(supplier.getSuppliertel1());
			finance.setCustomermanager(supplier.getSuppliermanager1());
			finance.setCustomeraddress(supplier.getSupplieraddress1());
			finance.setCustomermail(supplier.getSuppliermail1());
			finance.setRank(Constants.ROLE_RANK_OPERATOR);
			//状态=付款申请
			finance.setStatus(Constants.FINANCE_STATUS_NEW);
			finance.setCreateuid(userid);
			finance.setUpdateuid(userid);
			financeDao.insertFinance(finance);
		}
	}
	
	@Override
	public void warehouseOutOk(String ids, String userid) throws RuntimeException {
		if(StringUtil.isNotBlank(ids)) {
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			
			String[] idList = ids.split(",");
			WarehouseDto warehouse = null;
			
			//验证是否是同一个仓库，相同的预出库时间
			String warehousename = "";
			String supplierid = "";
			String plandate = "";
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						//数据check
						if(warehouse.getStatus() != Constants.WAREHOUSE_STATUS_NEW) {
							throw new RuntimeException("当前记录状态已更改，不能生成出库单！");
						}
						
						if(StringUtil.isBlank(warehousename)) {
							warehousename = warehouse.getWarehousename();
						}
						if(StringUtil.isBlank(plandate)) {
							plandate = warehouse.getPlandate();
						}
						if(StringUtil.isBlank(supplierid)) {
							supplierid = "" + warehouse.getSupplierid();
						}
						
						if(!warehousename.equals(warehouse.getWarehousename())) {
							throw new RuntimeException("不同仓库记录不能合并成一个出库单！");
						}
						//对于出库单，这里记录的是客户ID
						if(!supplierid.equals("" + warehouse.getSupplierid())) {
							throw new RuntimeException("不同客户的记录不能合并成一个出库单！");
						}
//						if(!plandate.equals(warehouse.getPlandate())) {
//							throw new RuntimeException("不同预出库时间记录不能合并成一个出库单！");
//						}
					}
				}
			}
			
			//客户ID
			String customerid = "";
			//入库单号集合
			String warehousenos = "";
			//产品信息
			String productinfo = "";
			//产品合集
			Map<String, BigDecimal> quantityMap = new HashMap<String, BigDecimal>();
			Map<String, BigDecimal> amountMap = new HashMap<String, BigDecimal>();
			BigDecimal count = new BigDecimal(0);
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						customerid = "" + warehouse.getSupplierid();
						
						if(quantityMap.get(warehouse.getProductid()) != null) {
							//发货单数量是负数，所以需要变成正的
							if(warehouse.getQuantity().floatValue() < 0) {
								quantityMap.put(warehouse.getProductid(), quantityMap.get(warehouse.getProductid()).add(warehouse.getQuantity().multiply(new BigDecimal(-1))));
							} else {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							}
							amountMap.put(warehouse.getProductid(), amountMap.get(warehouse.getProductid()).add(warehouse.getTaxamount()));
						} else {
							//发货单数量是负数，所以需要变成正的
							if(warehouse.getQuantity().floatValue() < 0) {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity().multiply(new BigDecimal(-1)));
							} else {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							}
							amountMap.put(warehouse.getProductid(), warehouse.getTaxamount());
						}
						
						warehouse.setUpdateuid(userid);
						warehouse.setApproverid(userid);
						warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
						
						//计算当前集集的库存数量
						count = count.add(warehouse.getQuantity());
						warehousenos += warehouse.getWarehouseno() + ",";
						productinfo += warehouse.getProductid() + "," + warehouse.getQuantity() + "," + warehouse.getTaxamount() + "," + StringUtil.getStr(warehouse.getRes09()) + "," + StringUtil.getStr(warehouse.getRes02()) + "#";
						
						//计算含税金额
						totaltaxamount = totaltaxamount.add(warehouse.getTaxamount());
						
						warehouseDao.updateWarehouse(warehouse);
						
						List<SalesItemDto> itemList = salesItemDao.querySalesItemBySalesno(warehouse.getParentid());
						if(itemList != null && itemList.size() > 0) {
							boolean b = true;
							//判断当前的采购单对应的货物是否都已入库：采购数量=入库数量
							for(SalesItemDto item : itemList) {
								if(item.getQuantity() != null && item.getQuantity().floatValue() > item.getOutquantity().floatValue()) {
									b = false;
									break;
								}
							}
							if(b) {
								//判断所有的库存记录均为已确认
								List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByParentid(warehouse.getParentid(), "");
								for(WarehouseDto warehouseDto : listWarehouse) {
									if(warehouseDto.getStatus() <= Constants.WAREHOUSE_STATUS_NEW) {
										b = false;
										break;
									}
								}
							}
							//以上2个条件均满足，则更新采购单状态
							if(b) {
								//需要更新销售单状态=已入库
								SalesDto salesDto = salesDao.querySalesByNo(warehouse.getParentid());
								salesDto.setStatus(Constants.SALES_STATUS_WAREHOUSE_OK);
								salesDto.setUpdateuid(userid);
								salesDao.updateSales(salesDto);
							} else {
								//需要更新销售单状态=部分入库
								SalesDto salesDto = salesDao.querySalesByNo(warehouse.getParentid());
								salesDto.setStatus(Constants.SALES_STATUS_WAREHOUSE_PART);
								salesDto.setUpdateuid(userid);
								salesDao.updateSales(salesDto);
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=出库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_OUT);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			
			//出库单号
			//String warehouseno = Constants.WAREHOUSERPT_OUT_NO_PRE + belongto + sdf.format(date);
			int newVal = 1;
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			String year = sdfYear.format(date);
			//根据出库单+年份查询出库单当前番号
			List<Dict01Dto> dictList = dict01Dao.queryDict01ByFieldcode(Constants.WAREHOUSERPT_OUT_NO_PRE + year, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			if(dictList != null && dictList.size() > 0) {
				Dict01Dto dict = dictList.get(0);
				//出库单番号+1
				newVal = Integer.valueOf(dict.getCode()) + 1;
				dict.setCode("" + newVal);
				//更新出库单番号
				dict01Dao.updateDict01(dict);
			} else {
				//新增出库单番号
				Dict01Dto dict = new Dict01Dto();
				dict.setCode("1");
				dict.setCreateuid("admin");
				dict.setUpdateuid("admin");
				dict.setFieldcode(Constants.WAREHOUSERPT_OUT_NO_PRE + year);
				dict.setFieldname(year + "出库单番号");
				dict.setNote(year + "出库单番号");
				dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
				dict.setMean(Constants.WAREHOUSERPT_OUT_NO_PRE + year);
				dict.setStatus(Constants.STATUS_NORMAL);
				dict01Dao.insertDict01(dict);
			}
			String warehouseno = Constants.WAREHOUSERPT_OUT_NO_PRE + belongto + year.substring(2, 4)+ StringUtil.replenishStr("" + newVal, 6);
			
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			warehouserpt.setWarehousename(warehousename);
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
			/*
			String productinfo = "";
			Set<?> key = quantityMap.keySet();
			for (Iterator<?> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				productinfo += k + "," + quantityMap.get(k) + "," + amountMap.get(k) + "#";
			} */
			warehouserpt.setProductinfo(productinfo);
			//入库单RPT日期
			warehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
			
			//入库数量
			if(count.floatValue() < 0) {
				count = count.multiply(new BigDecimal(-1));
			}
			warehouserpt.setTotalnum(count);
			
			//含税金额
			warehouserpt.setTotaltaxamount(totaltaxamount);
			//收货人
			warehouserpt.setHandler("");
			
			//查询客户信息
			CustomerDto customer = customerDao.queryEtbCustomerByID(customerid);
			//获得销售单的客户信息
			warehouserpt.setSupplierid(customerid);
			if(customer != null) {
				warehouserpt.setSuppliername(customer.getCustomername());
				warehouserpt.setSupplieraddress(customer.getCustomeraddress1());
				warehouserpt.setSuppliermail(customer.getCustomermail1());
				warehouserpt.setSuppliermanager(customer.getCustomermanager1());
				warehouserpt.setSuppliertel(customer.getCustomertel1());
				warehouserpt.setSupplierfax(customer.getCustomerfax1());
			}
			//快递公司ID==============================这里不做填充，等发货单时填充
			
			warehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
			warehouserpt.setStatus(Constants.FINANCE_STATUS_NEW);
			//入库单单号集合
			warehouserpt.setParentid(warehousenos);
			warehouserpt.setCreateuid(userid);
			warehouserpt.setUpdateuid(userid);
			
			warehouserptDao.insertWarehouserpt(warehouserpt);
			
			//新增一条财务记录（这里财务记录和出库单关联）
			FinanceDto finance = new FinanceDto();
			//类型=销售单
			finance.setFinancetype(Constants.FINANCE_TYPE_SALES);
			//销售单（收款）
			finance.setMode("1");
			finance.setBelongto(belongto);
			//单据号=出库单号
			finance.setInvoiceid(warehouseno);
			//发票号
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String receiptid = Constants.FINANCE_NO_PRE + belongto + sdf1.format(date);
			finance.setReceiptid(receiptid);
			//开票日期
			//finance.setReceiptdate(receiptdate);
			//结算日期=当天
			finance.setAccountdate(DateUtil.dateToShortStr(date));
			//金额=销售金额含税
			finance.setAmount(totaltaxamount);
			//负责人
			finance.setHandler(userid);
			//客户信息
			finance.setCustomerid(Long.valueOf(customerid));
			finance.setCustomername(customer.getCustomername());
			finance.setCustomertel(customer.getCustomertel1());
			finance.setCustomermanager(customer.getCustomermanager1());
			finance.setCustomeraddress(customer.getCustomeraddress1());
			finance.setCustomermail(customer.getCustomermail1());
			finance.setRank(Constants.ROLE_RANK_OPERATOR);
			//状态=开票申请
			finance.setStatus(Constants.FINANCE_STATUS_NEW);
			finance.setCreateuid(userid);
			finance.setUpdateuid(userid);
			financeDao.insertFinance(finance);
		}
	}
	
	@Override
	public Page queryWarehouseInOkByPage(String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseInOutOkCountByPage("" + Constants.WAREHOUSE_TYPE_IN,
				suppliername, theme, tradename, typeno, color, warehousename, status);
		page.setTotalCount(totalCount);
		System.out.println("totalCount: " + totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		
		//翻页查询记录
		List<WarehouseInOutOkDto> list = new ArrayList<WarehouseInOutOkDto>();
		list = warehouseDao.queryWarehouseInOkByPage("" + Constants.WAREHOUSE_TYPE_IN, suppliername, theme, tradename,
				typeno, color, warehousename, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryWarehouseOutOkByPage(String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseOutOkCountByPage("" + Constants.WAREHOUSE_TYPE_OUT,
				suppliername, theme, tradename, typeno, color, warehousename, status);
		page.setTotalCount(totalCount);
		System.out.println("totalCount: " + totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		
		//翻页查询记录
		List<WarehouseInOutOkDto> list = new ArrayList<WarehouseInOutOkDto>();
		list = warehouseDao.queryWarehouseOutOkByPage("" + Constants.WAREHOUSE_TYPE_OUT, suppliername, theme, tradename,
				typeno, color, warehousename, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}
	
	@Override
	public Page queryWarehouseOkByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseOkCountByPage(warehouseType,
				theme, tradename, typeno, color, warehousename, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		
		//翻页查询记录
		List<WarehouseOkDto> list = new ArrayList<WarehouseOkDto>();
		list = warehouseDao.queryWarehouseOkByPage(warehouseType, theme, tradename,
				typeno, color, warehousename, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid,
			String productid, String status, String warehousename, Page page) {
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseCountByPage(parentid, warehousetype, warehouseno,
				theme1, supplierid, productid, status, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDto> list = warehouseDao.queryWarehouseByPage(parentid, warehousetype,
				warehouseno, theme1, supplierid, productid, status, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public WarehouseDto queryWarehouseByID(String id) {
		WarehouseDto warehouse = warehouseDao.queryWarehouseByID(id);
		if(warehouse != null) {
			ProductDto product = productDao.queryProductByID(warehouse.getProductid());
			if(product != null) {
				warehouse.setProductname(product.getTradename());
				warehouse.setTypeno(product.getTypeno());
				warehouse.setColor(product.getColor());
				warehouse.setPackaging(product.getPackaging());
				warehouse.setUnit(product.getUnit());
				warehouse.setItem10(product.getItem10());
			}
		}
		return warehouse;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByIds(String ids) {
		return warehouseDao.queryWarehouseByIds(ids);
	}

	@Override
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid,
			String productid) {
		return warehouseDao.queryWarehouseByParentidAndProductid(parentid, productid);
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByParentid(String parentid) {
		return warehouseDao.queryWarehouseByParentid(parentid, "");
	}

	@Override
	public WarehouseDto queryWarehouseByWarehouseno(String warehouseno) {
		return warehouseDao.queryWarehouseByWarehouseno(warehouseno);
	}

	@Override
	public void insertWarehouse(WarehouseDto warehouse) {
		warehouseDao.insertWarehouse(warehouse);
	}
	
	@Override
	public String insertRefundWarehouse(WarehouseDto warehouse) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		//库存单号
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		String warehouseno = Constants.WAREHOUSE_NO_PRE + belongto + sdf.format(date) + uuid;
		
		warehouse.setWarehouseno(warehouseno);
		warehouse.setWarehousename(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_WAREHOUSE_NAME));
		ProductDto product = productDao.queryProductByID(warehouse.getProductid());
		//产品信息
		warehouse.setTheme1(product.getFieldno());
		warehouse.setWarehousedate(DateUtil.dateToShortStr(date));
		warehouse.setPlandate(DateUtil.dateToShortStr(date));
		warehouse.setHandler(warehouse.getUpdateuid());
		warehouse.setSupplierid(product.getSupplierid());
		warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
		warehouse.setStatus(Constants.FINANCE_STATUS_PAY_INVOICE);
		
		warehouseDao.insertWarehouse(warehouse);
		return warehouseno;
	}

	@Override
	public void updateWarehouse(WarehouseDto warehouse) {
		warehouseDao.updateWarehouse(warehouse);
	}

	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	public WarehouserptDao getWarehouserptDao() {
		return warehouserptDao;
	}

	public void setWarehouserptDao(WarehouserptDao warehouserptDao) {
		this.warehouserptDao = warehouserptDao;
	}

	public SupplierDao getSupplierDao() {
		return supplierDao;
	}

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
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

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public Page queryWarehouseDetailByPage(String parentid, String keyword,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename, Page page) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseDetailCountByPage(parentid, keyword, 
				warehousetype, warehouseno, theme1, productid, tradename, typeno, color, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDetailDto> list = warehouseDao.queryWarehouseDetailByPage(parentid, keyword,
				warehousetype, warehouseno, theme1, productid, tradename, typeno, color, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public List<WarehouseCheckDto> queryWarehouseCheckToExcel(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename) {
		return warehouseDao.queryWarehouseCheckToExcel(parentid, warehousetype, warehouseno, theme1, productid, tradename, typeno, color, warehousename);
	}
	
	@Override
	public List<WarehouseOkDto> queryProductBookByProductid(String productid) {
		return warehouseDao.queryProductBookByProductid(productid);
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
