<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>


<!DOCTYPE HTML>
<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />

<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />


</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="major">
					</header>

					
						<h3>비공개 글입니다. 비밀번호를 입력하세요</h3>
						<input type="password" name="password" var="password" />
						<input type="submit" value="확인" class="primary" />  
						<c:if test= "${QnaboardVo.pass == password }"> 
						<form method="post" >
						
						<input type="hidden" name="a" value="read"/>
						<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
						<input type="submit" value="확인" class="primary" />                      
						</form>
						</c:if>
	
						<c:if test= "${QnaboardVo.pass != password }">
							
						</c:if>
						
					
						
						
					

				</div>
			</section>

		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>