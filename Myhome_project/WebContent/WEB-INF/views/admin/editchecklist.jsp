<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%



%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyHome::체크리스트 게시판 관리</title>
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<link rel="stylesheet" href="/Myhome_project/css/template.css">
<link rel="stylesheet" href="/Myhome_project/css/bootstrap.css">
<link rel="stylesheet" href="/Myhome_project/css/board.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<style>

	style>body, html {
		/* width: 100%; */
		height: 100%;
		margin: 0 auto;
		padding: 0;
	}
	
	.container {
		border: 1px solid green;
		width: 1190px;
		margin: 0px auto;
		margin-top: 100px;
		position: relative;
		height: 1200px;
	}
	
	/* ------------------------------------------- */
	
	
	.boardcover{
	/* 	border: 1px solid tomato; */
		float: left;
		width:900px;
		height: auto;
		margin-left: 20px; 
	}
	
	#title {
		font-size: 24px;
		font-family: 'MaplestoryOTFLight';
		border-bottom: 1px solid #eaecef;
		padding-bottom: 10px;
		margin: 30px 10px;
		padding : 0px 10px;
	}
	
	.txt input, .txt textarea {
		width: 90%;
		margin : 10px auto;
	}
	
	
	
	#content { 
		height: 500px;
	}
	
	
	
	input, select {
		font-family: 'NanumBarunGothic';
		outline: none;
	}
	
	
	.btn {
		width: 70px;
		cursor: pointer;
		margin-bottom: 20px;
		margin-right : 10px;
		padding : 6px 12px;
		background-color: #F1AEAE;
		border-radius: 4px;
		outline: none !important;
		border : none;
		color: aliceblue;
		
	
	}
	
	.btns {
		text-align : center;
	}
	
	#file {
			outline: none;
	}


</style>
</head>
<body>
    <div class="boardwrap">
 
 <!-- header -->
<%@include file="/WEB-INF/views/admin/header.jsp" %>
 
     <div class="container">
     <%@include file="/WEB-INF/views/admin/nav.jsp" %>
        <div class="boardcover">
             
		<div id="title">부동산 체크리스트 게시판 관리</div>
		
			<div class="txt">
	        	<input type="text" class="form-control" name="subject" id=" subject" placeholder="수정전 체크리스트 제목입니다."> 
	            <textarea class="form-control" name="content" id="content" placeholder="수정전 체크리스트 내용입니다."></textarea>
				<input type="file"  id="file">
			</div>
			        
            <div class="btns">
                    <button type="button" class="btn" onclick="">
                        수정
                    </button>
                    <button type="button" class="btn" onclick="location.href='/Myhome_project/Myhome/admin/viewchecklist.do';">
                        취소
                    </button>
              </div>
            
			

	</div>
	</div>
	</div>
	
	<!-- footer -->
<%@include file="/WEB-INF/views/inc/footer.jsp" %>
 
</body>
</html>