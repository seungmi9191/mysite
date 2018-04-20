package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;


@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("guestcontroller 진입");
		
		String actionName=request.getParameter("g");
		System.out.println("g의 값은: " + actionName);
		
		
		//
		if("guestbook".equals(actionName)) { //방명록 폼 불러오기
			WebUtil.redirect(request, response, "/mysite/guest");
		
		} else if("add".equals(actionName)) {
			//add
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String content=request.getParameter("content");
			//String regDate=request.getParameter("regDate");
			
			GuestVo vo = new GuestVo(name, password, content, null);
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			//add redirect
			response.sendRedirect("/mysite/guest");
			System.out.println("얘는 add의 list");
		
			
		} else if("delete".equals(actionName)) {
			
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			String pw_input = request.getParameter("pw_input");
			System.out.println(no + password+pw_input);
			
			if (password.equals(pw_input)) {
				GuestbookDao dao = new GuestbookDao();
				dao.list_delete(no);				
				response.sendRedirect("/mysite/guest");
			} else {
				System.out.println("패스워드가 맞지 않습니다.");
				WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			}
			
			//delete redirect
			System.out.println("얘는 delete의 list");
			
		} else if("deleteform".equals(actionName)) {
			
			//deleteform
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			
		} else {
			//list
			//List<GuestVo> list = new ArrayList<GuestVo>();
			GuestbookDao dao = new GuestbookDao();
			List<GuestVo> list = dao.getList();
			
			request.setAttribute("list", list);
			System.out.println(list);
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
