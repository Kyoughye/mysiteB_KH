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
		<title>Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/noscript.css" />
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				<!-- Banner -->
					<section id="banner" class="major">
						<div class="inner">
							<header class="major">
								<h1>Hi, welcome to my shoppingmall</h1>
							</header>
							<div class="content">
								<p>당신의 원하는 모든 것을 찾을 수 있습니다.</p>
								<ul class="actions">
									<li><a href="#one" class="button next scrolly">Get Started</a></li>
								</ul>
							</div>
						</div>
					</section>

				<!-- Main -->
					<div id="main">

						<!-- One -->
							<section id="one" class="tiles">
								<article>
									<span class="image">
										<img src="images/pic01.jpg" alt="" />
									</span>
									<header class="major">
										<h3><a href="landing.html" class="link">best product</a></h3>
										<p>최다 판매 상품</p>
									</header>
								</article>
								<article>
									<span class="image">
										<img src="images/pic02.jpg" alt="" />
									</span>
									<header class="major">
										<h3><a href="/mysiteB/product?a=insertform" class="link">sale</a></h3>
										<p>상품 등록</p>
									</header>
								</article>
								<article>
									<span class="image">
										<img src="images/pic03.jpg" alt="" />
									</span>
									<header class="major">
										<h3><a href="/mysiteB/product?a=productList" class="link">category</a></h3>
										<p>전체 상품 종류</p>
									</header>
								</article>
								<article>
									<span class="image">
										<img src="images/pic04.jpg" alt="" />
									</span>
									<header class="major">
										<h3><a href="landing.html" class="link">best review</a></h3>
										<p>최고 후기 상품</p>
									</header>
								</article>
							</section>

						<!-- Two -->
							<section id="two">
								<div class="inner">
									<header class="major">
										<h2>신규 회원 이벤트</h2>
									</header>
									<p>지금 회원 가입하시면 즉시 2000천원 할인 쿠폰 지급!</p>
									<ul class="actions">
										<li><a href="/mysiteB/user?a=joinform" class="button next">회원 가입 하러가기</a></li>
									</ul>
								</div>
							</section>
							
							<section id="two">
								<div class="inner">
									<header class="major">
										<h2>문의하기</h2>
									</header>
									<p>질문하고 싶은 모든 것</p>
									<ul class="actions">
										<li><a href="/mysiteB/qna?a=list" class="button next">하러가기</a></li>
									</ul>
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