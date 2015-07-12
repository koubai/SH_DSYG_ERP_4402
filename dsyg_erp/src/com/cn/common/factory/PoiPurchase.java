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
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.PurchaseDto;

public class PoiPurchase extends Poi2007Base {

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
		cell.setCellValue("采购单一览");
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
		PurchaseDto purchase = new PurchaseDto();
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
			row = sheet.createRow(i + 3);
			purchase = (PurchaseDto) datas.get(i);
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
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(style);
			cell1.setCellValue(purchase.getPurchaseno());
			cell1.setCellStyle(style);
			cell2.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + purchase.getTheme1()));
			cell2.setCellStyle(style);
			cell3.setCellValue(purchase.getWarehouse());
			cell3.setCellStyle(style);
			cell4.setCellValue(purchase.getSuppliername());
			cell4.setCellStyle(style);
			cell5.setCellValue(purchase.getSuppliermanager());
			cell5.setCellStyle(style);
			cell6.setCellValue(purchase.getSuppliertel());
			cell6.setCellStyle(style);
			cell7.setCellValue(purchase.getSuppliermanageraddr());
			cell7.setCellStyle(style);
			cell8.setCellValue(purchase.getSuppliermail());
			cell8.setCellStyle(style);
			cell9.setCellValue(purchase.getHandler());
			cell9.setCellStyle(style);
			cell10.setCellValue(purchase.getPurchasedate());
			cell10.setCellStyle(style);
			cell11.setCellValue(purchase.getPlandate());
			cell11.setCellStyle(style);
			cell12.setCellValue("" + purchase.getTotalamount());
			cell12.setCellStyle(style);
			cell13.setCellValue("" + purchase.getTaxamount());
			cell13.setCellStyle(style);
			cell14.setCellValue("" + purchase.getPaidamount());
			cell14.setCellStyle(style);
			if(purchase.getUnpaidamount() == null) {
				cell15.setCellValue("0.00");
			} else {
				cell15.setCellValue("" + purchase.getUnpaidamount());
			}
			cell15.setCellStyle(style);
			if(StringUtil.isBlank(purchase.getApproverid())) {
				cell16.setCellValue("");
			} else {
				cell16.setCellValue(purchase.getApproverid());
			}
			cell16.setCellStyle(style);
			if(StringUtil.isBlank(purchase.getNote())) {
				cell17.setCellValue("");
			} else {
				cell17.setCellValue(purchase.getNote());
			}
			cell17.setCellStyle(style);
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
		heads.add("采购单号");
		sheet.setColumnWidth(1, 40 * 256);
		heads.add("采购主题");
		sheet.setColumnWidth(2, 15 * 256);
		heads.add("仓库");
		sheet.setColumnWidth(3, 20 * 256);
		heads.add("供应商");
		sheet.setColumnWidth(4, 20 * 256);
		heads.add("联系人");
		sheet.setColumnWidth(5, 10 * 256);
		heads.add("联系人电话");
		sheet.setColumnWidth(6, 20 * 256);
		heads.add("联系人地址");
		sheet.setColumnWidth(7, 30 * 256);
		heads.add("联系人邮箱");
		sheet.setColumnWidth(8, 30 * 256);
		heads.add("经手人");
		sheet.setColumnWidth(9, 10 * 256);
		heads.add("采购日期");
		sheet.setColumnWidth(10, 15 * 256);
		heads.add("预入库时间");
		sheet.setColumnWidth(11, 15 * 256);
		heads.add("采购金额（不含税）");
		sheet.setColumnWidth(12, 20 * 256);
		heads.add("采购金额（含税）");
		sheet.setColumnWidth(13, 20 * 256);
		heads.add("已付金额（含税）");
		sheet.setColumnWidth(14, 20 * 256);
		heads.add("未付金额（含税）");
		sheet.setColumnWidth(15, 20 * 256);
		heads.add("确认者");
		sheet.setColumnWidth(16, 10 * 256);
		heads.add("备注");
		sheet.setColumnWidth(17, 30 * 256);
		
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
