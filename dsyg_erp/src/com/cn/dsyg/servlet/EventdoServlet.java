package com.cn.dsyg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.dsyg.dto.CalendarDto;
import com.cn.dsyg.service.CalendarService;
import com.cn.common.util.DateUtil;

public class EventdoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5932608904016668602L;

	private CalendarService calendarService;
	public ApplicationContext ctx;

       
    public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	public EventdoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
        ServletContext servletContext = request.getSession().getServletContext();
    	ctx= WebApplicationContextUtils.getWebApplicationContext(servletContext);
		System.out.println("doPost");
		calendarService = (CalendarService)ctx.getBean("calendarService");
		calendarService.setCtx(ctx);    	
		if("add".equals(action)){
			System.out.println("Add");
			String events = request.getParameter("event");//事件内容
			String isallday = request.getParameter("isallday");//是否是全天事件
			String isend = request.getParameter("isend");//是否有结束时间
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String s_time = request.getParameter("s_hour") + ":" + request.getParameter("s_minute") + ":00";
			String e_time = request.getParameter("e_hour") + ":" + request.getParameter("e_minute") + ":00";
			String userColor = request.getParameter("userColor");
			String userId = request.getParameter("userId");
			
			String start = "";
			String end = "";
			if("1".equals(isallday) && "1".equals(isend)){
				start = startdate;
				end = enddate;
			}else if("1".equals(isallday) && isend == null){
				start = startdate;
			}else if(isallday == null && "1".equals(isend)){
				start = startdate + " " + s_time;
				end = enddate + " " + e_time;
				isallday = "0";
			}else {
				start = startdate + " " + s_time;
				isallday = "0";
			}
			
			//String[] colors = {"#360","#f30","#06c"};
			//int index = (int)(Math.random()*colors.length);
			CalendarDto calendar = new CalendarDto();
			calendar.setTitle(events);
			calendar.setStart(start);
			calendar.setEnd(end);
			calendar.setAllDay(Integer.parseInt(isallday));
			//calendar.setColor(colors[index]);
			System.out.println("events:" + events);
			calendar.setColor(userColor);
			calendar.setUserid(userId);
			boolean b = calendarService.add(calendar);

			if(b){
				out.print("1");
				session.setAttribute("userId",userId);

			}else {
				out.print("写入失败！");
			}
		}else if("edit".equals(action)) {
			System.out.println("Edit");
			Integer id =  Integer.parseInt(request.getParameter("id"));
			String userid = (String)session.getAttribute("user_id");
//			String userName = request.getParameter("userName");
			if (userCheck(id,userid)){
				String events = request.getParameter("event");//事件内容
				String isallday = request.getParameter("isallday");//是否是全天事件
				String isend = request.getParameter("isend");//是否有结束时间
				String startdate = request.getParameter("startdate");
				String enddate = request.getParameter("enddate");
				String s_time = request.getParameter("s_hour") + ":" + request.getParameter("s_minute") + ":00";
				String e_time = request.getParameter("e_hour") + ":" + request.getParameter("e_minute") + ":00";
				String userColor = request.getParameter("userColor");
				
				String start = "";
				String end = "";
				if("1".equals(isallday) && "1".equals(isend)){
					start = startdate;
					end = enddate;
				}else if("1".equals(isallday) && isend == null){
					start = startdate;
				}else if(isallday == null && "1".equals(isend)){
					start = startdate + " " + s_time;
					end = enddate + " " + e_time;
					isallday = "0";
				}else {
					start = startdate + " " + s_time;
					isallday = "0";
				}
				System.out.println("start:"+start);
				System.out.println("end"+end);
				System.out.println("isallday"+isallday);
				
				//String[] colors = {"#360","#f30","#06c"};
				//int index = (int)(Math.random()*colors.length);
				CalendarDto calendar = new CalendarDto();
				calendar.setTitle(events);
				calendar.setStart(start);
				calendar.setEnd(end);
				calendar.setAllDay(Integer.parseInt(isallday));
				//calendar.setColor(colors[index]);
				calendar.setColor(userColor);
				calendar.setId(id);
				boolean b = calendarService.modify(calendar);
				if(b){
					out.print("1");
				}else {
					out.print("写入失败！");
				}
			}else{
				out.print("不能修改他人事件！");
			}
			
		}else if("del".equals(action)){
			System.out.println("Del");
			if (request.getParameter("id") != null){
				Integer id =  Integer.parseInt(request.getParameter("id"));
				String userid = (String)session.getAttribute("user_id");
				System.out.println("id:"+request.getParameter("id"));
				System.out.println("userid:"+userid);
				//String userid = (String)session.getAttribute("user_id");
				//System.out.println("userName" + userName);
				if (userCheck(id,userid)){
					if(id > 0){
						boolean b = calendarService.del(id);
						if(b){
							out.print("1");
						}else {
							out.print("删除失败！");
						}
					}else {
						out.print("事件不存在！");
					}
				}else{
					out.print("不能删除他人事件！");
				}				
			} else {
				out.print("未创建事件！");
			}
		}else if("drag".equals(action)) {
			System.out.println("Drag");
			Integer id =  Integer.parseInt(request.getParameter("id"));
			String userid = (String)session.getAttribute("user_id");
			if (userCheck(id,userid)){
				Integer daydiff = Integer.parseInt(request.getParameter("daydiff")) * 24 * 60 * 60;
				Integer minudiff = Integer.parseInt(request.getParameter("minudiff")) * 60;
				String allday = request.getParameter("allday");
				CalendarDto calendar = calendarService.findById(id);
				String start = calendar.getStart();
				long lstart = DateUtil.string2long(start);				
				String end = calendar.getEnd();

				if("true".equals(allday)){
					System.out.println("allday:"+allday);
					System.out.println("start:"+start);
					System.out.println("end:"+end);
					if("".equals(end)){
						calendar.setStart(DateUtil.long2string(lstart + daydiff));
					}else {
						long lend = DateUtil.string2long(end);
						calendar.setStart(DateUtil.long2string(lstart + daydiff));
						calendar.setEnd(DateUtil.long2string(lend + daydiff));
					}
				}else {
					System.out.println("allday:"+allday);
					System.out.println("start:"+start);
					System.out.println("end:"+end);
					Integer difftime = daydiff + minudiff;
					if("".equals(end)){
						calendar.setStart(DateUtil.long2string(lstart + difftime));
					}else {
						long lend = DateUtil.string2long(end);
						calendar.setStart(DateUtil.long2string(lstart + difftime));
						calendar.setEnd(DateUtil.long2string(lend + difftime));
					}
				}
		//		System.out.println(sql);
				boolean b = calendarService.modify(calendar);
				if(b){
					out.print("1");
				}else {
					out.print("拖拽失败！");
				}
			}else{
				out.print("不能拖拽他人事件！");
			}
			
		}else if("resize".equals(action)){
			System.out.println("Resize");

			Integer id =  Integer.parseInt(request.getParameter("id"));
			String userid = (String)session.getAttribute("user_id");
			if (userCheck(id,userid)){
				Integer daydiff = Integer.parseInt(request.getParameter("daydiff")) * 24 * 60 * 60;
				Integer minudiff = Integer.parseInt(request.getParameter("minudiff")) * 60;
				CalendarDto calendar = calendarService.findById(id);
				String start = calendar.getStart();
				long lstart = DateUtil.string2long(start);
				String end = calendar.getEnd();
				Integer difftime = daydiff + minudiff;
				if("".equals(end)){
					calendar.setEnd(DateUtil.long2string(lstart + difftime));
				}else {
					long lend = DateUtil.string2long(end);
					calendar.setEnd(DateUtil.long2string(lend + difftime));
				}
		//		System.out.println(sql);
				boolean b = calendarService.modify(calendar);
				if(b){
					out.print("1");
				}else {
					out.print("缩放失败！");
				}
			}else{
				out.print("不能改变他人事件！");
			}
			
		}
	}
	
	private boolean userCheck(Integer id, String userid){
		System.out.println("UserCheck");
		System.out.println("id:" + String.valueOf(id));
		System.out.println("userid:" + userid);
		try{
			CalendarDto calendar = calendarService.findById(id);
			String chkUser = calendar.getUserid();
			System.out.println("chkUser:" + chkUser);
			if (userid.equals(chkUser)){
				return true;
			}
		} catch (Exception e){			
			System.out.println(e);
		}
		return false;
	}
}
