<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>문의글 수정하기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/noscript.css" />
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


				<!-- Contact -->
					<section id="contact">
						<div class="inner">
							<section>
								<form method="post" action="/mysiteB/qna">
								<c:if test="${authUser.memNo != 0 }">
								<input type="hidden" name="a" value="modify" />
								<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
								</c:if>
								<c:if test="${authUser.memNo == 0 }">
								<input type="hidden" name="a" value="nonmemwrite" />
								</c:if>
							
									<div class="fields">
									
										<c:if test="${authUser.memNo == 0 }">
										<div class="field half">
											<label for="name">닉네임</label>
											<input type="text" name="nickname" id="nickname" placeholder="닉네임을 입력해주세요" />
										</div>
										</c:if>
										
										<c:if test="${authUser.memNo != null }">
										<div class="field half">
											<label for="name">작성자</label>
											<input type="text" name="memName" id="memName" value= "${authUser.memName}" />
										</div>
										</c:if>
										
										<div class="field half">
											<label for="type">질문 유형</label>
											<select name="type">
												<option value="회원 문의">회원 문의</option>
												<option value="결제 문의">결제 문의</option>
												<option value="배송 문의">배송 문의</option>
												<option value="기타 문의">기타 문의</option>
											</select>
										</div>
										
										<div class="field">
											<label for="title">제목 </label>
											<input type="text" name="title" id="title" value="${QnaboardVo.title }"></input>
										</div>
										<div class="field">
											<label for="content">내용</label>
											<textarea name="content" id="content" rows="6">${QnaboardVo.content}</textarea>
										</div>
										
										<c:if test="${authUser.memNo == null }">
										<div class="field half">
											<label for="pass">비밀번호</label>
											<input type="password" name="password" id="pass" placeholder="비밀번호를 입력해주세요"/>
										</div>
										</c:if>
										
									</div>
									<ul class="actions">
										<li><input type="submit" value="등록" class="primary" /></li>
										<li><input type="reset" value="Clear" /></li>
									</ul>
								</form>
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