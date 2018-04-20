<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.javaex.vo.UserVo"%>
<%@page import="java.util.List"%>
<%@page import="com.javaex.vo.GuestVo" %>

 <!-- attribute에 담겨있는 list값 꺼내오기 -> 꺼낸 값을 list에 넣음 -->
    <%
    	List<GuestVo> list = (List<GuestVo>)request.getAttribute("list"); 
    
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<title>방명록</title>
</head>
<body>

	<div id="container">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<!-- /header -->

		<!-- navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<!-- /navigation -->

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					<form action="/mysite/guest" method="get">
						<table>
							<tr><input type="hidden" name="g" value="add"><br></tr>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" value="" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password"/></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right>
								<input type="submit" value="확인"/></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
						 <%
	 						for(GuestVo vo : list) {
	 					 %>
							<table width=510 border=1>
								<tr>
									<td><%=vo.getNo()%></td>
									<td><%=vo.getName() %></td>
									<td><%=vo.getRegDate() %></td>
									<td><button type="button" onclick="location.href='/mysite/guest?g=deleteform&no=<%=vo.getNo()%>&password=<%=vo.getPassword()%>'">삭제</button></td>
								</tr>
								<tr>
									<td colspan=4><%=vo.getContent() %></td>
								</tr>
							</table>
							<br/>
							<%
	 						}
      						%>
      						<br/>
						</li>
					</ul>
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- footer -->

	</div>
	<!-- /container -->

</body>
</html>