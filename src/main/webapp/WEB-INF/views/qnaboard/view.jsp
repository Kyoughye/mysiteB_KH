<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE HTML>
<html>
<head>
<title>문의글</title>
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
						<h1>문의하세요</h1>
					</header>

					<form>

								<h3>제목	${QnaboardVo.title }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/qna?a=list"
										class="button small">목록으로</a></li>
								</ul>
							</div>

						
						<c:if test="${QnaboardVo.memNo == 0}">
						<h3>작성자 ${QnaboardVo.nickname}</h3>
						</c:if>
						
						<c:if test="${QnaboardVo.nickname == null}">
						<h3>작성자 ${QnaboardVo.memName}</h3>
						</c:if>
						
						<h3>질문 유형 ${QnaboardVo.type}</h3>

						<div class="box">
							<p>${fn:replace(QnaboardVo.content, newLine,"<br>") }</p>
						</div>

						
						<div class="col-6 col-12-medium">
						</div>
						<div class="col-6 col-12-medium">
						
						<div class="row">
														<c:if test="${QnaboardVo.memNo == 0 }">
										<div class="field half">
											<label for="pass">비밀번호</label>
											<input type="password" name="password" id="pass" placeholder="비밀번호를 입력해주세요"/>
										</div>
										</c:if>
						
						
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<c:if test="${(QnaboardVo.memNo != 0 && authUser.memNo == QnaboardVo.memNo) }">
																<input type="password" name="password" value=""/>
																<form name="modifyform"  action="/mysiteB/qna?a=modifyform" method="post" >
																<input type="hidden" name="qnapass" value="${QnaboardVo.pass }"/>
																
																<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
																
																<li><a class="button">글수정</a></li>
																</form>
																
																
																<form name="deleteform"  action="/mysiteB/qna?a=delete" method="post" >
																<input type="hidden" name="qnapass" value="${QnaboardVo.pass }"/>
																<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
																
																<li><a href="/mysiteB/qna?a=delete&qnaNo=${QnaboardVo.qnaNo}" class="button primary">글삭제</a></li>
																</form>
																</c:if>
																
																<c:if test="${QnaboardVo.memNo == 0}">
																<li><a href="/mysiteB/qna?a=modifyform&qnaNo=${QnaboardVo.qnaNo}" class="button">글수정</a></li>
																<li><a href="/mysiteB/qna?a=delete&qnaNo=${QnaboardVo.qnaNo}" class="button primary">글삭제</a></li>
																</c:if>
																
															</ul>
														</div>
														<div class="col-6 col-12-xsmall">
															<ul class="actions stacked">
																<c:if test="${(QnaboardVo.memNo != 0 && authUser.memNo == QnaboardVo.memNo) || (authUser.memNo == null && QnaboardVo.memNo == 0 && QnaboardVo.pass == password) }">
																<li><a href="/mysiteB/qna?a=delete&qnaNo=${QnaboardVo.qnaNo}" class="button primary">글삭제</a></li>
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