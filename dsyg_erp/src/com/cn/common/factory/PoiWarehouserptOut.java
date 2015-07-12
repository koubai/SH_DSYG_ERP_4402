package com.cn.common.factory;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.WarehouserptDto;

public class PoiWarehouserptOut extends Poi2007Base {
	
	/**
	 * 输出大标题
	 * @param sheet
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void writeTitle(XSSFSheet sheet, XSSFWorkbook workbook) {
		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)18);
				
		XSSFRow row = sheet.createRow(1);
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("出库单一览");
		//式样
		XSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);
		cell.setCellStyle(style);
	}
	
	/**
	 * 输出数据部分
	 * @param sheet
	 */
	@Override
	public void writeData(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = null;
		WarehouserptDto warehouserpt = new WarehouserptDto();
		//式样
		XSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//添加边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		//添加数据
		int num = 0;
		for(int i = 0; i < datas.size(); i++) {
			warehouserpt = (WarehouserptDto) datas.get(i);
			if(warehouserpt.getListProduct() != null && warehouserpt.getListProduct().size() > 0) {
				//对货物数据解析
				for(int j = 0; j < warehouserpt.getListProduct().size(); j++) {
					ProductDto product = warehouserpt.getListProduct().get(j);
					row = sheet.createRow(num + 3);
					XSSFCell cell0 = row.createCell(0);
					XSSFCell cell1 = row.createCell(1);
					XSSFCell cell2 = row.createCell(2);
					XSSFCell cell3 = row.createCell(3);
					XSSFCell cell4 = row.createCell(4);
					XSSFCell cell5 = row.createCell(5);
					XSSFCell cell6 = row.createCell(6);
					XSSFCell cell7 = row.createCell(7);
					XSSFCell cell8 = row.createCell(8);
					XSSFCell cell9 = row.createCell(9);
					XSSFCell cell10 = row.createCell(10);
					XSSFCell cell11 = row.createCell(11);
					XSSFCell cell12 = row.createCell(12);
					XSSFCell cell13 = row.createCell(13);
					XSSFCell cell14 = row.createCell(14);
					XSSFCell cell15 = row.createCell(15);
					XSSFCell cell16 = row.createCell(16);
					XSSFCell cell17 = row.createCell(17);
					XSSFCell cell18 = row.createCell(18);
					XSSFCell cell19 = row.createCell(19);
					XSSFCell cell20 = row.createCell(20);
					XSSFCell cell21 = row.createCell(21);
					XSSFCell cell22 = row.createCell(22);
					XSSFCell cell23 = row.createCell(23);
					XSSFCell cell24 = row.createCell(24);
					XSSFCell cell25 = row.createCell(25);
					XSSFCell cell26 = row.createCell(26);
					XSSFCell cell27 = row.createCell(27);
					XSSFCell cell28 = row.createCell(28);
					XSSFCell cell29 = row.createCell(29);
					XSSFCell cell30 = row.createCell(30);
					XSSFCell cell31 = row.createCell(31);
					
					cell0.setCellValue(num + 1);
					cell0.setCellStyle(style);
					cell1.setCellValue(warehouserpt.getWarehouseno());
					cell1.setCellStyle(style);
					cell2.setCellValue("出库单");
					cell2.setCellStyle(style);
					cell3.setCellValue(warehouserpt.getWarehousename());
					cell3.setCellStyle(style);
					cell4.setCellValue(warehouserpt.getParentid());
					cell4.setCellStyle(style);
					cell5.setCellValue("" + warehouserpt.getTotaltaxamount());
					cell5.setCellStyle(style);
					
					//货物信息
					cell6.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + product.getFieldno()));
					cell6.setCellStyle(style);
					cell7.setCellValue(product.getBrand());
					cell7.setCellStyle(style);
					cell8.setCellValue(product.getTradename());
					cell8.setCellStyle(style);
					cell9.setCellValue(product.getTypeno());
					cell9.setCellStyle(style);
					cell10.setCellStyle(style);
					cell10.setCellValue(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + product.getColor()));
					cell11.setCellStyle(style);
					if("1".equals(product.getPackaging())) {
						cell11.setCellValue("整箱");
					} else {
						cell11.setCellValue("乱尺");
					}
					cell12.setCellStyle(style);
					cell12.setCellValue(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + product.getUnit()));
					cell13.setCellStyle(style);
					cell13.setCellValue(product.getNum());
					cell14.setCellStyle(style);
					cell14.setCellValue(product.getAmount());
					
