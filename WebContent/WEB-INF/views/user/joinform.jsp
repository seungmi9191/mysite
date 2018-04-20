<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
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
					<form id="joinform" name="joinform" method="post" action="/mysite/user">
						<input type="text" name="a" value="join"> <!--a 확인용=나중에 hidden으로 변경하기 -->
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="">
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						<input type="button" value="id 중복체크">
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!-- footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- footer -->
		
	</div> <!-- /container -->

</body>
</html>