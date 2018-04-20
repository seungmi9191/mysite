<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.javaex.vo.UserVo"%>
<%
	UserVo authUser = (UserVo)request.getAttribute("authUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet"
	type="text/css">
<title>수정폼</title>
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
				<div id="user">

					<form id="modify" name="modify" method="get" action="/mysite/user">
						<input type="text" name="a" value="modify" /> 
						<input type="hidden" name="no" value="<%=authUser.getNo()%>" />
						<label class="block-label" for="name">이름</label> 
						<input id="name" name="name" type="text" value="<%=authUser.getName()%>" /> 
						<label class="block-label" for="email">이메일</label> 
						<strong><%=authUser.getEmail()%></strong>
						<label class="block-label">패스워드</label> 
							<input type="password" name="password" value="<%=authUser.getPassword()%>" />

						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" <%=authUser.getGender().equals("female")?"checked":""%>>
							<label>남</label> <input type="radio" name="gender" value="male"<%=authUser.getGender().equals("male")?"checked":""%> >

						</fieldset>

						<input type="submit" value="수정완료">

					</form>
				</div>
				<!-- /user -->
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