					cell15.setCellStyle(style);
					cell15.setCellValue(warehouserpt.getShowWarehousedate());
					cell16.setCellStyle(style);
					cell16.setCellValue(StringUtil.getStr(warehouserpt.getSuppliername()));
					cell17.setCellStyle(style);
					cell17.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermanager()));
					cell18.setCellStyle(style);
					cell18.setCellValue(StringUtil.getStr(warehouserpt.getSuppliertel()));
					cell19.setCellStyle(style);
					cell19.setCellValue(StringUtil.getStr(warehouserpt.getSupplierfax()));
					cell20.setCellStyle(style);
					cell20.setCellValue(StringUtil.getStr(warehouserpt.getSupplieraddress()));
					cell21.setCellStyle(style);
					cell21.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermail()));
					cell22.setCellStyle(style);
					cell22.setCellValue(StringUtil.getStr(warehouserpt.getExpressname()));
					cell23.setCellStyle(style);
					cell23.setCellValue(StringUtil.getStr(warehouserpt.getExpressmanager()));
					cell24.setCellStyle(style);
					cell24.setCellValue(StringUtil.getStr(warehouserpt.getExpresstel()));
					cell25.setCellStyle(style);
					cell25.setCellValue(StringUtil.getStr(warehouserpt.getExpressaddress()));
					cell26.setCellStyle(style);
					cell26.setCellValue(StringUtil.getStr(warehouserpt.getExpressfax()));
					cell27.setCellStyle(style);
					cell27.setCellValue(StringUtil.getStr(warehouserpt.getExpressmail()));
					cell28.setCellStyle(style);
					if(warehouserpt.getExpresstaxamount() != null) {
						cell28.setCellValue("" + warehouserpt.getExpresstaxamount());
					} else {
						cell28.setCellValue("");
					}
					cell29.setCellStyle(style);
					cell29.setCellValue(warehouserpt.getApproverid());
					cell30.setCellStyle(style);
					cell30.setCellValue(DateUtil.dateToLogintime(warehouserpt.getCreatedate()));
					cell31.setCellStyle(style);
					cell31.setCellValue(DateUtil.dateToLogintime(warehouserpt.getUpdatedate()));
					num++;
				}
			} else {
				row = sheet.createRow(num + 3);
				XSSFCell cell0 = row.createCell(0);
				XSSFCell cell1 = row.createCell(1);
				XSSFCell cell2 = row.createCell(2);
				XSSFCell cell3 = row.createCell(3);
				XSSFCell cell4 = row.createCell(4);
				XSSFCell cell5 = row.createCell(5);
				XSSFCell cell6 = row.createCell(6);
				XSSFCell cell7 = row.createCell(7);
				XSSFCell cell8 = row.createCell(8);
				XSSFCell cell9 = row.createCell(9);
				XSSFCell cell10 = row.createCell(10);
				XSSFCell cell11 = row.createCell(11);
				XSSFCell cell12 = row.createCell(12);
				XSSFCell cell13 = row.createCell(13);
				XSSFCell cell14 = row.createCell(14);
				XSSFCell cell15 = row.createCell(15);
				XSSFCell cell16 = row.createCell(16);
				XSSFCell cell17 = row.createCell(17);
				XSSFCell cell18 = row.createCell(18);
				XSSFCell cell19 = row.createCell(19);
				XSSFCell cell20 = row.createCell(20);
				XSSFCell cell21 = row.createCell(21);
				XSSFCell cell22 = row.createCell(22);
				XSSFCell cell23 = row.createCell(23);
				XSSFCell cell24 = row.createCell(24);
				XSSFCell cell25 = row.createCell(25);
				XSSFCell cell26 = row.createCell(26);
				XSSFCell cell27 = row.createCell(27);
				XSSFCell cell28 = row.createCell(28);
				XSSFCell cell29 = row.createCell(29);
				XSSFCell cell30 = row.createCell(30);
				XSSFCell cell31 = row.createCell(31);
				
				cell0.setCellValue(num + 1);
				cell0.setCellStyle(style);
				cell1.setCellValue(warehouserpt.getWarehouseno());
				cell1.setCellStyle(style);
				cell2.setCellValue("出库单");
				cell2.setCellStyle(style);
				cell3.setCellValue(warehouserpt.getWarehousename());
				cell3.setCellStyle(style);
				cell4.setCellValue(warehouserpt.getParentid());
				cell4.setCellStyle(style);
				cell5.setCellValue("" + warehouserpt.getTotaltaxamount());
				cell5.setCellStyle(style);
				
				//货物信息
				cell6.setCellValue("");
				cell6.setCellStyle(style);
				cell7.setCellValue("");
				cell7.setCellStyle(style);
				cell8.setCellValue("");
				cell8.setCellStyle(style);
				cell9.setCellValue("");
				cell9.setCellStyle(style);
				cell10.setCellValue("");
				cell11.setCellStyle(style);
				cell11.setCellValue("");
				cell12.setCellStyle(style);
				cell12.setCellValue("");
				cell13.setCellStyle(style);
				cell13.setCellValue("");
				cell14.setCellStyle(style);
				cell14.setCellValue("");
				
				cell15.setCellStyle(style);
				cell15.setCellValue(warehouserpt.getShowWarehousedate());
				cell16.setCellStyle(style);
				cell16.setCellValue(StringUtil.getStr(warehouserpt.getSuppliername()));
				cell17.setCellStyle(style);
				cell17.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermanager()));
				cell18.setCellStyle(style);
				cell18.setCellValue(StringUtil.getStr(warehouserpt.getSuppliertel()));
				cell19.setCellStyle(style);
				cell19.setCellValue(StringUtil.getStr(warehouserpt.getSupplierfax()));
				cell20.setCellStyle(style);
				cell20.setCellValue(StringUtil.getStr(warehouserpt.getSupplieraddress()));
				cell21.setCellStyle(style);
				cell21.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermail()));
				cell22.setCellStyle(style);
				cell22.setCellValue(StringUtil.getStr(warehouserpt.getExpressname()));
				cell23.setCellStyle(style);
				cell23.setCellValue(StringUtil.getStr(warehouserpt.getExpressmanager()));
				cell24.setCellStyle(style);
				cell24.setCellValue(StringUtil.getStr(warehouserpt.getExpresstel()));
				cell25.setCellStyle(style);
				cell25.setCellValue(StringUtil.getStr(warehouserpt.getExpressaddress()));
				cell26.setCellStyle(style);
				cell26.setCellValue(StringUtil.getStr(warehouserpt.getExpressfax()));
				cell27.setCellStyle(style);
				cell27.setCellValue(StringUtil.getStr(warehouserpt.getExpressmail()));
				cell28.setCellStyle(style);
				if(warehouserpt.getExpresstaxamount() != null) {
					cell28.setCellValue("" + warehouserpt.getExpresstaxamount());
				} else {
					cell28.setCellValue("");
				}
				cell29.setCellStyle(style);
				cell29.setCellValue(warehouserpt.getApproverid());
				cell30.setCellStyle(style);
				cell30.setCellValue(DateUtil.dateToLogintime(warehouserpt.getCreatedate()));
				cell31.setCellStyle(style);
				cell31.setCellValue(DateUtil.dateToLogintime(warehouserpt.getUpdatedate()));
				num++;
			}
		}
	}
	
	/**
	 * 输出Head部分
	 * @param sheet
	 */
	@Override
	public void writeHead(XSSFSheet sheet, XSSFWorkbook workbook) {
		heads = new ArrayList<String>();
		heads.add("编号");
		sheet.setColumnWidth(0, 10 * 256);
		heads.add("出库单号");
		sheet.setColumnWidth(1, 35 * 256);
		heads.add("来源类型");
		sheet.setColumnWidth(2, 15 * 256);
		heads.add("仓库");
		sheet.setColumnWidth(3, 30 * 256);
		heads.add("来源单号");
		sheet.setColumnWidth(4, 35 * 256);
		heads.add("总金额（含税）");
		sheet.setColumnWidth(5, 15 * 256);
		
		//货物信息
		heads.add("主题");
		sheet.setColumnWidth(6, 15 * 256);
		heads.add("品牌");
		sheet.setColumnWidth(7, 15 * 256);
		heads.add("品名");
		sheet.setColumnWidth(8, 15 * 256);
		heads.add("规格");
		sheet.setColumnWidth(9, 15 * 256);
		heads.add("颜色");
		sheet.setColumnWidth(10, 15 * 256);
		heads.add("包装");
		sheet.setColumnWidth(11, 15 * 256);
		heads.add("单位");
		sheet.setColumnWidth(12, 15 * 256);
		heads.add("数量");
		sheet.setColumnWidth(13, 15 * 256);
		heads.add("税后金额");
		sheet.setColumnWidth(14, 15 * 256);
		
		heads.add("出库单日期");
		sheet.setColumnWidth(15, 20 * 256);
		heads.add("客户");
		sheet.setColumnWidth(16, 30 * 256);
		heads.add("客户联系人");
		sheet.setColumnWidth(17, 20 * 256);
		heads.add("客户联系电话");
		sheet.setColumnWidth(18, 20 * 256);
		heads.add("客户传真");
		sheet.setColumnWidth(19, 15 * 256);
		heads.add("客户地址");
		sheet.setColumnWidth(20, 35 * 256);
		heads.add("客户邮箱");
		sheet.setColumnWidth(21, 30 * 256);
		heads.add("快递公司");
		sheet.setColumnWidth(22, 30 * 256);
		heads.add("快递联系人");
		sheet.setColumnWidth(23, 20 * 256);
		heads.add("快递联系电话");
		sheet.setColumnWidth(24, 20 * 256);
		heads.add("快递联系地址");
		sheet.setColumnWidth(25, 30 * 256);
		heads.add("快递联系传真");
		sheet.setColumnWidth(26, 20 * 256);
		heads.add("快递联系邮箱");
		sheet.setColumnWidth(27, 30 * 256);
		heads.add("快递金额(含税)");
		sheet.setColumnWidth(28, 20 * 256);
		heads.add("确认者");
		sheet.setColumnWidth(29, 20 * 256);
		heads.add("作成时间");
		sheet.setColumnWidth(30, 20 * 256);
		heads.add("更新时间");
		sheet.setColumnWidth(31, 20 * 256);
		
		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)12);
		
		//式样
		XSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//添加边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setFont(font);
		//背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(180, 180, 180)));
		XSSFRow row = sheet.createRow(2);
		
		XSSFCell cell = null;
		for(int i = 0; i < heads.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(heads.get(i));
			cell.setCellStyle(style);
		}
	}
}
