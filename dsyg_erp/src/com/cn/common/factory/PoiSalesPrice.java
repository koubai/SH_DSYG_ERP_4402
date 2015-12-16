package com.cn.common.factory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cn.common.util.Constants;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.opensymphony.xwork2.ActionContext;

public class PoiSalesPrice {

	/**
	 * 字典表记录
	 */
	protected Map<String, String> dictMap;
	
	/**
	 * 输出大标题
	 * @param sheet
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public void toHtml(HttpServletResponse response, SalesDto updSalesDto, List<SalesItemDto> updSalesItemList) throws IOException {
		String salesprice = "page/salesprice.html";
		String filePath = "";
		try {
			filePath = PoiSalesPrice.class.getClassLoader().getResource("").toURI().getPath();
	        System.out.print(filePath);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filePath = filePath.replace("WEB-INF/classes/", "");
		System.out.println(filePath + salesprice);
        String templateContent = "";
        
        FileInputStream fileinputstream = new FileInputStream(filePath + salesprice);// 读取模板文件
        InputStreamReader read = new InputStreamReader(fileinputstream,"utf-8");
        BufferedReader reader = new BufferedReader(read); 
        String line;
        while ((line = reader.readLine()) != null) {       
        	templateContent += line;
        }
        fileinputstream.close();
        read.close();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = dateFormat.format(new Date());

        templateContent = templateContent.replaceAll("#customername#", updSalesDto.getCustomername());
        templateContent = templateContent.replaceAll("#customermanager#", updSalesDto.getCustomermanager());
        templateContent = templateContent.replaceAll("#fromname#", (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME));
        templateContent = templateContent.replaceAll("#fax#", updSalesDto.getCustomerfax());
        templateContent = templateContent.replaceAll("#date#", str_date);
        templateContent = templateContent.replaceAll("#tel#", updSalesDto.getCustomertel());
        templateContent = templateContent.replaceAll("#dsyg#", updSalesDto.getTheme2());
        templateContent = templateContent.replaceAll("#res01#", dictMap.get(Constants.DICT_PAY_TYPE + "_" + updSalesDto.getRes01()));
        templateContent = templateContent.replaceAll("#res03#", updSalesDto.getRes03());
        templateContent = templateContent.replaceAll("#validdate#", updSalesDto.getRes04());
        templateContent = templateContent.replaceAll("#indexnote#", updSalesDto.getNote());
        
        StringBuilder products = new StringBuilder(" ");
        for (int i = 0; i < updSalesItemList.size(); i++) {  
    		//包装
    		String packaging = "";
    		if(StringUtil.isNotBlank(updSalesItemList.get(i).getPackaging())) {
    			if(Constants.DICT_PACKAGING_TYPE_CODE_1.equals(updSalesItemList.get(i).getPackaging())) {
    				packaging = " - 乱尺";
    			} else if(Constants.DICT_PACKAGING_TYPE_CODE_0.equals(updSalesItemList.get(i).getPackaging())) {
    				packaging = " - 整箱";
    			}
    		}
    		String typeno = "";
    		if(StringUtil.isNotBlank(updSalesItemList.get(i).getTypeno())) {
    			typeno = " - " + updSalesItemList.get(i).getTypeno();
    		}
    		String item01 = "";
    		if(StringUtil.isNotBlank(updSalesItemList.get(i).getItem01())) {
    			item01 = " - " + updSalesItemList.get(i).getItem01();
    		}
    		
        	products.append("<tr>");   
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;\">");  
//            products.append(updSalesItemList.get(i).getTradename() + typeno + item01 + packaging + " - " + 
//            		dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updSalesItemList.get(i).getColor()));  
            products.append(updSalesItemList.get(i).getTradename() + typeno + item01);  
            products.append("</td>"); 
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;\">"); 
        	if(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + updSalesItemList.get(i).getUnit()) != null){
	            products.append(dictMap.get(Constants.DICT_UNIT_TYPE + "_" + updSalesItemList.get(i).getUnit()));  
        	}
            products.append("</td>"); 
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;\">"); 
            products.append("￥" + updSalesItemList.get(i).getTaxunitprice());  
            products.append("</td>");
            products.append("<td style=\"border:solid; border-width:0px 1px 1px 1px; font-weight:bold\">"); 
        	if(updSalesItemList.get(i).getRes09() != null){
	            products.append(updSalesItemList.get(i).getRes09());  
        	}
            products.append("</td>");
            products.append("</tr>");  
        }  

        templateContent = templateContent.replaceAll("#products#", products.toString());
        
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
	public static void main(String[] args){
		String salesprice = "page/salesprice.html";
		String path = "";
		try {
			path = PoiSalesPrice.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path.replace("WEB-INF/classes/", "");
		System.out.println(path + salesprice);
	}
}
