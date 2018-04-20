package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			String actionName = request.getParameter("a");
			
			if("joinform".equals(actionName)) { //회원가입 폼
				WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
			
			} else if("join".equals(actionName)) { //회원정보 저장
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				
				
				UserVo vo = new UserVo(name, email, password, gender);
				System.out.println(vo.toString());
				
				UserDao userDao = new UserDao();
				userDao.insert(vo);
				
				//joinsuccess로 보내기
				WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
			
			}else if("loginform".equals(actionName)) { //로그인 폼
				WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			
			} else if("login".equals(actionName)) {
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				
				UserDao userDao = new UserDao();
				UserVo userVo = userDao.getUser(email, password);
				
				if(userVo==null) {//로그인 실패
					System.out.println("로그인 실패");
					
					//다시 로그인하라고 되돌릴거
					WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
					
				} else { //로그인 성공
					System.out.println("로그인 성공");
					HttpSession session=request.getSession();
					session.setAttribute("authUser", userVo);
					
					WebUtil.redirect(request, response, "/mysite/main");
				} 
				
			} else if("logout".equals(actionName)) {
				HttpSession session = request.getSession();
				session.removeAttribute("authUser");
				session.invalidate();
				
				WebUtil.redirect(request, response, "/mysite/main");
				
			} else if("modifyform".equals(actionName)) {
				//세션을 받아옴 (회원정보수정은 이미 로그인 상태일 때 가능하기 때문에)
				HttpSession session = request.getSession();
				//세션 값을 빼옴
				UserVo authUser = (UserVo)session.getAttribute("authUser");
				//Dao에서 회원의 전체 정보를 받아옴
				//System.out.println(authUser.getNo());
				UserDao userDao = new UserDao();
				//Dao의 값을 세션에 넣어줌
				authUser = userDao.getUser(authUser.getNo());
				//forward할 때 request에 담아 넘겨줌
				request.setAttribute("authUser", authUser);
				System.out.println(authUser.getEmail());
				
				WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			
			} else if("modify".equals(actionName)) {
				String no = request.getParameter("no");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				System.out.println(no);
				UserVo userVo = new UserVo(Integer.parseInt(no),name,null,password,gender);
				
				/*UserVo userVo = new UserVo();
				userVo.setNo(Integer.parseInt(no));
				userVo.setName(name);
				userVo.setPassword(password);
				userVo.setGender(gender);*/
				
				System.out.println(userVo);
				
				UserDao userDao = new UserDao();
				boolean flag = userDao.update(userVo);
				
				if(flag) {
					HttpSession session = request.getSession();
					UserVo authUser = (UserVo)session.getAttribute("authUser");
					authUser.setName(name);
					session.setAttribute("authUser", authUser);
				}
				
				WebUtil.redirect(request, response, "/mysite/main");

			}
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
