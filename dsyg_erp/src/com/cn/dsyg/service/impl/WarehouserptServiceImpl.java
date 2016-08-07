package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.SalesDto;
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
	private SalesDao salesDao;
	private Dict01Dao dict01Dao;
	private FinanceDao financeDao;
	
	@Override
	public void approveWarehouserpt(String id, String userid, String res10,
			String receiptdate, String status) {
		WarehouserptDto warehouserpt = warehouserptDao.queryWarehouserptByID(id);
		if(warehouserpt != null) {
			warehouserpt.setUpdateuid(userid);
			//更新采购单状态
			warehouserpt.setStatus(Integer.valueOf(status));
			warehouserptDao.updateWarehouserpt(warehouserpt);
			
			//更新财务数据状态
			FinanceDto finance = financeDao.queryFinanceByInvoiceid(warehouserpt.getWarehouseno(), "");
			if(finance != null) {
				//审核开票日期1-3
				finance.setRes09(receiptdate);
				//发票号
				finance.setRes10(res10);
				//确认者=当前用户
				finance.setApproverid(userid);
				finance.setStatus(Integer.valueOf(status));
				financeDao.updateFinance(finance);
			}
			
			//状态=99时，更新warehouse表数据
			if(status.equals("" + Constants.FINANCE_STATUS_PAY_INVOICE)) {
				String parentid = warehouserpt.getParentid();
				if(StringUtil.isNotBlank(parentid)) {
					String[] ids = parentid.split(",");
					for(String warehouseno : ids) {
						if(StringUtil.isNotBlank(warehouseno)) {
							WarehouseDto warehouse = warehouseDao.queryWarehouseByWarehouseno(warehouseno);
							if(warehouse != null) {
								warehouse.setStatus(Constants.WAREHOUSE_STATUS_FINISHED);
								warehouseDao.updateWarehouse(warehouse);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void approveWarehouserpt(String id, String userid, String status) {
		WarehouserptDto warehouserpt = warehouserptDao.queryWarehouserptByID(id);
		if(warehouserpt != null) {
			warehouserpt.setUpdateuid(userid);
			//更新采购单状态
			warehouserpt.setStatus(Integer.valueOf(status));
			warehouserptDao.updateWarehouserpt(warehouserpt);
			
			//更新财务数据状态
			FinanceDto finance = financeDao.queryFinanceByInvoiceid(warehouserpt.getWarehouseno(), "");
			if(finance != null) {
				if(status.equals("" + Constants.FINANCE_STATUS_PAY_INVOICE)) {
					//开票日期=当天
					//finance.setReceiptdate(DateUtil.dateToShortStr(new Date()));
				}
				//确认者=当前用户
				finance.setApproverid(userid);
				finance.setStatus(Integer.valueOf(status));
				financeDao.updateFinance(finance);
			}
			
			//状态=99时，更新warehouse表数据
			if(status.equals("" + Constants.FINANCE_STATUS_PAY_INVOICE)) {
				String parentid = warehouserpt.getParentid();
				if(StringUtil.isNotBlank(parentid)) {
					String[] ids = parentid.split(",");
					for(String warehouseno : ids) {
						if(StringUtil.isNotBlank(warehouseno)) {
							WarehouseDto warehouse = warehouseDao.queryWarehouseByWarehouseno(warehouseno);
							if(warehouse != null) {
								warehouse.setStatus(Constants.WAREHOUSE_STATUS_FINISHED);
								warehouseDao.updateWarehouse(warehouse);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public List<WarehouserptDto> queryAllWarehouserptToExport(String status,
			String warehousetype, String warehouseno, String theme1,
			String parentid, String supplierid, String productid) {
		//TODO
		List<WarehouserptDto> listWarehouserpt = warehouserptDao.queryAllWarehouserptToExport(status, warehousetype, warehouseno, theme1, parentid, supplierid, productid);
		if(listWarehouserpt != null && listWarehouserpt.size() > 0) {
			for(WarehouserptDto rpt : listWarehouserpt) {
				//查询对应的库存记录列表
				if(StringUtil.isNotBlank(rpt.getProductinfo()) && StringUtil.isNotBlank(rpt.getParentid())
						&& (rpt.getParentid().split(",").length == rpt.getProductinfo().split("#").length)) {
					//若产品信息数据正常，则按正常数据查询
					List<ProductDto> list = new ArrayList<ProductDto>();
					String[] infos = rpt.getProductinfo().split("#");
					String[] parents = rpt.getParentid().split(",");
					ProductDto product = null;
					WarehouseDto warehouse = null;
					for(int i = 0; i < infos.length; i++) {
						String info = infos[i];
						if(StringUtil.isNotBlank(info) && !"null".equalsIgnoreCase(info)) {
							String[] ll = info.split(",");
							product = productDao.queryProductByID(ll[0]);
							if(product != null) {
								//货物数量
								product.setNum(ll[1]);
								//货物金额
								product.setAmount(ll[2]);
								//RES09 特殊订单号
								if (ll.length > 3){
									if (StringUtil.isNotBlank(ll[3]))
										product.setRes09(ll[3]);									
								}
								product.setHasbroken("0");
								product.setBrokennum("0");
								list.add(product);
							}
						} else {
							//尝试根据父单号查询
							warehouse = warehouseDao.queryWarehouseByWarehouseno(parents[i]);
							if(warehouse != null) {
								product = productDao.queryProductByID(warehouse.getProductid());
								//货物数量
								product.setNum("" + warehouse.getQuantity());
								//货物金额
								product.setAmount("" + warehouse.getTaxamount());
								//RES09 特殊订单号
								product.setRes09(StringUtil.getStr(warehouse.getRes09()));
								product.setHasbroken("0");
								product.setBrokennum("0");
								list.add(product);
							}
						}
					}
					rpt.setListProduct(list);
				} else {
					//查询对应的库存记录列表
					//if(StringUtil.isNotBlank(rpt.getProductinfo())) {
					//这里全部根据父单号来查询
					if(StringUtil.isNotBlank(rpt.getParentid())) {
						List<ProductDto> list = new ArrayList<ProductDto>();
						//String[] infos = rpt.getProductinfo().split("#");
						String[] parents = rpt.getParentid().split(",");
						//for(String info : infos) {
						WarehouseDto warehouse = null;
						ProductDto product = null;
						for(String pid : parents) {
							if(StringUtil.isNotBlank(pid)) {
								//String[] ll = info.split(",");
								//查询库存记录
								warehouse = warehouseDao.queryWarehouseByWarehouseno(pid);
								if(warehouse != null) {
									//查询产品数据
									product = productDao.queryProductByID(warehouse.getProductid());
									if(product != null) {
										//货物数量
										product.setNum("" + warehouse.getQuantity());
										//货物金额
										product.setAmount("" + warehouse.getTaxamount());
										//RES09 特殊订单号
										product.setRes09(StringUtil.getStr(warehouse.getRes09()));									
										product.setHasbroken("0");
										product.setBrokennum("0");
										list.add(product);
									}
								} else {
									//由于库存记录不存在（这里是因为双浏览器操作导致库存记录消失），这里就什么都不做。
								}
							}
						}
						rpt.setListProduct(list);
					}
				}
			}
		}
		return listWarehouserpt;
	}

	@Override
	public Page queryWarehouserptByPage(String no, String status, String warehousetype,
			String warehouseno, String theme1, String parentid, String supplierid,
			String productid, String beginDate, String endDate, String strSuppliername,
			String strWarehouseno, String createdateLow, String createdateHigh, Page page) {
		strSuppliername = StringUtil.replaceDatabaseKeyword_mysql(strSuppliername);
		if(StringUtil.isNotBlank(no)) {
			List<WarehouserptDto> listAll = new ArrayList<WarehouserptDto>();
			List<WarehouserptDto> listTemp = new ArrayList<WarehouserptDto>();
			List<WarehouserptDto> list = new ArrayList<WarehouserptDto>();
			//根据采购单OR订单模糊查询RPT数据
			List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByTheme2(warehousetype, no);
			if(listWarehouse != null && listWarehouse.size() > 0) {
				//查询RPT记录
				for(WarehouseDto warehouse : listWarehouse) {
					List<WarehouserptDto> ll = warehouserptDao.queryWarehouserptByWarehouse(warehousetype, strWarehouseno,
							warehouse.getWarehouseno(), strSuppliername, createdateLow, createdateHigh);
					if(ll != null) {
						listTemp.addAll(ll);
					}
				}
				//合并相同的记录，保证同一个RPT只有一条记录
				Map<String, WarehouserptDto> map = new LinkedHashMap<String, WarehouserptDto>();
				for(WarehouserptDto rpt : listTemp) {
					if(map.get(rpt.getWarehouseno()) == null) {
						map.put(rpt.getWarehouseno(), rpt);
					}
				}
				for(Map.Entry<String, WarehouserptDto> entry : map.entrySet()) {
					listAll.add(entry.getValue());
				}
				
				page.setTotalCount(listAll.size());
				//手动分页
				if(listAll.size() > page.getStartIndex() * page.getPageSize()) {
					for(int i = page.getStartIndex() * page.getPageSize(); i < page.getStartIndex() * page.getPageSize() + page.getPageSize(); i++) {
						if(i < listAll.size()) {
							list.add(listAll.get(i));
						}
					}
				}
				if(list != null && list.size() > 0) {
					FinanceDto finance = null;
					for(WarehouserptDto rpt : list) {
						//查询财务记录的发票
						finance = financeDao.queryFinanceByInvoiceid(rpt.getWarehouseno(), "" + rpt.getWarehousetype());
						if(finance != null) {
							rpt.setFinanceBillno(finance.getRes10());
						}
					}
				}
				page.setItems(list);
			}
			return page;
		} else {
			//查询总记录数
			int totalCount = warehouserptDao.queryWarehouserptCountByPage(status, warehousetype,
					warehouseno, theme1, parentid, supplierid, productid, beginDate, endDate, strSuppliername,
					strWarehouseno, createdateLow, createdateHigh);
			page.setTotalCount(totalCount);
			if(totalCount % page.getPageSize() > 0) {
				page.setTotalPage(totalCount / page.getPageSize() + 1);
			} else {
				page.setTotalPage(totalCount / page.getPageSize());
			}
			//翻页查询记录
			List<WarehouserptDto> list = warehouserptDao.queryWarehouserptByPage(status, warehousetype,
					warehouseno, theme1, parentid, supplierid, productid, beginDate, endDate, strSuppliername,
					strWarehouseno, createdateLow, createdateHigh,
					page.getStartIndex() * page.getPageSize(), page.getPageSize());
			if(list != null && list.size() > 0) {
				FinanceDto finance = null;
				for(WarehouserptDto rpt : list) {
					//查询财务记录的发票
					finance = financeDao.queryFinanceByInvoiceid(rpt.getWarehouseno(), "" + rpt.getWarehousetype());
					if(finance != null) {
						rpt.setFinanceBillno(finance.getRes10());
					}
				}
			}
			page.setItems(list);
			return page;
		}
	}
	
	@Override
	public WarehouserptDto queryWarehouserptByID(String id) {
		WarehouserptDto rpt = warehouserptDao.queryWarehouserptByID(id);
		//TODO
		if(rpt != null) {
			//查询对应的库存记录列表
			if(StringUtil.isNotBlank(rpt.getProductinfo()) && StringUtil.isNotBlank(rpt.getParentid())) {
				List<ProductDto> list = new ArrayList<ProductDto>();
				String[] infos = rpt.getProductinfo().split("#");
				String[] parents = rpt.getParentid().split(",");
				if(infos.length == parents.length) {
					Map<String, ProductDto> map = new LinkedHashMap<String, ProductDto>();
					for(int i=0; i<infos.length; i++) {
						if(StringUtil.isNotBlank(infos[i])) {
							String[] ll = infos[i].split(",");
							
							String key = "";
							
							WarehouseDto ww = warehouseDao.queryWarehouseByWarehouseno(parents[i]);
							//KEY=产品ID_销售单ID_含税单价，同一个销售单含税单价有可能不一样。
							BigDecimal taxprice = new BigDecimal(0);
							if(ww != null) {
								if(StringUtil.isNotBlank(ww.getRes02())) {
									taxprice = new BigDecimal(ww.getRes02()).setScale(6, BigDecimal.ROUND_HALF_UP);;
								}
								key = ll[0] + "_" + ww.getParentid() + "_" + taxprice;
							} else {
								//由于库存记录不存在（这里是因为双浏览器操作导致库存记录消失），所以就用库存no来作为KEY的一部分
								key = ll[0] + "_" + parents[i] + "_" + taxprice;
							}
							
							if(map.get(key) != null) {
								//存在该订单的货物记录，需要合并
								ProductDto pp = map.get(key);
								//数量
								BigDecimal nn = new BigDecimal(pp.getNum());
								BigDecimal n = new BigDecimal(ll[1]);
								//金额
								BigDecimal aa = new BigDecimal(pp.getAmount());
								BigDecimal a = new BigDecimal(ll[2]);
								
								//RES09 特殊订单号
								String res09 = "";
								if(StringUtil.isNotBlank(pp.getRes09())) {
									res09 += pp.getRes09() + ",";
								}
								if(ll.length > 3 && StringUtil.isNotBlank(ll[3])) {
									res09 += ll[3] + ",";
								}
								if(StringUtil.isNotBlank(res09)) {
									res09 = res09.substring(0, res09.length() - 1);
								}
								pp.setRes09(res09);
								
								pp.setNum("" + nn.add(n));
								pp.setAmount("" + aa.add(a));
								pp.setNumabs(StringUtil.BigDecimal2StrAbs(nn.add(n), 2));
								map.put(key, pp);
							} else {
								//没有该货物记录，则直接添加到MAP中
								ProductDto product = productDao.queryProductByID(ll[0]);
								WarehouseDto warehouse = warehouseDao.queryWarehouseByWarehouseno(parents[i]);
								String parentid = "";
								
								if(warehouse != null) {
									if(rpt.getWarehousetype() == 1){
										PurchaseDto purchase = purchaseDao.queryPurchaseByNo(warehouse.getParentid());
										parentid = purchase == null ? "" : purchase.getTheme2();
									} else {
										SalesDto sales = salesDao.querySalesByNo(warehouse.getParentid());
										parentid = sales == null ? "" : sales.getTheme2();
									}
								}
								if(product != null) {
									//货物数量
									product.setNum(ll[1]);
									BigDecimal num = new BigDecimal(ll[1]);
									product.setNumabs(StringUtil.BigDecimal2StrAbs(num, 2));
									//货物金额
									product.setAmount(ll[2]);
									//RES09 特殊订单号
									if (ll.length > 3){	
										if (StringUtil.isNotBlank(ll[3]))
											product.setRes09(ll[3]);
									}
									//含税单价
									if (ll.length > 4){	
										if (StringUtil.isNotBlank(ll[4]))
											product.setUnitprice((ll[4]));
									}
									product.setHasbroken("0");
									product.setBrokennum("0");
									product.setParentid(parentid);
									map.put(key, product);
								}
							}
						}
					}
					
					//对入出库单有相同货物ID的进行合并
					for(Map.Entry<String, ProductDto> entry : map.entrySet()) {
						//去掉数量为0的货物
						BigDecimal num = new BigDecimal(entry.getValue().getNum());
						BigDecimal zero = new BigDecimal(0);
						if(zero.compareTo(num) != 0) {
							list.add(entry.getValue());
						}
					}
					rpt.setListProduct(list);
				} else {
					//直接根据父单号查询数据
					Map<String, ProductDto> map = new LinkedHashMap<String, ProductDto>();
					for(int i = 0; i < parents.length; i++) {
						if(StringUtil.isNotBlank(parents[i])) {
							String key = "";
							
							WarehouseDto ww = warehouseDao.queryWarehouseByWarehouseno(parents[i]);
							//KEY=产品ID_销售单ID_含税单价，同一个销售单含税单价有可能不一样。
							BigDecimal taxprice = new BigDecimal(0);
							if(ww != null) {
								if(StringUtil.isNotBlank(ww.getRes02())) {
									taxprice = new BigDecimal(ww.getRes02()).setScale(6, BigDecimal.ROUND_HALF_UP);;
								}
								key = "" + ww.getProductid() + "_" + ww.getParentid() + "_" + taxprice;
								
								if(map.get(key) != null) {
									//存在该订单的货物记录，需要合并
									ProductDto pp = map.get(key);
									//数量
									BigDecimal nn = new BigDecimal(pp.getNum());
									BigDecimal n = ww.getQuantity();
									//金额
									BigDecimal aa = new BigDecimal(pp.getAmount());
									BigDecimal a = ww.getTaxamount();
									
									//RES09 特殊订单号
									pp.setRes09(StringUtil.getStr(ww.getRes09()));
									
									pp.setNum("" + nn.add(n));
									pp.setAmount("" + aa.add(a));
									pp.setNumabs(StringUtil.BigDecimal2StrAbs(nn.add(n), 2));
									map.put(key, pp);
								} else {
									//没有该货物记录，则直接添加到MAP中
									ProductDto product = productDao.queryProductByID(ww.getProductid());
									String parentid = "";
									
									if(rpt.getWarehousetype() == 1){
										PurchaseDto purchase = purchaseDao.queryPurchaseByNo(ww.getParentid());
										parentid = purchase == null ? "" : purchase.getTheme2();
									} else {
										SalesDto sales = salesDao.querySalesByNo(ww.getParentid());
										parentid = sales == null ? "" : sales.getTheme2();
									}
									if(product != null) {
										//货物数量
										product.setNum("" + ww.getQuantity());
										BigDecimal num = ww.getQuantity();
										product.setNumabs(StringUtil.BigDecimal2StrAbs(num, 2));
										//货物金额
										product.setAmount("" + ww.getTaxamount());
										//RES09 特殊订单号
										product.setRes09(StringUtil.getStr(ww.getRes09()));
										//含税单价
										product.setUnitprice(StringUtil.getStr(ww.getRes02()));
										product.setHasbroken("0");
										product.setBrokennum("0");
										product.setParentid(parentid);
										map.put(key, product);
									}
								}
							} else {
								//由于库存记录不存在（这里是因为双浏览器操作导致库存记录消失），这里就什么都不做。
							}
						}
					}
					
					//对入出库单有相同货物ID的进行合并
					for(Map.Entry<String, ProductDto> entry : map.entrySet()) {
						//去掉数量为0的货物
						BigDecimal num = new BigDecimal(entry.getValue().getNum());
						BigDecimal zero = new BigDecimal(0);
						if(zero.compareTo(num) != 0) {
							list.add(entry.getValue());
						}
					}
					rpt.setListProduct(list);
				}
			}
		}
		return rpt;
	}
	
	@Override
	public WarehouserptDto queryWarehouserptInterByID(String id) {
		WarehouserptDto rpt = warehouserptDao.queryWarehouserptByID(id);
		if(rpt != null) {
			//TODO
			//查询对应的库存记录列表
			if(StringUtil.isNotBlank(rpt.getProductinfo()) && StringUtil.isNotBlank(rpt.getParentid())
					&& (rpt.getParentid().split(",").length == rpt.getProductinfo().split("#").length)) {
				List<ProductDto> list = new ArrayList<ProductDto>();
				String[] infos = rpt.getProductinfo().split("#");
				for(int i = 0; i < infos.length; i++) {
					if(StringUtil.isNotBlank(infos[i])) {
						String[] ll = infos[i].split(",");
						ProductDto product = productDao.queryProductByID(ll[0]);
						if(product != null) {
							boolean isInlist = false;
							int index = 0;
							for (int j = 0; j < list.size(); j++) { 
								if(list.get(j).getId() == Long.parseLong(ll[0])){
									isInlist = true;
									index = j;
								}
							}
							if(isInlist){
								//货物数量
								BigDecimal num = new BigDecimal(list.get(index).getNum());
								BigDecimal num_new = new BigDecimal(ll[1]);
								list.get(index).setNum(num.add(num_new).toString());
								//list.get(index).setNum(String.valueOf(Integer.parseInt(list.get(index).getNum()) + Integer.parseInt(ll[1])));
								//货物金额
								list.get(index).setAmount(String.valueOf(Double.parseDouble(list.get(index).getAmount()) + Double.parseDouble(ll[2])));
								//RES09 特殊订单号
								if (ll.length > 3){	
									if (StringUtil.isNotBlank(ll[3]))
										list.get(index).setRes09(ll[3]);
								}
								//税后单价
//								if (ll.length > 4){	
//									if (StringUtil.isNotBlank(ll[4]))
//										product.setRes09(ll[4]);
//								}
							} else {
								//货物数量
								product.setNum(ll[1]);
								//货物金额
								product.setAmount(ll[2]);
								//RES09 特殊订单号
								if (ll.length > 3){	
									if (StringUtil.isNotBlank(ll[3]))
										product.setRes09(ll[3]);
								}
								//税后单价
//								if (ll.length > 4){	
//									if (StringUtil.isNotBlank(ll[4]))
//										product.setRes09(ll[4]);
//								}
								product.setHasbroken("0");
								product.setBrokennum("0");
								list.add(product);
							}
						}
					}
				}
				rpt.setListProduct(list);
			} else {
				//根据父单号查询
				List<ProductDto> list = new ArrayList<ProductDto>();
				String[] parents = rpt.getParentid().split(",");
				WarehouseDto ww = null;
				for(int i = 0; i < parents.length; i++) {
					if(StringUtil.isNotBlank(parents[i])) {
						ww = warehouseDao.queryWarehouseByWarehouseno(parents[i]);
						if(ww != null) {
							ProductDto product = productDao.queryProductByID(ww.getProductid());
							if(product != null) {
								boolean isInlist = false;
								int index = 0;
								for (int j = 0; j < list.size(); j++) { 
									if(list.get(j).getId() == Long.parseLong(ww.getProductid())){
										isInlist = true;
										index = j;
									}
								}
								if(isInlist) {
									//货物数量
									BigDecimal num = new BigDecimal(list.get(index).getNum());
									BigDecimal num_new = ww.getQuantity();
									list.get(index).setNum(num.add(num_new).toString());
									//list.get(index).setNum(String.valueOf(Integer.parseInt(list.get(index).getNum()) + Integer.parseInt(ll[1])));
									//货物金额
									list.get(index).setAmount(String.valueOf(Double.parseDouble(list.get(index).getAmount()) + Double.parseDouble("" + ww.getTaxamount())));
									//RES09 特殊订单号
									list.get(index).setRes09(StringUtil.getStr(ww.getRes09()));
								} else {
									//货物数量
									product.setNum("" + ww.getQuantity());
									//货物金额
									product.setAmount("" + ww.getTaxamount());
									//RES09 特殊订单号
									product.setRes09(StringUtil.getStr(ww.getRes09()));
									//税后单价
									product.setHasbroken("0");
									product.setBrokennum("0");
									list.add(product);
								}
							}
						} else {
							//由于库存记录不存在（这里是因为双浏览器操作导致库存记录消失），这里就什么都不做。
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
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(warehouserpt.getListProduct() != null && warehouserpt.getListProduct().size() > 0) {
			String productinfo = "";
			for(ProductDto product : warehouserpt.getListProduct()) {
				//判断是否需要添加入库记录
				if("1".equals(product.getHasbroken())) {
					//需要新增一条新的入出库记录
					WarehouseDto warehouse = new WarehouseDto();
					//数据来源单号=当前入库单号
					warehouse.setParentid(warehouserpt.getWarehouseno());
					//类型
					warehouse.setWarehousetype(type);
					//入库单号
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.substring(uuid.length() - 8, uuid.length());
					String warehouseno = Constants.WAREHOUSE_NO_PRE + belongto + sdf.format(date) + uuid;
					warehouse.setWarehouseno(warehouseno);
					
					warehouse.setProductid("" + product.getId());
					
					//仓库名===========？
					//warehouse.setWarehousename(warehousename);
					//单价
					if(StringUtil.isBlank(product.getPurchaseprice())) {
						//默认为0
						warehouse.setUnitprice(new BigDecimal(0));
					} else {
						warehouse.setUnitprice(new BigDecimal(product.getPurchaseprice()));
					}
					
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
						taxamount = new BigDecimal(product.getBrokennum()).multiply(warehouse.getUnitprice());
						amount = new BigDecimal(product.getBrokennum()).multiply(warehouse.getUnitprice());
						//入出库数量=损毁数
						//入库单，数量为负数
						warehouse.setQuantity(new BigDecimal(product.getBrokennum()).multiply(new BigDecimal(-1)));
					} else {
						//销售单价
						taxamount = new BigDecimal(product.getBrokennum()).multiply(warehouse.getUnitprice());
						amount = new BigDecimal(product.getBrokennum()).multiply(warehouse.getUnitprice());
						//出库单，数量为正数
						warehouse.setQuantity(new BigDecimal(product.getBrokennum()));
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
					//入库单数据状态=预入出库确认
					warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
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
					
					//退换货单号
					//SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");
					//String warehouserptno = Constants.WAREHOUSERPT_REFUND_NO_PRE + belongto + sdf1.format(date);
					int newVal = 1;
					SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
					String year = sdfYear.format(date);
					//根据退换货+年份查询退换货当前番号
					List<Dict01Dto> dictList = dict01Dao.queryDict01ByFieldcode(Constants.WAREHOUSERPT_REFUND_NO_PRE + year, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
					if(dictList != null && dictList.size() > 0) {
						Dict01Dto dict = dictList.get(0);
						//退换货番号+1
						newVal = Integer.valueOf(dict.getCode()) + 1;
						dict.setCode("" + newVal);
						//更新退换货番号
						dict01Dao.updateDict01(dict);
					} else {
						//新增退换货番号
						Dict01Dto dict = new Dict01Dto();
						dict.setCode("1");
						dict.setCreateuid("admin");
						dict.setUpdateuid("admin");
						dict.setFieldcode(Constants.WAREHOUSERPT_REFUND_NO_PRE + year);
						dict.setFieldname(year + "退换货番号");
						dict.setNote(year + "退换货番号");
						dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
						dict.setMean(Constants.WAREHOUSERPT_REFUND_NO_PRE + year);
						dict.setStatus(Constants.STATUS_NORMAL);
						dict01Dao.insertDict01(dict);
					}
					String warehouserptno = Constants.WAREHOUSERPT_REFUND_NO_PRE + belongto + year.substring(2, 4) + StringUtil.replenishStr("" + newVal, 6);
					
					newwarehouserpt.setWarehouseno(warehouserptno);
					
					//货物信息：产品ID,产品数量,产品金额#产品ID,产品数量,产品金额
					newwarehouserpt.setProductinfo("" + product.getId() + "," + product.getBrokennum() + "," + taxamount + "," + product.getRes09() + "#");
					
					//主题
					newwarehouserpt.setTheme1(product.getFieldno());
					//入出库单RPT日期
					newwarehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
					//入库数量，这里不需要乘以-1
					newwarehouserpt.setTotalnum(new BigDecimal(product.getBrokennum()));
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
					
					productinfo += "" + product.getId() + "," + num + "," + bb + "," + product.getRes09() + "#";
				} else {
					productinfo += "" + product.getId() + "," + product.getNum() + "," + product.getAmount() + "," + product.getRes09() + "#";
				}
			}
			warehouserpt.setProductinfo(productinfo);
		}
		warehouserpt.setRes01(warehouserpt.getReceiptdate());
		warehouserptDao.updateWarehouserpt(warehouserpt);
		
		//快递信息
		//判断是否有对应的财务记录
		FinanceDto ff = financeDao.queryFinanceByInvoiceid(warehouserpt.getWarehouseno(), "" + Constants.FINANCE_TYPE_DELIVERY);
		if(ff == null) {
			//新增一条财务记录
			FinanceDto finance = new FinanceDto();
			//类型=快递
			finance.setFinancetype(Constants.FINANCE_TYPE_DELIVERY);
			if(type == Constants.WAREHOUSE_TYPE_IN) {
				//入库单（付款）
				finance.setMode("2");
			} else {
				//出库单（收款）
				finance.setMode("1");
			}
			finance.setBelongto(belongto);
			//单据号=入出库单号
			finance.setInvoiceid(warehouserpt.getWarehouseno());
			//发票号
			String receiptid = Constants.FINANCE_NO_PRE + belongto + sdf.format(date);
			finance.setReceiptid(receiptid);
			//开票日期=当天
			//finance.setReceiptdate(DateUtil.dateToShortStr(date));
			//结算日期=当天
			finance.setAccountdate(DateUtil.dateToShortStr(date));
			//金额=快递金额含税
			finance.setAmount(warehouserpt.getExpresstaxamount());
			//负责人
			finance.setHandler(warehouserpt.getUpdateuid());
			finance.setApproverid(warehouserpt.getUpdateuid());
			
			//快递信息需要显示对应的客户/供应商的ID和名称
			finance.setRes01(warehouserpt.getSupplierid());
			finance.setRes02(warehouserpt.getSuppliername());
			
			finance.setReceiptdate(warehouserpt.getReceiptdate());
			finance.setRes08(warehouserpt.getExpressno());
			finance.setCustomerid(Long.valueOf(warehouserpt.getExpressid()));
			finance.setCustomername(warehouserpt.getExpressname());
			finance.setCustomertel(warehouserpt.getExpresstel());
			finance.setCustomermanager(warehouserpt.getExpressmanager());
			finance.setCustomeraddress(warehouserpt.getExpressaddress());
			finance.setCustomermail(warehouserpt.getExpressmail());
			finance.setRank(Constants.ROLE_RANK_OPERATOR);
			//状态=新增
			finance.setStatus(Constants.FINANCE_STATUS_NEW);
			finance.setCreateuid(warehouserpt.getUpdateuid());
			finance.setUpdateuid(warehouserpt.getUpdateuid());
			financeDao.insertFinance(finance);
		} else {
			//修改财务记录
			//结算日期=当天
			ff.setAccountdate(DateUtil.dateToShortStr(date));
			//金额=快递金额含税
			ff.setAmount(warehouserpt.getExpresstaxamount());
			//负责人
			ff.setHandler(warehouserpt.getUpdateuid());
			//快递信息
			ff.setRes01(warehouserpt.getSupplierid());
			ff.setRes02(warehouserpt.getSuppliername());
			ff.setReceiptdate(warehouserpt.getReceiptdate());
			ff.setRes08(warehouserpt.getExpressno());
			ff.setCustomerid(Long.valueOf(warehouserpt.getExpressid()));
			ff.setCustomername(warehouserpt.getExpressname());
			ff.setCustomertel(warehouserpt.getExpresstel());
			ff.setCustomermanager(warehouserpt.getExpressmanager());
			ff.setCustomeraddress(warehouserpt.getExpressaddress());
			ff.setCustomermail(warehouserpt.getExpressmail());
			ff.setUpdateuid(warehouserpt.getUpdateuid());
			financeDao.updateFinance(ff);
		}
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

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public SalesDao getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}
}
