package com.cn.common.factory;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.WarehouserptDto;

public class PoiWarehouserptInDetail extends Poi2007Base {

	/**
	 * 输出大标题
	 * @param sheet
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void writeTitle(XSSFSheet sheet, XSSFWorkbook workbook) {
		//设置打印参数
		XSSFPrintSetup print = sheet.getPrintSetup();
		print.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
		print.setScale((short)68);
		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)18);
				
		XSSFRow row = sheet.createRow(1);
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 10));
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("东升盈港入库明细单");
		//式样
		XSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);
		cell.setCellStyle(style);
		
		//颜色字体
		XSSFFont font_cus = workbook.createFont();
		//字体大小
		font_cus.setFontHeightInPoints((short)18);
		//式样
		XSSFCellStyle style_cus = workbook.createCellStyle();
		style_cus.setFont(font_cus);
		
		WarehouserptDto warehouserpt = new WarehouserptDto();
		//添加数据
		warehouserpt = (WarehouserptDto) datas.get(0);
		
		XSSFRow row3 = sheet.createRow(3);
		XSSFCell cell_cus = row3.createCell(1);
		cell_cus.setCellValue("供应商：" + warehouserpt.getSuppliername());
		cell_cus.setCellStyle(style_cus);
		
		//颜色字体
		XSSFFont font_other = workbook.createFont();
		//字体大小
		font_other.setFontHeightInPoints((short)12);
		//式样
		XSSFCellStyle style_other = workbook.createCellStyle();
		style_other.setFont(font_other);
		
		XSSFRow row4 = sheet.createRow(4);
		XSSFCell cell_no = row4.createCell(8);
		cell_no.setCellValue("入库单号：" + warehouserpt.getWarehouseno());
		cell_no.setCellStyle(style_other);
		
		XSSFRow row5 = sheet.createRow(5);
		XSSFCell cell_date = row5.createCell(8);
		cell_date.setCellValue("入库日期：" + warehouserpt.getShowWarehousedate());
		cell_date.setCellStyle(style_other);
		
		XSSFRow row6 = sheet.createRow(6);
		XSSFCell cell_deliverry = row6.createCell(8);
		cell_deliverry.setCellValue("运送方式：");
		cell_deliverry.setCellStyle(style_other);

		XSSFRow row7 = sheet.createRow(7);
		XSSFCell cell_detail = row7.createCell(0);
		cell_detail.setCellValue("入库明细单：");
		cell_detail.setCellStyle(style_cus);
	}
	
	/**
	 * 输出数据部分
	 * @param sheet
	 */
	@Override
	public void writeData(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = null;
		WarehouserptDto warehouserpt = new WarehouserptDto();
		XSSFFont font = workbook.createFont();
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
		style.setWrapText(true);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		//添加数据
		int num = 0;
		for(int i = 0; i < datas.size(); i++) {
			warehouserpt = (WarehouserptDto) datas.get(i);
			if(warehouserpt.getListProduct() != null && warehouserpt.getListProduct().size() > 0) {
				//对货物数据解析
				for(int j = 0; j < warehouserpt.getListProduct().size(); j++) {
					ProductDto product = warehouserpt.getListProduct().get(j);
					row = sheet.createRow(num + 9);
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

					cell0.setCellValue(num + 1);
					cell0.setCellStyle(style);
					
					//货物信息
					//cell1.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + product.getFieldno()));
					//cell1.setCellStyle(style);
					cell1.setCellValue(product.getParentid());
					cell1.setCellStyle(style);
					cell2.setCellValue(product.getBrand());
					cell2.setCellStyle(style);
					cell3.setCellValue(product.getTradename());
					cell3.setCellStyle(style);
					cell4.setCellValue(product.getTypeno());
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					cell5.setCellValue(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + product.getColor()));
					cell6.setCellStyle(style);
//					if("0".equals(product.getPackaging())) {
//						cell6.setCellValue("整箱");
//					} else {
//						cell6.setCellValue("乱尺");
//					}
//					cell7.setCellStyle(style);
//					cell7.setCellValue(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + product.getUnit()));
//					cell8.setCellStyle(style);
//					cell8.setCellValue(product.getNum());
//					cell9.setCellStyle(style);
//					cell9.setCellValue(product.getAmount());
//					cell10.setCellValue(product.getRes09());
//					cell10.setCellStyle(style);
					cell6.setCellStyle(style);
					cell6.setCellValue(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + product.getUnit()));
					cell7.setCellStyle(style);
					cell7.setCellValue(product.getNum());
					cell8.setCellStyle(style);
					//含税单价
					if(product.getUnitprice() != null && !product.getUnitprice().equals("")){
						cell8.setCellValue(product.getUnitprice());
					} else {
						BigDecimal bdAmount = new BigDecimal(product.getAmount());
						BigDecimal bdNum = new BigDecimal(product.getNum());
						BigDecimal bdprice = new BigDecimal(0);
						if(!bdNum.equals(BigDecimal.ZERO)){
							bdprice = bdAmount.divide(bdNum,6, BigDecimal.ROUND_HALF_UP);
						}						
						cell8.setCellValue(StringUtil.BigDecimal2StrAbs(bdprice, 6));
					}
					cell9.setCellStyle(style);
					cell9.setCellValue(product.getAmount());
					cell10.setCellValue(product.getRes09());
					cell10.setCellStyle(style);
					num++;
				}
			} else {
				row = sheet.createRow(num + 9);
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

				cell0.setCellValue(num + 1);
				cell0.setCellStyle(style);
				
				//货物信息
				cell1.setCellValue("");
				cell1.setCellStyle(style);
				cell2.setCellValue("");
				cell2.setCellStyle(style);
				cell3.setCellValue("");
				cell3.setCellStyle(style);
				cell4.setCellValue("");
				cell4.setCellStyle(style);
				cell5.setCellValue("");
				cell5.setCellStyle(style);
				cell6.setCellValue("");
				cell6.setCellStyle(style);
				cell7.setCellValue("");
				cell7.setCellStyle(style);
				cell8.setCellValue("");
				cell8.setCellStyle(style);
				cell9.setCellValue("");
				cell9.setCellStyle(style);
				cell10.setCellValue("");
				cell10.setCellStyle(style);
				num++;
			}
		}

		row = sheet.createRow(num + 9);
		XSSFCell cell30 = row.createCell(0);
		XSSFCell cell31 = row.createCell(1);
		XSSFCell cell32 = row.createCell(2);
		XSSFCell cell33 = row.createCell(3);
		XSSFCell cell34 = row.createCell(4);
		XSSFCell cell35 = row.createCell(5);
		XSSFCell cell36 = row.createCell(6);
		XSSFCell cell37 = row.createCell(7);
		XSSFCell cell38 = row.createCell(8);
		XSSFCell cell39 = row.createCell(9);
		XSSFCell cell40 = row.createCell(10);

		cell30.setCellValue("");
		cell30.setCellStyle(style);
		cell31.setCellValue("");
		cell31.setCellStyle(style);
		cell32.setCellValue("");
		cell32.setCellStyle(style);
		cell33.setCellValue("");
		cell33.setCellStyle(style);
		cell34.setCellValue("");
		cell34.setCellStyle(style);
		cell35.setCellValue("");
		cell35.setCellStyle(style);
		cell36.setCellValue("总计:");
		cell36.setCellStyle(style);
		cell37.setCellValue(StringUtil.BigDecimal2Str(warehouserpt.getTotalnum(), 2));
		cell37.setCellStyle(style);
		cell38.setCellValue("");
		cell38.setCellStyle(style);
		cell39.setCellValue(warehouserpt.getTotaltaxamount().toString());
		cell39.setCellStyle(style);
		cell40.setCellValue("");
		cell40.setCellStyle(style);
		
		XSSFCellStyle style_other = workbook.createCellStyle();	
		XSSFFont font_other = workbook.createFont();
		//字体大小
		font_other.setFontHeightInPoints((short)14);
		style_other.setFont(font_other);	
