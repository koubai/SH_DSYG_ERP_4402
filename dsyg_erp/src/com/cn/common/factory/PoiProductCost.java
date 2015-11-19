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
import com.cn.dsyg.dto.ProductCostDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.WarehouseCheckDto;

public class PoiProductCost extends Poi2007Base {

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
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("产品成本一览");
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
		ProductCostDto productCostDto = new ProductCostDto();
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
			productCostDto = (ProductCostDto) datas.get(i);
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
			cell1.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + productCostDto.getFieldno()));
			cell1.setCellStyle(style);
			cell2.setCellValue(productCostDto.getTradename());
			cell2.setCellStyle(style);
			cell3.setCellValue(productCostDto.getTypeno());
			cell3.setCellStyle(style);
			cell4.setCellValue(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + productCostDto.getColor()));
			cell4.setCellStyle(style);
			cell5.setCellValue(productCostDto.getItem10());
			cell5.setCellStyle(style);
			cell6.setCellValue(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + productCostDto.getUnit()));
			cell6.setCellStyle(style);
			if("0".equals(productCostDto.getPackaging())) {
				cell7.setCellValue("整箱");
			} else {
				cell7.setCellValue("乱尺");
			}
			cell7.setCellStyle(style);
			cell8.setCellValue(dictMap.get(Constants.DICT_MAKEAREA_TYPE + "_" + productCostDto.getMakearea()));
			cell8.setCellStyle(style);
			if (productCostDto!= null && productCostDto.getProductcost()!=null)
				cell9.setCellValue(productCostDto.getProductcost().toString());
			else 
				cell9.setCellValue("");
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
		sheet.setColumnWidth(1, 20 * 256);
		heads.add("品名");
		sheet.setColumnWidth(2, 20 * 256);
		heads.add("规格");
		sheet.setColumnWidth(3, 20 * 256);
		heads.add("颜色");
		sheet.setColumnWidth(4, 10 * 256);
		heads.add("包装");
		sheet.setColumnWidth(5, 10 * 256);
		heads.add("单位");
		sheet.setColumnWidth(6, 10 * 256);
		heads.add("形式");
		sheet.setColumnWidth(7, 10 * 256);
		heads.add("产地");
		sheet.setColumnWidth(8, 10 * 256);
		heads.add("平均成本价格(含税)");
		sheet.setColumnWidth(9, 30 * 256);
		
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
