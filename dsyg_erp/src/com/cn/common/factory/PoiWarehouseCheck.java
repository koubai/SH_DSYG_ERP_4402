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
import com.cn.dsyg.dto.WarehouseCheckDto;

public class PoiWarehouseCheck extends Poi2007Base {

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
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("盘点一览");
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
		WarehouseCheckDto warehouseCheck = new WarehouseCheckDto();
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
			warehouseCheck = (WarehouseCheckDto) datas.get(i);
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
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(style);
			cell1.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + warehouseCheck.getFieldno()));
			cell1.setCellStyle(style);
			cell2.setCellValue(warehouseCheck.getWarehousename());
			cell2.setCellStyle(style);
			cell3.setCellValue(warehouseCheck.getTradename());
			cell3.setCellStyle(style);
			cell4.setCellValue(warehouseCheck.getTypeno());
			cell4.setCellStyle(style);
			cell5.setCellValue(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + warehouseCheck.getColor()));
			cell5.setCellStyle(style);
			if("0".equals(warehouseCheck.getPackaging())) {
				cell6.setCellValue("整箱");
			} else {
				cell6.setCellValue("乱尺");
			}
			cell6.setCellStyle(style);
			cell7.setCellValue(warehouseCheck.getItem10());
			cell7.setCellStyle(style);
			cell8.setCellValue(warehouseCheck.getSuppliername());
			cell8.setCellStyle(style);
			cell9.setCellValue("" + warehouseCheck.getWarehouseamount());
			cell9.setCellStyle(style);
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
		heads.add("主题");
		sheet.setColumnWidth(1, 15 * 256);
		heads.add("仓库");
		sheet.setColumnWidth(2, 10 * 256);
		heads.add("品名");
		sheet.setColumnWidth(3, 20 * 256);
		heads.add("规格");
		sheet.setColumnWidth(4, 50 * 256);
		heads.add("颜色");
		sheet.setColumnWidth(5, 10 * 256);
		heads.add("形式");
		sheet.setColumnWidth(6, 10 * 256);
		heads.add("包装");
		sheet.setColumnWidth(7, 30 * 256);
		heads.add("供应商");
		sheet.setColumnWidth(8, 30 * 256);
		heads.add("库存数量");
		sheet.setColumnWidth(9, 15 * 256);
		
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
