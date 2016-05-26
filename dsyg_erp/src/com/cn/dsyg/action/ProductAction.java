package com.cn.dsyg.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.FileUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FeatureDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.ProductService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name ProductAction.java
 * @author Frank
 * @time 2015-5-18下午9:58:25
 * @version 1.0
 */
public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 4977406296240495384L;
	private static final Logger log = LogManager.getLogger(ProductAction.class);
	
	private ProductService productService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的产品列表
	private List<ProductDto> productList;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//电线特征列表
	private List<FeatureDto> featureList01;
	//套管特征列表
	private List<FeatureDto> featureList02;
	
	private String strFieldno;
	private String strItem10;//包装
	private String strKeyword;
	private String strSupplierId;
	private String strTradename;
	private String strTypeno;
	private String strColor;
	private String strSeq;
	private String strPackaging;

	//1为采购单，2为销售单
	private String strFlag;
	
	//新增
	private ProductDto addProductDto;
	private File addPicFile01;
	private File addPicFile02;
	private File addPicFile03;
	private File addPdfFile;
	//对应的文件名
	private String file01Name;
	private String file02Name;
	private String file03Name;
	private String file04Name;
	
	//修改
	private String updProductId;
	private ProductDto updProductDto;
	private File updPicFile01;
	private File updPicFile02;
	private File updPicFile03;
	private File updPdfFile;
	
	//删除
	private String delProductId;
	
	/**
	 * 删除数据
	 * @return
	 */
	public String delProductAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			productService.deleteProduct(delProductId, username);
			this.addActionMessage("删除数据成功！");
			//刷新页面数据
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showUpdProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改产品页面
	 * @return
	 */
	public String showUpdProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updPicFile01 = null;
			updPicFile02 = null;
			updPicFile03 = null;
			updPdfFile = null;
			file01Name = "";
			file02Name = "";
			file03Name = "";
			file04Name = "";
			updProductDto = productService.queryProductByID(updProductId);
		} catch(Exception e) {
			log.error("showUpdProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改产品
	 * @return
	 */
	public String updProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(updProductDto)) {
				return "checkerror";
			}
			//判断逻辑主键是否唯一
			ProductDto pro = productService.queryProductByLogicId(updProductDto.getTradename(),
					updProductDto.getTypeno(), updProductDto.getColor(), updProductDto.getItem10(),updProductDto.getPackaging(),updProductDto.getMakearea());
			if(pro != null && !pro.getId().equals(updProductDto.getId())) {
				this.addActionMessage("已存在相同产品名称、产品规格、颜色、包装、形式和产地的产品！");
				return "checkerror";
			}
			
			//文件目录
			String image_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_IMAGES_PATH);
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			String oldpic1 = "";
			String oldpic2 = "";
			String oldpic3 = "";
			String oldpdf = "";
			ProductDto oldProduct = productService.queryProductByID(updProductId);
			
			if(updPicFile01 != null) {
				String newfile01 = FileUtil.uploadFile(updPicFile01, image_path, file01Name);
				updProductDto.setPic01(newfile01);
				oldpic1 = oldProduct.getPic01();
			}
			if(updPicFile02 != null) {
				String newfile02 = FileUtil.uploadFile(updPicFile02, image_path, file02Name);
				updProductDto.setPic02(newfile02);
				oldpic2 = oldProduct.getPic02();
			}
			if(updPicFile03 != null) {
				String newfile03 = FileUtil.uploadFile(updPicFile03, image_path, file03Name);
				updProductDto.setPic03(newfile03);
				oldpic3 = oldProduct.getPic03();
			}
			if(updPdfFile != null) {
				String newfile04 = FileUtil.uploadFile(updPdfFile, pdf_path, file04Name);
				updProductDto.setPdfpath(newfile04);
				oldpdf = oldProduct.getPdfpath();
			}
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updProductDto.setUpdateuid(username);
			productService.updateProduct(updProductDto);
			//清空数据
			updPicFile01 = null;
			updPicFile02 = null;
			updPicFile03 = null;
			updPdfFile = null;
			
			//如果更新前有图片，更新后没有图片，则删除原图片
			if(StringUtil.isNotBlank(oldProduct.getPic01()) && StringUtil.isBlank(updProductDto.getPic01())) {
				oldpic1 = oldProduct.getPic01();
			}
			if(StringUtil.isNotBlank(oldProduct.getPic02()) && StringUtil.isBlank(updProductDto.getPic02())) {
				oldpic2 = oldProduct.getPic02();
			}
			if(StringUtil.isNotBlank(oldProduct.getPic03()) && StringUtil.isBlank(updProductDto.getPic03())) {
				oldpic3 = oldProduct.getPic03();
			}
			
			//判断是否需要删除原来文件
			if(StringUtil.isNotBlank(oldpic1)) {
				FileUtil.deleteFile(oldpic1, image_path);
			}
			if(StringUtil.isNotBlank(oldpic2)) {
				FileUtil.deleteFile(oldpic2, image_path);
			}
			if(StringUtil.isNotBlank(oldpic3)) {
				FileUtil.deleteFile(oldpic3, image_path);
			}
			if(StringUtil.isNotBlank(oldpdf)) {
				FileUtil.deleteFile(oldpdf, pdf_path);
			}
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增产品页面
	 * @return
	 */
	public String showAddProductAction() {
		try {
			this.clearMessages();
			addProductDto = new ProductDto();
			addProductDto.setRank(Constants.ROLE_RANK_OPERATOR);
			addPdfFile = null;
			addPicFile01 = null;
			addPicFile02 = null;
			addPicFile03 = null;
			file01Name = "";
			file02Name = "";
			file03Name = "";
			file04Name = "";
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showAddProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增产品
	 * @return
	 */
	public String addProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addProductDto)) {
				return "checkerror";
			}
			
			//判断逻辑主键是否唯一（产品名称、产品规格和颜色）
			ProductDto pro = productService.queryProductByLogicId(addProductDto.getTradename(),
					addProductDto.getTypeno(), addProductDto.getColor(), addProductDto.getItem10(), addProductDto.getPackaging(), addProductDto.getMakearea());
			if(pro != null) {
				this.addActionMessage("已存在相同产品名称、产品规格、颜色、包装、形式和产地的产品！");
				return "checkerror";
			}
			if(addPdfFile == null) {
				//this.addActionMessage("请选择对应PDF文件！");
				//return "checkerror";
			}
			
			//文件目录
			String image_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_IMAGES_PATH);
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			if(addPicFile01 != null) {
				String newfile01 = FileUtil.uploadFile(addPicFile01, image_path, file01Name);
				addProductDto.setPic01(newfile01);
			}
			if(addPicFile02 != null) {
				String newfile02 = FileUtil.uploadFile(addPicFile02, image_path, file02Name);
				addProductDto.setPic02(newfile02);
			}
			if(addPicFile03 != null) {
				String newfile03 = FileUtil.uploadFile(addPicFile03, image_path, file03Name);
				addProductDto.setPic03(newfile03);
			}
			
			//这里允许PDF文件为空update by frank 2015-10-24
			if(addPdfFile != null) {
				String newfile04 = FileUtil.uploadFile(addPdfFile, pdf_path, file04Name);
				addProductDto.setPdfpath(newfile04);
			}
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addProductDto.setUpdateuid(username);
			addProductDto.setCreateuid(username);
			//默认状态=有效
			addProductDto.setStatus(Constants.STATUS_NORMAL);
			
			addProductDto.setSampleflag("0");
			productService.insertProduct(addProductDto);
			this.addActionMessage("添加成功！");
			addProductDto = new ProductDto();
		} catch(Exception e) {
			log.error("addProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 产品管理页面
	 * @return
	 */
	public String showProductAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			strFieldno = "";
			strKeyword = "";
			strItem10 = "";
			strTradename = "";
			strTypeno = "";
			strColor = "";
			strSupplierId = "";
			strSeq = "";
			updProductId = "";
			page = new Page(intPageSize);
			//初期化字典数据
			initDictList();
			productList = new ArrayList<ProductDto>();
			
			queryData();
		} catch(Exception e) {
			log.error("showProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品
	 * @return
	 */
	public String queryProductAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnProductAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//产品选择页面========================
	/**
	 * 显示产品选择页面
	 * @return
	 */
	public String showProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（选择页面）
	 * @return
	 */
	public String queryProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（选择页面）
	 * @return
	 */
	public String turnProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//样品产品选择页面
	/**
	 * 显示产品选择页面
	 * @return
	 */
	public String showSampleProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showSampleProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（选择页面）
	 * @return
	 */
	public String querySampleProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("querySampleProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（选择页面）
	 * @return
	 */
	public String turnSampleProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnSampleProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//销售产品选择页面
	/**
	 * 显示销售产品选择页面
	 * @return
	 */
	public String showSalesProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showSalesProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示选择页面
	 * @return
	 */
	public String showProductidSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showProductidSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（选择页面）
	 * @return
	 */
	public String queryProductidSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryProductidSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（选择页面）
	 * @return
	 */
	public String turnProductidSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnProductidSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示客户跟踪选择页面
	 * @return
	 */
	public String showTrackProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showTrackProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户跟踪产品（选择页面）
	 * @return
	 */
	public String queryTrackProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryTrackProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页客户跟踪产品（选择页面）
	 * @return
	 */
	public String turnTrackProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnTrackProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询产品（销售单选择页面）
	 * @return
	 */
	public String querySalesProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("querySalesProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（销售单选择页面）
	 * @return
	 */
	public String turnSalesProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnSalesProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//单个产品选择页面
	/**
	 * 显示单个产品选择页面
	 * @return
	 */
	public String showProductSingleSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（单个产品选择页面）
	 * @return
	 */
	public String queryProductSingleSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（单个产品选择页面）
	 * @return
	 */
	public String turnProductSingleSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//单个产品选择页面
	/**
	 * 显示单个产品选择页面
	 * @return
	 */
	public String showDetailProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（单个产品选择页面）
	 * @return
	 */
	public String queryDetailProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（单个产品选择页面）
	 * @return
	 */
	public String turnDetailProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnProductSingleSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//税率
		List<Dict01Dto> listRate = dict01Service.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listRate != null && listRate.size() > 0) {
			common_rate = listRate.get(0).getCode();
		}
		//采购主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//电线特征列表
		featureList01 = dict01Service.queryFeatureByFieldcode(Constants.DICT_GOODS_TYPE_CODE_01, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//套管特征列表
		featureList02 = dict01Service.queryFeatureByFieldcode(Constants.DICT_GOODS_TYPE_CODE_02, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}
	
	/**
	 * 验证数据
	 * @param purchase
	 * @return
	 */
	private boolean checkData(ProductDto product) {
		if(product == null) {
			this.addActionMessage("类型不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getFieldno())) {
			this.addActionMessage("请选择类型！");
			return false;
		}
		if(StringUtil.isBlank(product.getBrand())) {
			this.addActionMessage("品牌不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getTradename())) {
			this.addActionMessage("品名不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getTypeno())) {
			this.addActionMessage("规格不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getColor())) {
			if (product.getFieldno().compareTo("03")!= 0){
				System.out.println("Fieldno="+product.getFieldno());
				this.addActionMessage("请选择颜色！");
				return false;
			}
		}
		if(StringUtil.isBlank(product.getItem10())) {
			this.addActionMessage("包装不能为空！");
			return false;
		}
//		if(product.getSupplierid() == null) {
//			this.addActionMessage("请选择供应商！");
//			return false;
//		}
		if(StringUtil.isNotBlank(product.getPurchaseprice()) && (new BigDecimal(product.getPurchaseprice())).doubleValue() < 0) {
			this.addActionMessage("采购价必须为大于0的数！");
			return false;
		} else {
			product.setPurchaseprice(null);
		}
		if(StringUtil.isNotBlank(product.getSalesprice()) && (new BigDecimal(product.getSalesprice())).doubleValue() < 0) {
			this.addActionMessage("销售指导价必须为大于0的数！");
			return false;
		} else {
			product.setSalesprice(null);
		}
/*		if(StringUtil.isBlank(product.getItem11())) {
			this.addActionMessage("住友代码不能为空！");
			return false;
		}
*/		
		if(Constants.DICT_GOODS_TYPE_CODE_01.equals(product.getFieldno())) {
			//电线，需要验证单选框数据
			if(StringUtil.isBlank(product.getItem01())) {
				this.addActionMessage("请选择耐温！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem02())) {
				this.addActionMessage("请选择耐压！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem03())) {
				this.addActionMessage("请选择材质！");
				return false;
			}
//			if(StringUtil.isBlank(product.getItem04())) {
//				this.addActionMessage("请选择环保！");
//				return false;
//			}
		} else if(Constants.DICT_GOODS_TYPE_CODE_02.equals(product.getFieldno())) {
			//套管，需要验证单选框数据
			if(StringUtil.isBlank(product.getItem01())) {
				this.addActionMessage("请选择耐温！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem02())) {
				this.addActionMessage("请选择耐压！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem03())) {
				this.addActionMessage("请选择绝缘！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem04())) {
				this.addActionMessage("请选择收缩比！");
				return false;
			}
			if(StringUtil.isBlank(product.getItem05())) {
				this.addActionMessage("请选择材质！");
				return false;
			}
//			if(StringUtil.isBlank(product.getItem06())) {
//				this.addActionMessage("请选择环保！");
//				return false;
//			}
		}
		
		return true;
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//初期化字典数据
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = productService.queryProductByPage(strFieldno, strItem10, strKeyword, strPackaging, strTradename, strTypeno, strColor,
				strSupplierId, "" + Constants.STATUS_NORMAL, page);
		productList = (List<ProductDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	
	/**
	 * 显示产品页面
	 * @return
	 */
	public String showUpdProductitemAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updProductDto = productService.queryProductByID(updProductId);
		} catch(Exception e) {
			log.error("showUpdProductitemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public String getStrFieldno() {
		return strFieldno;
	}

	public String getStrKeyword() {
		return strKeyword;
	}

	public void setStrFieldno(String strFieldno) {
		this.strFieldno = strFieldno;
	}

	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public List<Dict01Dto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Dict01Dto> goodsList) {
		this.goodsList = goodsList;
	}

	public ProductDto getAddProductDto() {
		return addProductDto;
	}

	public void setAddProductDto(ProductDto addProductDto) {
		this.addProductDto = addProductDto;
	}

	public List<Dict01Dto> getColorList() {
		return colorList;
	}

	public void setColorList(List<Dict01Dto> colorList) {
		this.colorList = colorList;
	}

	public List<Dict01Dto> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Dict01Dto> unitList) {
		this.unitList = unitList;
	}

	public List<Dict01Dto> getMakeareaList() {
		return makeareaList;
	}

	public void setMakeareaList(List<Dict01Dto> makeareaList) {
		this.makeareaList = makeareaList;
	}

	public String getStrSupplierId() {
		return strSupplierId;
	}

	public void setStrSupplierId(String strSupplierId) {
		this.strSupplierId = strSupplierId;
	}

	public String getStrSeq() {
		return strSeq;
	}

	public void setStrSeq(String strSeq) {
		this.strSeq = strSeq;
	}

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
	}

	public String getStrTypeno() {
		return strTypeno;
	}

	public void setStrTypeno(String strTypeno) {
		this.strTypeno = strTypeno;
	}

	public String getStrColor() {
		return strColor;
	}

	public void setStrColor(String strColor) {
		this.strColor = strColor;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getUpdProductId() {
		return updProductId;
	}

	public void setUpdProductId(String updProductId) {
		this.updProductId = updProductId;
	}

	public ProductDto getUpdProductDto() {
		return updProductDto;
	}

	public void setUpdProductDto(ProductDto updProductDto) {
		this.updProductDto = updProductDto;
	}

	public String getDelProductId() {
		return delProductId;
	}

	public void setDelProductId(String delProductId) {
		this.delProductId = delProductId;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

	public String getStrItem10() {
		return strItem10;
	}

	public void setStrItem10(String strItem10) {
		this.strItem10 = strItem10;
	}

	public File getAddPicFile01() {
		return addPicFile01;
	}

	public void setAddPicFile01(File addPicFile01) {
		this.addPicFile01 = addPicFile01;
	}

	public File getAddPicFile02() {
		return addPicFile02;
	}

	public void setAddPicFile02(File addPicFile02) {
		this.addPicFile02 = addPicFile02;
	}

	public File getAddPicFile03() {
		return addPicFile03;
	}

	public void setAddPicFile03(File addPicFile03) {
		this.addPicFile03 = addPicFile03;
	}

	public File getAddPdfFile() {
		return addPdfFile;
	}

	public void setAddPdfFile(File addPdfFile) {
		this.addPdfFile = addPdfFile;
	}

	public String getFile01Name() {
		return file01Name;
	}

	public void setFile01Name(String file01Name) {
		this.file01Name = file01Name;
	}

	public String getFile02Name() {
		return file02Name;
	}

	public void setFile02Name(String file02Name) {
		this.file02Name = file02Name;
	}

	public String getFile03Name() {
		return file03Name;
	}

	public void setFile03Name(String file03Name) {
		this.file03Name = file03Name;
	}

	public String getFile04Name() {
		return file04Name;
	}

	public void setFile04Name(String file04Name) {
		this.file04Name = file04Name;
	}

	public File getUpdPicFile01() {
		return updPicFile01;
	}

	public void setUpdPicFile01(File updPicFile01) {
		this.updPicFile01 = updPicFile01;
	}

	public File getUpdPicFile02() {
		return updPicFile02;
	}

	public void setUpdPicFile02(File updPicFile02) {
		this.updPicFile02 = updPicFile02;
	}

	public File getUpdPicFile03() {
		return updPicFile03;
	}

	public void setUpdPicFile03(File updPicFile03) {
		this.updPicFile03 = updPicFile03;
	}

	public File getUpdPdfFile() {
		return updPdfFile;
	}

	public void setUpdPdfFile(File updPdfFile) {
		this.updPdfFile = updPdfFile;
	}

	public List<FeatureDto> getFeatureList01() {
		return featureList01;
	}

	public void setFeatureList01(List<FeatureDto> featureList01) {
		this.featureList01 = featureList01;
	}

	public List<FeatureDto> getFeatureList02() {
		return featureList02;
	}

	public void setFeatureList02(List<FeatureDto> featureList02) {
		this.featureList02 = featureList02;
	}

	public String getStrPackaging() {
		return strPackaging;
	}

	public void setStrPackaging(String strPackaging) {
		this.strPackaging = strPackaging;
	}
}
