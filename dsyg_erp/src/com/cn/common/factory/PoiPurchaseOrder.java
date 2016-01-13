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
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.opensymphony.xwork2.ActionContext;

public class PoiPurchaseOrder extends PoiSalesPrice {

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
	public void toHtml(HttpServletResponse response, PurchaseDto updPurchaseDto, List<PurchaseItemDto> updPurchaseItemList) throws IOException {
		String salesprice = "page/salespriceorder.html";
		String filePath = "";
		try {
			filePath = PoiPurchaseOrder.class.getClassLoader().getResource("").toURI().getPath();
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
        String res03 = "";
        if(StringUtil.isNotNull(updPurchaseDto.getRes03())){
        	res03 = updPurchaseDto.getRes03();
        }
        
        templateContent = templateContent.replaceAll("#customername#", updPurchaseDto.getSuppliername());
        templateContent = templateContent.replaceAll("#customermanager#", updPurchaseDto.getSuppliermanager());
        templateContent = templateContent.replaceAll("#namefrom#", (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME));
        templateContent = templateContent.replaceAll("#faxto#", updPurchaseDto.getSupplierfax());
        templateContent = templateContent.replaceAll("#date#", str_date);
        templateContent = templateContent.replaceAll("#telto#", updPurchaseDto.getSuppliertel());
        templateContent = templateContent.replaceAll("#amount#", updPurchaseDto.getTotalamount().toString());
        templateContent = templateContent.replaceAll("#res01#", dictMap.get(Constants.DICT_PAY_TYPE + "_" + updPurchaseDto.getRes01()));
        templateContent = templateContent.replaceAll("#res03#", res03);
        templateContent = templateContent.replaceAll("#orderid#", updPurchaseDto.getTheme2());
        templateContent = templateContent.replaceAll("#indexnote#", updPurchaseDto.getNote());
        
        StringBuilder products = new StringBuilder(" ");
        for (int i = 0; i < updPurchaseItemList.size(); i++) {  
        	products.append("<tr>");   
            products.append("<td style=\"border:solid; border-width:0px 1px 1px 1px;text-align:center\">");   
            products.append(updPurchaseItemList.get(i).getTradename());  
            products.append("</td>"); 
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;\">"); 
        	if(updPurchaseItemList.get(i).getTypeno() != null){
	            products.append(updPurchaseItemList.get(i).getTypeno());  
        	}
            products.append("</td>"); 
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;text-align:center\">"); 
            products.append(dictMap.get(Constants.DICT_COLOR_TYPE + "_" + updPurchaseItemList.get(i).getColor()));  
            products.append("</td>");
            products.append("<td style=\"border:solid; border-width:0px 0px 1px 1px;text-align:center\">"); 
            products.append(updPurchaseItemList.get(i).getUnitprice());  
            products.append("</td>");
            products.append("<td style=\"border:solid; border-width:0px 1px 1px 1px;text-align:center\">"); 
        	if(updPurchaseItemList.get(i).getQuantity() != null){
	            products.append(updPurchaseItemList.get(i).getQuantity());  
        	}
            products.append("</td>");
            products.append("<td style=\"border:solid; border-width:0px 1px 1px 0px;text-align:center\">"); 
        	if(updPurchaseItemList.get(i).getAmount() != null){
	            products.append(updPurchaseItemList.get(i).getAmount());  
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
			path = PoiPurchaseOrder.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path.replace("WEB-INF/classes/", "");
		System.out.println(path + salesprice);
	}
}
