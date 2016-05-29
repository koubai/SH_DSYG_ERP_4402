package com.cn.dsyg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONSerializer;

import com.cn.dsyg.dto.CalendarDto;
import com.cn.dsyg.dto.IssueDto;
import com.cn.dsyg.service.CalendarService;
import com.cn.dsyg.service.IssueService;

public class IssueServlet extends HttpServlet {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = -178791024848587483L;

	private IssueService issueService;

	public ApplicationContext ctx;

    public IssueService getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}

    public IssueServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        ServletContext servletContext = request.getSession().getServletContext();
    	ctx= WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		System.out.println("IssueServlet doPost");
		issueService = (IssueService)ctx.getBean("issueService");
		issueService.setCtx(ctx);    	
		List<IssueDto> list = issueService.queryIssueWorking();
		String json = JSONSerializer.toJSON(list).toString();
//		System.out.println(json);
		out.print(json);
	}

}
