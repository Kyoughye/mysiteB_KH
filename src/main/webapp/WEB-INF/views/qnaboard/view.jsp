<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
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
					<header class="major">
						<h1>�����ϼ���</h1>
					</header>

					<form>

								<h3>����	${	QnaboardVo.title }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/qna?a=list"
										class="button small">�������</a></li>
								</ul>
							</div>

					
						<h3>�ۼ��� ${QnaboardVo.memName}</h3>
						

						<div class="box">
							<p>${fn:replace(QnaboardVo.content, newLine,"<br>") }</p>
						</div>

						
						<div class="col-6 col-12-medium">
						</div>
						<div class="col-6 col-12-medium">
						
						<div class="row">
														<c:if test="${authUser.memNo == null && QnaboardVo.memNo == 0 }">
										<div class="field half">
											<label for="pass">��й�ȣ</label>
											<input type="password" name="password" id="pass" placeholder="��й�ȣ�� �Է����ּ���"/>
										</div>
										</c:if>
						
						
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<c:if test="${authUser.memNo == QnaboardVo.memNo || QnaboardVo.memNo == 0}">
																<li><a href="/mysiteB/qna?a=modifyform&qnaNo=${QnaboardVo.qnaNo}" class="button">�ۼ���</a></li>
																</c:if>
																</ul>
														</div>
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<c:if test="${authUser.memNo == QnaboardVo.memNo || QnaboardVo.memNo == 0}">
																<li><a href="/mysiteB/qna?a=delete&qnaNo=${QnaboardVo.qnaNo}" class="button primary">�ۻ���</a></li>
																</c:if>
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