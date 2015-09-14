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

import com.cn.common.util.DateUtil;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.WarehouserptDto;

public class PoiWarehouserptIn extends Poi2007Base {

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
		cell.setCellValue("入库单一览");
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
		for(int i = 0; i < datas.size(); i++) {
			warehouserpt = (WarehouserptDto) datas.get(i);
			row = sheet.createRow(i + 3);
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
			
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(style);
			cell1.setCellValue(warehouserpt.getWarehouseno());
			cell1.setCellStyle(style);
			cell2.setCellValue("入库单");
			cell2.setCellStyle(style);
			cell3.setCellValue(warehouserpt.getWarehousename());
			cell3.setCellStyle(style);
			cell4.setCellValue(warehouserpt.getParentid());
			cell4.setCellStyle(style);
			cell5.setCellValue("" + warehouserpt.getTotaltaxamount());
			cell5.setCellStyle(style);
			
			cell6.setCellStyle(style);
			cell6.setCellValue(warehouserpt.getShowWarehousedate());
			cell7.setCellStyle(style);
			cell7.setCellValue(StringUtil.getStr(warehouserpt.getSuppliername()));
			cell8.setCellStyle(style);
			cell8.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermanager()));
			cell9.setCellStyle(style);
			cell9.setCellValue(StringUtil.getStr(warehouserpt.getSuppliertel()));
			cell10.setCellStyle(style);
			cell10.setCellValue(StringUtil.getStr(warehouserpt.getSupplierfax()));
			cell11.setCellStyle(style);
			cell11.setCellValue(StringUtil.getStr(warehouserpt.getSupplieraddress()));
			cell12.setCellStyle(style);
			cell12.setCellValue(StringUtil.getStr(warehouserpt.getSuppliermail()));
			cell13.setCellStyle(style);
			cell13.setCellValue(StringUtil.getStr(warehouserpt.getExpressname()));
			cell14.setCellStyle(style);
			cell14.setCellValue(StringUtil.getStr(warehouserpt.getExpressmanager()));
			cell15.setCellStyle(style);
			cell15.setCellValue(StringUtil.getStr(warehouserpt.getExpresstel()));
			cell16.setCellStyle(style);
			cell16.setCellValue(StringUtil.getStr(warehouserpt.getExpressaddress()));
			cell17.setCellStyle(style);
			cell17.setCellValue(StringUtil.getStr(warehouserpt.getExpressfax()));
			cell18.setCellStyle(style);
			cell18.setCellValue(StringUtil.getStr(warehouserpt.getExpressmail()));
			cell19.setCellStyle(style);
			if(warehouserpt.getExpresstaxamount() != null) {
				cell19.setCellValue("" + warehouserpt.getExpresstaxamount());
			} else {
				cell19.setCellValue("");
			}				
			cell20.setCellStyle(style);
			cell20.setCellValue(warehouserpt.getApproverid());
			cell21.setCellStyle(style);
			cell21.setCellValue(DateUtil.dateToLogintime(warehouserpt.getCreatedate()));
			cell22.setCellStyle(style);
			cell22.setCellValue(DateUtil.dateToLogintime(warehouserpt.getUpdatedate()));
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
		heads.add("入库单号");
		sheet.setColumnWidth(1, 35 * 256);
		heads.add("来源类型");
		sheet.setColumnWidth(2, 15 * 256);
		heads.add("仓库");
		sheet.setColumnWidth(3, 30 * 256);
		heads.add("来源单号");
		sheet.setColumnWidth(4, 35 * 256);
		heads.add("总金额（含税）");
		sheet.setColumnWidth(5, 15 * 256);

		heads.add("入库单日期");
		sheet.setColumnWidth(6, 20 * 256);
		heads.add("供应商");
		sheet.setColumnWidth(7, 30 * 256);
		heads.add("供应商联系人");
		sheet.setColumnWidth(8, 20 * 256);
		heads.add("供应商联系电话");
		sheet.setColumnWidth(9, 20 * 256);
		heads.add("供应商传真");
		sheet.setColumnWidth(10, 15 * 256);
		heads.add("供应商地址");
		sheet.setColumnWidth(11, 35 * 256);
		heads.add("供应商邮箱");
		sheet.setColumnWidth(12, 30 * 256);
		heads.add("快递公司");
		sheet.setColumnWidth(13, 30 * 256);
		heads.add("快递联系人");
		sheet.setColumnWidth(14, 20 * 256);
		heads.add("快递联系电话");
		sheet.setColumnWidth(15, 20 * 256);
		heads.add("快递联系地址");
		sheet.setColumnWidth(16, 30 * 256);
		heads.add("快递联系传真");
		sheet.setColumnWidth(17, 20 * 256);
		heads.add("快递联系邮箱");
		sheet.setColumnWidth(18, 30 * 256);
		heads.add("快递金额(含税)");
		sheet.setColumnWidth(19, 20 * 256);
		heads.add("确认者");
		sheet.setColumnWidth(20, 20 * 256);
		heads.add("作成时间");
		sheet.setColumnWidth(21, 20 * 256);
		heads.add("更新时间");
		sheet.setColumnWidth(22, 20 * 256);
		
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
