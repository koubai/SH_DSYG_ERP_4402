package com.cn.dsyg.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dao.impl.UserDaoImpl;
import com.cn.dsyg.dto.UserDto;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1359683184348570612L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		
		HttpSession session = request.getSession();
		UserDao userDao= new UserDaoImpl();
		UserDto searchUser = new UserDto();

		searchUser = userDao.queryUserByID(userId);
		
		if (searchUser != null )
		{
			session.setAttribute("userName",searchUser.getUsername());
			session.setAttribute("userColor",searchUser.getColor());
			response.sendRedirect("index.jsp");
		}else
		{
			response.sendRedirect("login.jsp?error="+userId);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}
}