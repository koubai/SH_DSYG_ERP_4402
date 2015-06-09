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
import com.cn.dsyg.service.CalendarService;

public class JsonServlet extends HttpServlet {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = -378077189081071453L;
	private CalendarService calendarService;

	public ApplicationContext ctx;

    public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}


    public JsonServlet() {
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
		System.out.println("JsonServlet doPost");

		
		calendarService = (CalendarService)ctx.getBean("calendarService");
		calendarService.setCtx(ctx);    	
		List<CalendarDto> list = calendarService.find();
		String json = JSONSerializer.toJSON(list).toString();
		out.print(json);
		//值得一提的是，在返回的json数据中，每个字段如id，title..对应着FullCalendar的Event Object事件对象中的属性id，title..。
	}

}
