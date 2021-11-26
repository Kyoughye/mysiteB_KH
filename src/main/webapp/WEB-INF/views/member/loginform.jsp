<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Generic - Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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
										<h1>로그인</h1>
									</header>
								</div>
							</section>

					</div>

				<!-- Contact -->
					<section id="contact">
						<div class="inner">
							<section>
								<form method="post" action="/mysiteB/user">
								<input type="hidden" name="a" value="login" />
									<div class="fields">
										<div class="field half">
											<label for="name">아이디</label>
											<input type="text" class="form-control" placeholder="ID" id="id" name="memId" value="">
										</div>
										
										<div class="field half">
											<label for="password">비밀번호</label>
											<input type="password" placeholder="Password" value="" name="memPass" />
										</div>
										
										<c:if test="${param.result eq 'fail' }"> 
									<P>로그인이 실패했습니다. 다시입력해주세요</P>
										</c:if>
										<c:if test="${param.result eq 'out' }"> 
									<P>탈퇴한 회원 입니다.</P>
										</c:if>
										<div class="field">
											<button  type="submit" value="로그인">로그인</button>
										</div>
									</div>
									
								</form>
								<div class="col-6 col-12-small">
									<a href="/mysiteB/user?a=joinform">회원가입</a>
								</div>
							</section>
							<section class="split">
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-envelope"></span>
										<h3>Email</h3>
										<a href="#">information@untitled.tld</a>
									</div>
								</section>
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-phone"></span>
										<h3>Phone</h3>
										<span>(000) 000-0000 x12387</span>
									</div>
								</section>
								<section>
									<div class="contact-method">
										<span class="icon solid alt fa-home"></span>
										<h3>Address</h3>
										<span>1234 Somewhere Road #5432<br />
										Nashville, TN 00000<br />
										United States of America</span>
									</div>
								</section>
							</section>
						</div>
					</section>
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