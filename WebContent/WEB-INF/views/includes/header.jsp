<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.javaex.vo.UserVo"%>

<!-- 세션에 접근하기 -->
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser"); //어떤 자료형인지 안정해줘서 다운캐스팅
	//주소를 변수 authUser에 담고 주소를 따라가면  그 주소 자리에 (왼쪽)UserVo라는 애가 있음 그 안의 주소값에 있는 내용으로 로그인 파악
%>

<div id="header">
	<h1>
		<a href="/mysite/main">MySite</a>
	</h1>
	<ul>

		<%
				if(authUser==null) {
		%>
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
		<%			
				}else {
		%>
				<!-- 로그인 후 -->
				<li><a href="/mysite/user?a=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
				<li><font color="green"><b><%=authUser.getName()%></font></b>님</li>
		<%
				}
		%>
	</ul>
</div>
<!-- /header -->