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
						<h1>???? ??ǰ ????</h1>
					</header>



					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>??ȣ</th>
									<th>??????</th>
									<th>???? ????</th>
									<th>???? ???? ?Ⱓ</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1111</td>
									<td>?ű? ȸ?? ????</td>
									<th>2000?? ???? ????</th>
									<th>2021/11/01-2021/12/31</th>
								</tr>


								<tr>
									<form class="form-signin" id="orderlist-form" name="couponform"
										method="get" action="/mysiteB/user">
										<input type="hidden" name="a" value="coupon" /> 
										<input type="hidden" name="no" value="${authUser.memId}" />


									</form>
								</tr>
							</tbody>
						</table>
						
						<div class="row gtr-uniform">
						
						<h3>???? ????</h3>
									<div class="col-6 col-12-xsmall">
										<input type="text" name="demo-name" id="demo-name" value=""
											placeholder="" />
									</div>
									<ul class="actions">
														<li><input type="submit" value="????"/></li>
														
									</ul>
						</div>
					</div>


					<!-- Elements -->

					<div class="row gtr-200">
						<div class="col-6 col-12-medium"></div>
						<div class="col-6 col-12-medium"></div>
					</div>

				</div>
			</section>

		</div>

		<!-- Contact -->
		<section id="contact">
			<div class="inner">
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
							<span>1234 Somewhere Road #5432<br /> Nashville, TN 00000<br />
								United States of America
							</span>
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