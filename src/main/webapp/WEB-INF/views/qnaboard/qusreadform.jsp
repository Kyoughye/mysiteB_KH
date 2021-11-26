<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
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


					<form>
						
							<header class="major">
								<h1>제목 나오기</h1>

							</header>


							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/user?a=questlist"
										class="button small">이전으로</a></li>
								</ul>
							</div>

					
						<h3>작성자</h3>

						<div class="box">
							<p>여기 내용 나오는 곳.</p>
						</div>

						
						<div class="col-6 col-12-medium">
						</div>
						<div class="col-6 col-12-medium">
						<div class="row">
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<li><a href="#" class="button">수정하러 가기</a></li>
																</ul>
														</div>
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<li><a href="#" class="button primary">삭제</a></li>
																</ul>
														</div>
													</div>
													</div>

					</form>


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