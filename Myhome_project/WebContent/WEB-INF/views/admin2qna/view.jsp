<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Myhome::QnA</title>

<%-- <%@include file="/WEB-INF/views/inc/asset.jsp" %> --%>

<!-- <link rel="stylesheet" href="/Myhome_project/css/main.css"> -->
<link rel="stylesheet" href="/Myhome_project/css/template.css">
<link rel="stylesheet" href="/Myhome_project/css/bootstrap.css">
<link rel="stylesheet" href="/Myhome_project/css/board.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
body, html {
	height: 100%;
	margin: 0 auto;
	padding: 0;
}

.container {
	border: 0px;
}

#header {
	background-color: white;
}

#header:hover {
	background-color: white;
}

.header-containerwrap {
	height: auto;
	min-height: 100%;
}

.section2 {
	
    font-family: 'MaplestoryOTFLight';
    display: inline;
    position: relative;
    left: 200px;
    top: 100px;
    float: left;
    
}
#title {
	border-bottom: 1px dashed #ccc;
	font-size: 24px;
	margin-bottom: 30px;
	width: 200px;
}
#tbl1 {
	font-size: 16px;
	color: #202020;
}
#tbl1 td {
	height: 50px;
}

.section3 {
	margin-top: 30px;
	text-align: center;
	/* border: 1px solid black; */
	
}

    .header {
	     background-color : white;
        
    }

    #navboardtbl{
        height: 800px;  
        width: 100%;  
        margin: 0px;
    }
    
    
    /*기존 200xp 삭제만 300px*/
    #navboardtbl .navboardtd:first-child{
    	width: 300px;

    }
    
	 .navboardtd{
	
	    vertical-align: top;
			
	}
	
	/*타이틀 태그 */

	/*타이틀 태그 */
	#boardtitle {
	font-size: 24px;
	margin: 30px 10px;
	padding: 0px 10px;
	color: black;
	font-family: 'MaplestoryOTFLight';
	border-left: 5px solid #F1AEAE;
}

#board {
	margin-left: 0px;
}


	.boardtext{
		margin-top:10px;
	}
	
	.boardfile > input{
		background-color: #f1aeae;
		width:400px;
		height:40px;
		margin-left:20px;
    	text-align:center;
    	color:white;	
    	outline: none;
    	border: 0;
    	
	}
	
	
	/*버트 새로 고침 */
	
	.boardbutton{
		width:940px;
		padding-right:120px;
	}
	.boardbutton button{
	
		float:right;
		display:inline-block;	
        outline: none;
        border: 0px solid #000;
        background-color: #f1aeae;
        color: white;
        width: 70px;
        height: 34px;
        margin-right:5px;
        border-radius: 3px;
	}

    .boardbutton button:after{
        content: "";
        display: block;
        clear: both;
	}
</style>
</head>
<body>
	<div class="wrap">

		<!-- header -->
		<%@include file="/WEB-INF/views/admin/header.jsp"%>

		<div class="container">

			<form action="" id="form1">
				
				<table id="navboardtbl">

					<tr id="navboardtr">
						<td class="navboardtd">

					<!-- nav -->
					<%@include file="/WEB-INF/views/admin/nav.jsp"%>
				
						</td>

						<!-- 상세 보기 입니다.-->

						<td class="navboardtd">
							<div id="boardtitle" class="">QnA</div>
							
							<div class="boardcover">
								<table id="board"
									class="table table-hover table-striped table-condensed">
										<!--  ?는 데이터 직접 넣기  -->
									<tr class="headtr">
										<th class="firtd boardtd">카테고리</th>
										<th class="sectd boardtd" colspan="2">제목</th>
										<th class="firtd boardtd">작성자</th>
										<th class="firtd boardtd">처리상태</th>
										<th class="fortd boardtd">작성일</th>
									<!-- <th class="fiftd boardtd">조회수</th> -->	
									</tr>
									<tr class="boardtr">
										<td class="firtd boardtd">카테고리</td>
										<td class="sectd boardtd" colspan="2">
										<div class="boardtexttilte"> 제목</div></td>
										<td class="firtd boardtd">작성자</td>
										<td class="firtd boardtd">처리상태</td>
										<td class="fortd boardtd">작성일</td>
									<!-- <th class="fiftd boardtd">조회수</th> -->	
									</tr>

									<tr class="boardtr">
										<td class="firtd boardtd" colspan="6">
											<textarea class="form-control col-sm-5 boardtext"  
											placeholder="여기에 게시글을 작성해주세요" rows="15" disabled>여기글들!!</textarea>
										</td>

									</tr>


								</table>
							</div>

						
						
						<!-- 버튼 처리상태가 완료면 신고카운도 disabled
						ProcReportChat 채팅 전송
						ProcReportCompleteChat 카운트와 동시에 완료채팅 전송 -> 처리 상태 변경 
						-->
						
						<!--  -->
						<div class="boardbutton">
							<button onclick="location.href='/Myhome_project/admin2/qna/writeok.do';">
							등록</button>
							<button onclick="location.href='/Myhome_project/admin2/qna/list.do';">
							목록</button>
						<!--  <button> 1234</button>-->	
						</div>
						
						
						<!-- 지금은 삭제? 아니면 안보이게? -->
<!-- 							<div class="boardwork d-grid gap-2 d-md-block btn-group">
								<button class="btn btn-outline-secondary " type="button"
									id="button-addon2" onclick="location.href='/Myhome-project/admin2/report/chatok.do';">
									채팅 전송</button>
								<button class="btn btn-outline-secondary " type="button"
									id="button-addon2" onclick="location.href='/Myhome-project/admin2/application/list.do';">
									List</button>
							</div>
 -->
						</td>
					</tr>
				</table>
			</form>



		</div>

	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/inc/footer.jsp"%>


	<script>

	</script>



</body>
</html>