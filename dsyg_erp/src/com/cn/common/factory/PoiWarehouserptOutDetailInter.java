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
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.WarehouserptDto;

public class PoiWarehouserptOutDetailInter extends Poi2007Base {
	
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
		print.setScale((short)69);
		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)18);
				
		XSSFRow row = sheet.createRow(1);
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("仓库配货单");
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
		XSSFFont font = workbook.createFont();
		//字体大小
		font.setFontHeightInPoints((short)16);
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
					row = sheet.createRow(num + 8);
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
					cell1.setCellValue(dictMap.get(Constants.DICT_GOODS_TYPE + "_" + product.getFieldno()));
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
					if("0".equals(product.getPackaging())) {
						cell6.setCellValue("整箱");
					} else {
						cell6.setCellValue("乱尺");
					}
					cell7.setCellStyle(style);
					cell7.setCellValue(product.getItem10());
					cell8.setCellStyle(style);
					cell8.setCellValue(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + product.getUnit()));
					cell9.setCellStyle(style);
					if(product.getNum() != null && !"".equals(product.getNum())) {
						//Float n = Float.valueOf(product.getNum());
						BigDecimal d = new BigDecimal(product.getNum());
						//if(n < 0) {
							//cell8.setCellValue("" + (n * -1));
						//} else {
							cell9.setCellValue(StringUtil.BigDecimal2StrAbs(d, 2));
						//}
					} else {
						cell9.setCellValue("");
					}
					cell10.setCellStyle(style);
					cell10.setCellValue(dictMap.get(Constants.DICT_MAKEAREA + "_" + product.getMakearea()));
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
		heads.add("主题");
		sheet.setColumnWidth(1, 12 * 256);
		heads.add("品牌");
		sheet.setColumnWidth(2, 12 * 256);
		heads.add("品名");
		sheet.setColumnWidth(3, 12 * 256);
		heads.add("规格");
		sheet.setColumnWidth(4, 25 * 256);
		heads.add("颜色");
		sheet.setColumnWidth(5, 6 * 256);
		heads.add("形式");
		sheet.setColumnWidth(6, 10 * 256);
		heads.add("包装");
		sheet.setColumnWidth(7, 10 * 256);
		heads.add("单位");
		sheet.setColumnWidth(8, 6 * 256);
		heads.add("数量");
		sheet.setColumnWidth(9, 14 * 256);
		heads.add("产地");
		sheet.setColumnWidth(10, 12 * 256);
		//heads.add("税后金额");
		//sheet.setColumnWidth(9, 15 * 256);
		
		//Head部分颜色字体
		XSSFFont font = workbook.createFont();
		//加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//字体大小
		font.setFontHeightInPoints((short)16);
		
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
		
		XSSFFont font_other = workbook.createFont();
		//字体大小
		font_other.setFontHeightInPoints((short)16);
		//式样
		XSSFCellStyle style_other = workbook.createCellStyle();
		style_other.setFont(font_other);
		
		WarehouserptDto warehouserpt = new WarehouserptDto();
		//添加数据
		warehouserpt = (WarehouserptDto) datas.get(0);
		XSSFRow row1 = sheet.createRow(3);
		XSSFCell cell_no = row1.createCell(0);
		cell_no.setCellValue("出库单号：" + warehouserpt.getWarehouseno());
		cell_no.setCellStyle(style_other);
		XSSFRow row2 = sheet.createRow(4);
		XSSFCell cell_cus = row2.createCell(0);
		XSSFCell cell_del = row2.createCell(4);
		cell_cus.setCellValue("客户：" + warehouserpt.getSuppliername());
		cell_cus.setCellStyle(style_other);
		XSSFCell cell_date = row2.createCell(7);
		cell_date.setCellValue("发货日期：" + warehouserpt.getShowWarehousedate());
		cell_date.setCellStyle(style_other);
		/*
		if (warehouserpt.getExpressname()!=null)
			cell_del.setCellValue("快递：" + warehouserpt.getExpressname());
		XSSFRow row3 = sheet.createRow(5);
		XSSFCell cell_cus_add = row3.createCell(0);
		XSSFCell cell_del_add = row3.createCell(4);
		//XSSFCell cell_sum = row3.createCell(8);
		cell_cus_add.setCellValue("客户地址：" + warehouserpt.getSupplieraddress());
		if (warehouserpt.getExpressname()!=null)
			cell_del_add.setCellValue("快递公司地址：" + warehouserpt.getExpressname());
		//cell_sum.setCellValue("转运费用合计：" + warehouserpt.getExpresstaxamount());
		XSSFRow row4 = sheet.createRow(6);
		XSSFCell cell_cus_mng = row4.createCell(0);
		XSSFCell cell_del_mng = row4.createCell(4);
		cell_cus_mng.setCellValue("联系人：" + warehouserpt.getSuppliermanager());
		if (warehouserpt.getExpressname()!=null)
			cell_del_mng.setCellValue("联系人：" + warehouserpt.getExpressmanager());
		XSSFRow row5 = sheet.createRow(7);
		XSSFCell cell_cus_tel = row5.createCell(0);
		XSSFCell cell_del_tel = row5.createCell(4);
		cell_cus_tel.setCellValue("联系人电话：" + warehouserpt.getSuppliertel());
		if (warehouserpt.getExpressname()!=null)
			cell_del_tel.setCellValue("联系人电话：" + warehouserpt.getExpresstel());
		XSSFRow row6 = sheet.createRow(8);
		XSSFCell cell_cus_fax = row6.createCell(0);
		XSSFCell cell_del_fax = row6.createCell(4);
		XSSFCell cell_date = row6.createCell(7);
		cell_cus_fax.setCellValue("联系人传真：" + warehouserpt.getSupplierfax());
		if (warehouserpt.getExpressname()!=null)
			cell_del_fax.setCellValue("联系人传真：" + warehouserpt.getExpressfax());
		cell_date.setCellValue("发货日期：" + warehouserpt.getShowWarehousedate());
		XSSFRow row7 = sheet.createRow(9);
		XSSFCell cell_cus_mail = row7.createCell(0);
		XSSFCell cell_del_mail = row7.createCell(4);
		cell_cus_mail.setCellValue("联系人信箱：" + warehouserpt.getSuppliermail());
		if (warehouserpt.getExpressname()!=null)
			cell_del_mail.setCellValue("联系人信箱：" + warehouserpt.getExpressmail());
*/
		
		XSSFRow row8 = sheet.createRow(6);
		XSSFCell cell_detail = row8.createCell(0);
		cell_detail.setCellValue("出库单明细：");
		cell_detail.setCellStyle(style_other);
		
		XSSFRow row = sheet.createRow(7);
		XSSFCell cell = null;
		for(int i = 0; i < heads.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(heads.get(i));
			cell.setCellStyle(style);
		}
	}
}
