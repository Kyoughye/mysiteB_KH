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


<script>

</script>

<style>
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">


		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<div class="inner">
				<header class="major">
					<h1>관리자</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<div class="col-3 col-3-small">

							<ul>
								<a href="#" class="list-group-item">회원관리</a>
								<br>
								<a href="#" class="list-group-item">상품관리</a>
								<br>
								<a href="#" class="list-group-item">문의/구매평</a>
								<br>
								<a href="#" class="list-group-item">공지사항</a>
							</ul>

						</div>

						<div class="col-9 col-12-medium">


							<!-- 
								
								<p>
									<span class="image left"><img src="images/pic09.jpg" alt="" /></span>
									<h4>상품 이름</h4>
									
									<h4><b>2000원</b></h4>
									
									
									<h4> </h4>
									
									<div class="inner">
										<section>
											<form method="post" action="#">
												
												<ul class="actions">
												
													<form method="post" action="#">
														<input type=button value="-" onClick="javascript:this.form.amount.value--;">
														<input style="text-align:center" type=text name=amount value=1>
														<input type=button value="+" onClick="javascript:this.form.amount.value++;">
													</form>
													
													<li><input type="submit" value="장바구니" class="primary" /></li>
													<li><input type="reset" value="구매하기" /></li>
												</ul>
												
											</form>
										</section>
									</div>
								</p>
								
								-->

							<div class="table-wrapper">
								<h4>오늘의 할 일 &nbsp 5</h4>
								<table>
									<tbody>
										<tr>
											<td><a href="">신규주문 &nbsp 0</a></td>
											<td><a href="">취소관리 &nbsp 1</a></td>
											<td><a href="">답변대기 상품문의 &nbsp </a></td>
											<td><a href="/mysiteB/qna?a=listNoAnswer">답변대기 일반문의 &nbsp ${noanswercount }</a></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="table-wrapper">
								<h4>일자별 요약</h4>
								<table>
									<thead>
										<tr>
											<th>일자</th>
											<th>주문수</th>
											<th>매출액</th>
											<th>가입</th>
											<th>문의</th>
											<th>후기</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>2020-01-16</td>
											<td>0</td>
											<td>1</td>
											<td>2</td>
											<td>3</td>
											<td>4</td>
										</tr>
									</tbody>
								</table>
							</div>


							<div class="row">
								<div class="col-6 col-12-small">
									<h4>문의/구매평</h4>
									<section class="split">
									<c:forEach items="${qblist }" var="vo">
										<section>
											<div class="contact-method" style="margin:0">
												<span class="icon solid alt"><img src="images/사람.png"
													alt="" style="max-width: 2em; height: auto;" /></span>
												<h6 style="margin:0">
													[${vo.type}]<a href="/mysiteB/qna?a=read&qnaNo=${vo.qnaNo}">${vo.title }</a><br>${vo.memName }&nbsp&nbsp${vo.regDate }
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
									</c:forEach>	
									</section>
								</div>
								
								
								
								
								<div class="col-6 col-12-small">
									<h4>공지사항</h4>
									<section class="split">
										<section>
											<div class="contact-method" style="margin:0; padding:0">
												<h6 style="margin:0">
													<a href="">제목</a><br>관리자&nbsp&nbsp2021.11.15
												</h6>
											</div>
										</section>
										
										<hr class="major" style="margin: 20px 0"/>
										<section>
											<div class="contact-method" style="margin:0; padding:0">
												<h6 style="margin:0">
													<a href="">제목</a><br>관리자&nbsp&nbsp2021.11.15
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
										<section>
											<div class="contact-method" style="margin:0; padding:0">
												<h6 style="margin:0">
													<a href="">제목</a><br>관리자&nbsp&nbsp2021.11.15
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
									</section>
								</div>

							</div>


						</div>

					</div>

				</div>










			</div>


		</div>

	</div>





	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


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