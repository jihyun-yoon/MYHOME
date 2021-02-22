<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%-- <%@include file="/WEB-INF/views/inc/asset.jsp" %> --%>

<!-- <link rel="stylesheet" href="/Myhome_project/css/main.css"> -->
<link rel="stylesheet" href="/Myhome_project/css/template.css">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
	body, html {
		height: 100%;
		margin: 0 auto;
		padding: 0;
	}
	
	#header {
		background-color: white;
	}
	
	#header:hover {
		background-color: white;
	}
	
	.register {
		margin-left: 300px;
		margin-top: 100px;
		width: 800px;
		height: 500px;
	}
	
	.user, .contractor {
		border: 1px solid black;
		border-radius: 2em;
		background-color: #F6AFB3;
		width: 150px;
		height: 150px;
		display: inline-block;
		padding-left: 110px;
		padding-top: 120px;
	}
	
	.contractor {
		margin-left: 30px;
	}

</style>
</head>
<body>
		<div class="header-containerwrap">
			<!-- header -->
			<%@include file="/WEB-INF/views/inc/header.jsp"%>

			<div class="container">
				<div class="register">
					<div class="user" onClick="location.href='/Myhome_project/member/RegisterUser.do'">
						일반회원
					</div>
					<div class="contractor" onClick="location.href='/Myhome_project/member/RegisterContractor.do'">
						중개인
					</div>
				</div>
			</div>		
		</div>


		<!-- footer -->
		<%@include file="/WEB-INF/views/inc/footer.jsp"%>

</body>
</html>