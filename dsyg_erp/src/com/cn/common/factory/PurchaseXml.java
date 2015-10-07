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
	
	public void modifyXml(PurchaseDto updPurchaseDto, List<PurchaseItemDto> updPurchaseItemList) {
		String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
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
	        no.setText(updPurchaseDto.getTheme2());
	
	        Element date = (Element) doc.selectSingleNode("//my:DATE");
	        date.setText(updPurchaseDto.getShowPurchasedate());
	        
	        Node des01 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-01"); 
	        des01.setText(updPurchaseItemList.get(0).getTradename());
	        
	        Node des02 = doc.selectSingleNode("//my:group1/my:group2/my:DESCRIPTION-02"); 
	        des02.setText(updPurchaseItemList.get(0).getTypeno());
	        
	        Node color = doc.selectSingleNode("//my:group1/my:group2/my:COLOR"); 
	        color.setText(updPurchaseItemList.get(0).getColor());
	        
	        Node qty = doc.selectSingleNode("//my:group1/my:group2/my:QTY"); 
	        qty.setText(updPurchaseItemList.get(0).getQuantity().toString());
	        
	        Node unitprice = doc.selectSingleNode("//my:group1/my:group2/my:UNIT_PRICE"); 
	        unitprice.setText(updPurchaseItemList.get(0).getUnitprice().toString());


	        for (int i = 1; i < updPurchaseItemList.size(); i++) {  
		        Element group1 = (Element) doc.selectSingleNode("//my:group1");
		        group1.addElement("my:group2").addElement("my:DESCRIPTION-01").addText(updPurchaseItemList.get(1).getTradename());
		        
		        Element group = (Element) doc.selectNodes("//my:group1/my:group2").get(i); 
		        group.addElement("my:DESCRIPTION-02").addText(updPurchaseItemList.get(i).getTypeno());
		        group.addElement("my:COLOR").addText(updPurchaseItemList.get(i).getColor());
		        group.addElement("my:QTY").addText(updPurchaseItemList.get(i).getQuantity().toString());
		        group.addElement("my:UNIT_PRICE").addText(updPurchaseItemList.get(i).getUnitprice().toString());
		        group.addElement("my:AMOUNT").addAttribute("xsi:nil", "true");
	        }
	
	        String strnote = " ";
	        if(StringUtil.isNotBlank(updPurchaseDto.getNote())){
	        	strnote = updPurchaseDto.getNote().toString();
	        }
	        Element note = (Element) doc.selectSingleNode("//my:备注");
	        note.setText(strnote);
	
	        //将上述改动保存到文件
	        FileWriter fileWriter = new FileWriter(pdf_path + "\\template.xml");
	
	        OutputFormat format = OutputFormat.createPrettyPrint(); //设置美观的缩进格式，便于阅读
	        //format = OutputFormat.createCompactFormat();//设置紧凑格式（消除多余空格），便于下载
	        XMLWriter writer = new XMLWriter(System.out);
	        writer.setWriter(fileWriter);
	        writer.write(doc);
	        writer.close();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}  
	
	/**
	 * 输出大标题
	 * @param sheet
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public void toXml(HttpServletResponse response) throws IOException {
		String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
		String template = "template.xml";
        String templateContent = "";
        
        FileInputStream fileinputstream = new FileInputStream(pdf_path + template);// 读取模板文件
        InputStreamReader read = new InputStreamReader(fileinputstream,"utf-8");
        BufferedReader reader = new BufferedReader(read); 
        String line;
        while ((line = reader.readLine()) != null) {       
        	templateContent += line;
        }
        fileinputstream.close();
        read.close();
        
		//输出
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.flush();
			pw.write(templateContent.toString());
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
