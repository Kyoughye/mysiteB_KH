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
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
						<h1>카테고리</h1>
					</header>



					<hr class="major" />

					<!-- Elements -->

					<div class="row gtr-200">
						<div class="col-6 col-12-medium">


							<!-- Lists -->
							<div class="row">
								<div class="col-6 col-12-small">


									<ul>
										<li>아우터</li>
										<li>상의</li>
										<li>하의</li>
									</ul>


								</div>
							</div>




						</div>


						<div class="col-6 col-12-medium">

							<span class="image fit"><img src="images/pic03.jpg" alt="" /></span>

							<form method="post" action="/mysiteB/product">
								<input type="hidden" name="a" value="productList" />

								<c:forEach items="${list}" var="vo">

									<div class="row gtr-50 gtr-uniform">
										<div class="box alt">
											<div class="col-4">
												<a href=""><span class="image fit"><img
														src="images/pic08.jpg" alt="" /></span></a>
												<h4>${vo.proName }</h4>
												<h5>${vo.proDesc }</h5>
											</div>
										</div>

									</div>
							</form>


							</c:forEach>

							<h4>신규 상품</h4>
							<p>
								<span class="image left"><img src="images/pic09.jpg"
									alt="" /></span>ipiscing eu felis iacul
							</p>


						</div>


					</div>


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