<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<title>삭제화면</title>
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
				<div id="guestbook" class="delete-form">
					<form method="post" action="/mysite/guest?g=delete">
						<input type='hidden' name="no" value=<%=request.getParameter("no")%>> 
						<input type='hidden' name="password" value=<%=request.getParameter("password")%>>
						<table>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pw_input"></td>
								<td><input type="submit" value="확인"></td>
							</tr>
						</table>
					</form>
						<a href="./main">메인으로 돌아가기</a>
				</div>
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
