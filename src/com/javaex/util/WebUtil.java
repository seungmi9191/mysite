package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	
	public static void forward(HttpServletRequest request, 
							   HttpServletResponse response, String path) throws ServletException, IOException { //path는 jsp 파일 위치
		RequestDispatcher rd = request.getRequestDispatcher(path); //요청 받은거
		rd.forward(request, response); //요청 보낼거
	}
	
	public static void redirect(HttpServletRequest request, 
								HttpServletResponse response, String url) throws IOException {
		response.sendRedirect(url);
		
	}
}
