<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.javaex.vo.UserVo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser"); //어떤 자료형인지 안정해줘서 다운캐스팅
	//주소를 변수 authUser에 담고 주소를 따라가면  그 주소 자리에 (왼쪽)UserVo라는 애가 있음 그 안의 주소값에 있는 내용으로 로그인 파악
%>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!-- header -->
		<c:import url ="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- /header -->

		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<!-- /navigation -->
		
		<div id="content">
			<div id="board">
				
				<form id="search_form" action="" method="post">
					<input type="hidden" name="" value="">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					
					<c:forEach items="${list}" var="vo">			
					<tr>
						<td>${vo.no}</td>
						<td><a href="/mysite/board?b=read&no=${vo.no}">${vo.title}</a></td>
						<td>${vo.userName}</td>
						<td>${vo.hit}</td>
						<td>${vo.regDate}</td>
						<td><c:if test="${authUser.no == vo.userNo}">
						<a href="/mysite/board?b=delete&no=${vo.no}" class="del">삭제</a></c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>	
					
				<c:if test="${authUser != null }">
					<div class="bottom">
						<a href="/mysite/board?b=writeform" id="new-book">글쓰기</a>
					</div>	
				</c:if>			
			</div>
		</div>
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->
		
	</div>
</body>
</html>