package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("b");
		System.out.println("게시판 진입");
		System.out.println("b의 값은 "+actionName);
		
		if("list".equals(actionName)) {
			//리스트 가져오기
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.getList();
			
			System.out.println(list.toString());
			
			//리스트 화면에 보내기
			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
		} else if("read".equals(actionName)) {
			//게시물 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			BoardVo boardVo = dao.getBoard(no);
			
			System.out.println(boardVo.toString());
			
			//게시물 화면에 보내기
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		
		} else if("modifyform".equals(actionName)) {
			//게시물 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			BoardVo boardVo = dao.getBoard(no);
			
			//게시물 화면에 보내기
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
		
		} else if("modify".equals(actionName)) {
			// 게시물 가져오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));
						
			BoardVo vo = new BoardVo(no, title, content);
			BoardDao dao = new BoardDao();
						
			dao.update(vo);
			
			WebUtil.redirect(request, response, "/mysite/board?a=list");
			
		} else if("writeform".equals(actionName)) {
			
			// 로그인 여부체크
			UserVo authUser = getAuthUser(request);
			if (authUser != null) { // 로그인했으면 작성페이지로
				WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			} else { // 로그인 안했으면 리스트로
				WebUtil.redirect(request, response, "/mysite/board?b=list");
			}
			
		} else if("write".equals(actionName)) {
			
			UserVo authUser = getAuthUser(request);
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = authUser.getNo();
			
			BoardVo vo = new BoardVo(title,content,userNo);
			BoardDao dao = new BoardDao();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/mysite/board?b=list");
		
		} else if("delete".equals(actionName)) {
			
			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			dao.delete(no);

			WebUtil.redirect(request, response, "/mysite/board?b=list");
		
		} else {
			WebUtil.redirect(request, response, "/mysite/board?b=list");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 로그인 되어 있는 정보를 가져온다.
		protected UserVo getAuthUser(HttpServletRequest request) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			return authUser;
		}

}
