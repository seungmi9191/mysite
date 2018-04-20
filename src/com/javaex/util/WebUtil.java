package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	
	public static void forward(HttpServletRequest request, 
							   HttpServletResponse response, String path) throws ServletException, IOException { //path�� jsp ���� ��ġ
		RequestDispatcher rd = request.getRequestDispatcher(path); //��û ������
		rd.forward(request, response); //��û ������
	}
	
	public static void redirect(HttpServletRequest request, 
								HttpServletResponse response, String url) throws IOException {
		response.sendRedirect(url);
		
	}
}
