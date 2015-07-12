package com.cn.common.factory;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel2007操作类基类
 * @author Frank
 * @time 2013-10-3下午3:32:06
 * @version 1.0
 */
public class Poi2007Base {
	
	private static final Logger log = LogManager.getLogger(Poi2007Base.class);
	
	/**
	 * 字典表记录
	 */
	protected Map<String, String> dictMap;

	/**
	 * 文件路径
	 */
	protected String path;
	
	/**
	 * 文件名
	 */
	protected String name;
	
	/**
	 * Sheet名
	 */
	protected String sheetName;
	
	/**
	 * 数据标题
	 */
	protected String title;
	
	/**
	 * 数据头部
	 */
	protected List<String> heads;
	
	/**
	 * 数据列表
	 */
	protected List<?> datas;
	
	/**
	 * 创建Excel2007
	 */
	public void createExcel() {
		OutputStream os = null;
		try {
			//创建Workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			//创建Sheet
			XSSFSheet sheet = createSheet(workbook);
			//输出title
			writeTitle(sheet, workbook);
			//输出Head部分
			writeHead(sheet, workbook);
			//输出数据部分
			writeData(sheet, workbook);
			
			//输出Excel
			os = new FileOutputStream(path + name);
			workbook.write(os);
			log.info("createExcel fileName=[" + path + name + "] success.");
		} catch (Exception e) {
			log.error("createExcel fileName=[" + path + name + "] error:" + e);
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				log.error("createExcel close os error:" + e);
			}
		}
	}
	
	/**
	 * 导出Excel2007
	 * @param out
	 */
	public void exportExcel(OutputStream out) {
		try {
			//创建Workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			//创建Sheet
			XSSFSheet sheet = createSheet(workbook);
			//输出title
			writeTitle(sheet, workbook);
			//输出Head部分
			writeHead(sheet, workbook);
			//输出数据部分
			writeData(sheet, workbook);
			
			//输出Excel
			out.flush();
			workbook.write(out);
			out.close();
			log.info("exportExcel success.");
		} catch (Exception e) {
			log.error("exportExcel error:" + e);
		}
	}
	
	/**
	 * 创建Sheet
	 * @param workbook
	 */
	public XSSFSheet createSheet(XSSFWorkbook workbook) {
		return workbook.createSheet(sheetName);
	}
	
	/**
	 * 输出大标题
	 * @param sheet
	 */
	public void writeTitle(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = sheet.createRow(1);
		//式样
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		row.createCell(0).setCellValue(title);
	}
	
	/**
	 * 输出Head部分
	 * @param sheet
	 */
	public void writeHead(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = sheet.createRow(2);
		for(int i = 0; i < heads.size(); i++) {
			row.createCell(i).setCellValue(heads.get(i));
		}
	}
	
	/**
	 * 输出数据部分
	 * @param sheet
	 */
	public void writeData(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = null;
		for(int i = 0; i < datas.size(); i++) {
			row = sheet.createRow(i + 3);
			for(int j = 0; j < heads.size(); j++) {
				row.createCell(j).setCellValue(heads.get(j));
			}
		}
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getHeads() {
		return heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public Map<String, String> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<String, String> dictMap) {
		this.dictMap = dictMap;
	}
}
