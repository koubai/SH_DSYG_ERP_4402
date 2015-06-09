package com.cn.dsyg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dao.impl.UserDaoImpl;
import com.cn.dsyg.dto.UserDto;

@WebServlet("/RegisterServlet")
public class RegisterServlet  extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7900060531473301184L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userColor = request.getParameter("userColor");
		UserDao userDao= new UserDaoImpl();
		UserDto newUser= new UserDto();
		
//		newUser.setUserId(userName);
//		newUser.setColor(userColor);
		
		userDao.insertUser(newUser);
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}
}
