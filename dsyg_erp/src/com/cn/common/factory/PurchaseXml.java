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
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;


public class PurchaseXml {
	/**
	 * 字典表记录
	 */
	protected Map<String, String> dictMap;
	
	public void exportXml(OutputStream out, PurchaseDto updPurchaseDto, List<PurchaseItemDto> updPurchaseItemList) {
		String template = "page/template.xml";
		String filePath = "";
		try {
			filePath = PoiSalesPrice.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filePath = filePath.replace("WEB-INF/classes/", "");
        
	    String fileName = filePath + template;
	    System.out.println(fileName);
	    try {
	        SAXReader reader = new SAXReader();
	        Document doc = reader.read(fileName); //加载xml文件
	
	        Element no = (Element) doc.selectSingleNode("//my:N0");
	        if(StringUtil.isNotBlank(updPurchaseDto.getTheme2())){
	        	no.setText(updPurchaseDto.getTheme2());
	        }
	
	        Element date = (Element) doc.selectSingleNode("//my:DATE");
	        if(StringUtil.isNotBlank(updPurchaseDto.getShowPurchasedate())){
	        	date.setText(updPurchaseDto.getShowPurchasedate());
	        }
	        
	        Node des01 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-01"); 
	        if(StringUtil.isNotBlank(updPurchaseItemList.get(0).getItem11())){
	        	des01.setText(updPurchaseItemList.get(0).getItem11());
	        }
	        
	        Node des02 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-02"); 
	        des02.setText(updPurchaseItemList.get(0).getTradename() + " " + updPurchaseItemList.get(0).getTypeno());
	        
	        Node color = doc.selectSingleNode("//my:group1/my:group2/my:COLOR"); 
	        color.setText(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updPurchaseItemList.get(0).getColor() + "_e"));
	        
	        Node qty = doc.selectSingleNode("//my:group1/my:group2/my:QTY"); 
	        if(updPurchaseItemList.get(0).getQuantity() != null){
	        	qty.setText(updPurchaseItemList.get(0).getQuantity().toString());
	        }
	        
	        Node unitprice = doc.selectSingleNode("//my:group1/my:group2/my:UNIT_PRICE"); 
	        if(updPurchaseItemList.get(0).getUnitprice() != null){
	        	unitprice.setText(updPurchaseItemList.get(0).getUnitprice().toString());
	        }

	        String str_des01 = "";
	        String str_qty = "";
	        String str_unitprice = "";
	        String str_color = "";
	        for (int i = 1; i < updPurchaseItemList.size(); i++) {  
		        Element group1 = (Element) doc.selectSingleNode("//my:group1");
		        
		        if(StringUtil.isNotBlank(updPurchaseItemList.get(i).getItem11())){
		        	str_des01 = updPurchaseItemList.get(i).getItem11();
		        }
		        if(updPurchaseItemList.get(i).getQuantity() != null){
		        	str_qty = updPurchaseItemList.get(i).getQuantity().toString();
		        }
		        if(updPurchaseItemList.get(i).getUnitprice() != null){
		        	str_unitprice = updPurchaseItemList.get(i).getUnitprice().toString();
		        }
		        if(StringUtil.isNotBlank(updPurchaseItemList.get(i).getColor())){
		        	str_color = dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updPurchaseItemList.get(i).getColor() + "_e");
		        }
		        
		        group1.addElement("my:group2").addElement("my:DESCRIPTION-01").addText(str_des01);
		        
		        Element group = (Element) doc.selectNodes("//my:group1/my:group2").get(i); 
		        group.addElement("my:DESCRIPTION-02").addText(updPurchaseItemList.get(i).getTradename() + " " + updPurchaseItemList.get(i).getTypeno());
		        group.addElement("my:COLOR").addText(str_color);
		        group.addElement("my:QTY").addText(str_qty);
		        group.addElement("my:UNIT_PRICE").addText(str_unitprice);
		        group.addElement("my:AMOUNT").addAttribute("xsi:nil", "true");
	        }
	
	        String strnote = " ";
	        if(StringUtil.isNotBlank(updPurchaseDto.getNote())){
	        	strnote = updPurchaseDto.getNote().toString();
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
