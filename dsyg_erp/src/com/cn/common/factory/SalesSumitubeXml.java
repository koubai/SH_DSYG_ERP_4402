package com.cn.common.factory;

import java.util.*;
import java.io.*;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.*;
import org.dom4j.io.*;

import com.cn.common.util.Constants;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;


public class SalesSumitubeXml {
	/**
	 * 字典表记录
	 */
	protected Map<String, String> dictMap;
	
	public void exportXml(OutputStream out, SalesDto updSalesDto, List<SalesItemDto> updSalesItemList) {
		String template = "page/template_sumitube.xml";
		String filePath = "";
		try {
			filePath = PoiSalesPrice.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filePath = filePath.replace("WEB-INF/classes/", "");
        
	    String fileName = filePath + template;
	    System.out.println("filename=:"+fileName);
	    try {
	        SAXReader reader = new SAXReader();
	        Document doc = reader.read(fileName); //加载xml文件
	
	        if (doc==null){
	    	    System.out.println("template file not exist");
	        	return;
	        }
	        Element no = (Element) doc.selectSingleNode("//my:N0");
	        if(StringUtil.isNotBlank(updSalesDto.getTheme2())){
	        	no.setText(updSalesDto.getTheme2());
	        }
	
	        Element date = (Element) doc.selectSingleNode("//my:DATE");
	        if(StringUtil.isNotBlank(updSalesDto.getShowBookdate())){
	        	date.setText(updSalesDto.getShowBookdate());
	        }
	
	        String str_area = "";
	        if(StringUtil.isNotBlank(updSalesItemList.get(0).getMakearea())){
	        	str_area = dictMap.get(Constants.DICT_MAKEAREA + "_" + updSalesItemList.get(0).getMakearea());
	        	if(str_area == null)
	        		str_area = "";
	        }
	        Element area = (Element) doc.selectSingleNode("//my:AREA");
	        area.setText(str_area);
	        
	        Node des01 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-01"); 
	        if(StringUtil.isNotBlank(updSalesItemList.get(0).getTradename())){
	        	des01.setText(updSalesItemList.get(0).getTradename());
	        }
	        
	        Node des02 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-02"); 
	        des02.setText(updSalesItemList.get(0).getTypeno());

	        String color_index0 = "";
	        if(StringUtil.isNotBlank(updSalesItemList.get(0).getColor())){
	        	color_index0 = dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updSalesItemList.get(0).getColor());
	        	if (color_index0 == null)
	        		color_index0 = "";
	        }
	        Node color = doc.selectSingleNode("//my:group1/my:group2/my:COLOR");
	        color.setText(color_index0);
	        
	        Node qty = doc.selectSingleNode("//my:group1/my:group2/my:QTY"); 
	        if(updSalesItemList.get(0).getQuantity() != null){
	        	qty.setText(updSalesItemList.get(0).getQuantity().toString());
	        }
	        
	        Node unitprice = doc.selectSingleNode("//my:group1/my:group2/my:UNIT_PRICE"); 
	        if(updSalesItemList.get(0).getUnitprice() != null){
	        	unitprice.setText(updSalesItemList.get(0).getUnitprice().toString());
	        }

	        String str_des01 = "";
	        String str_qty = "";
	        String str_unitprice = "";
	        String str_color = "";
	        for (int i = 1; i < updSalesItemList.size(); i++) {  
		        Element group1 = (Element) doc.selectSingleNode("//my:group1");
		        
		        if(StringUtil.isNotBlank(updSalesItemList.get(i).getTradename())){
		        	str_des01 = updSalesItemList.get(i).getTradename();
		        }
		        if(updSalesItemList.get(i).getQuantity() != null){
		        	str_qty = updSalesItemList.get(i).getQuantity().toString();
		        }
		        if(updSalesItemList.get(i).getUnitprice() != null){
		        	str_unitprice = updSalesItemList.get(i).getUnitprice().toString();
		        }
		        if(StringUtil.isNotBlank(updSalesItemList.get(i).getColor())){
		        	str_color = dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updSalesItemList.get(i).getColor());
		        	if (str_color == null)
		        		str_color = "";
		        }
		        
		        group1.addElement("my:group2").addElement("my:DESCRIPTION-01").addText(str_des01);
		        
		        Element group = (Element) doc.selectNodes("//my:group1/my:group2").get(i); 
		        group.addElement("my:DESCRIPTION-02").addText(updSalesItemList.get(i).getTypeno());
		        group.addElement("my:COLOR").addText(str_color);
		        group.addElement("my:QTY").addText(str_qty);
		        group.addElement("my:UNIT_PRICE").addText(str_unitprice);
		        group.addElement("my:AMOUNT").addAttribute("xsi:nil", "true");
	        }

	        String strnote = " ";
	        if(StringUtil.isNotBlank(updSalesDto.getNote())){
	        	strnote = updSalesDto.getNote().toString();
	        }
	        Element note = (Element) doc.selectSingleNode("//my:备注");
	        note.setText(strnote);

            OutputFormat format = new OutputFormat("    ", true); 
            format.setEncoding("GB2312"); 
            XMLWriter xmlWriter = new XMLWriter(out, format); 
            xmlWriter.write(doc); 
            xmlWriter.close();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}  

	public Map<String, String> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<String, String> dictMap) {
		this.dictMap = dictMap;
	}

}