//		row = sheet.createRow(num + 14);
//		XSSFCell cell14 = row.createCell(1);
//		cell14.setCellValue("品保出荷检查:");
//		cell14.setCellStyle(style_other);
		
		row = sheet.createRow(num + 13);
		XSSFCell cell15 = row.createCell(1);
		cell15.setCellValue("出货方签字:");
		cell15.setCellStyle(style_other);
		XSSFCell cell16 = row.createCell(5);
		cell16.setCellValue("收货方签字:");
		cell16.setCellStyle(style_other);
	}
	
	/**
	 * 输出Head部分
	 * @param sheet
	 */
	@Override
	public void writeHead(XSSFSheet sheet, XSSFWorkbook workbook) {
		heads = new ArrayList<String>();
		heads.add("编号");
		sheet.setColumnWidth(0, 5 * 256);
		
		//货物信息
		//heads.add("主题");
		//sheet.setColumnWidth(1, 15 * 256);
		heads.add("采购订单号");
		sheet.setColumnWidth(1, 15 * 256);
		heads.add("品牌");
		sheet.setColumnWidth(2, 10 * 256);
		heads.add("品名");
		sheet.setColumnWidth(3, 12 * 256);
		heads.add("规格");
		sheet.setColumnWidth(4, 25 * 256);
		heads.add("颜色");
		sheet.setColumnWidth(5, 6 * 256);
		heads.add("单位");
		sheet.setColumnWidth(6, 6 * 256);
		heads.add("数量");
		sheet.setColumnWidth(7, 10 * 256);
		heads.add("含税单价");
		sheet.setColumnWidth(8, 12 * 256);
		heads.add("含税金额");
		sheet.setColumnWidth(9, 12 * 256);
		heads.add("备注");
		sheet.setColumnWidth(10, 12 * 256);

		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)14);
		
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
		
		XSSFRow row = sheet.createRow(8);
		XSSFCell cell = null;
		
		for(int i = 0; i < heads.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(heads.get(i));
			cell.setCellStyle(style);
		}
	}
}
