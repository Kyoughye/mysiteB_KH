<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>

<!DOCTYPE HTML>
<html>
<head>
<title>문의글 게시판</title>
<meta charset="UTF-8" />
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
					
					
					<label class="actions" align:right>
						<a href="/mysiteB/qna?a=writeform" class="button primary">문의글 남기기</a>
					</label>
					
					<form id="search_form" action="/mysiteB/qna" method="post">
					<input type = "hidden" name = "a" value="list">
					<label class="hidden">검색 분류</label>
					<select name="t" style="width:200px;height:50px;" >
						<option ${param.t == "전체문의"?"selected":"" } value="전체문의">전체문의</option>
						<option ${param.t == "회원문의"?"selected":"" } value="회원문의">회원문의</option>
						<option ${param.t == "결제문의"?"selected":"" } value="결제문의">결제문의</option>
						<option ${param.t == "배송문의"?"selected":"" } value="배송문의">배송문의</option>
						<option ${param.t == "기타문의"?"selected":"" } value="기타문의">기타문의</option>
					</select>				
						<input type="submit" value="검색">
					</form>


					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>질문유형</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="vo">
									<tr>
										<td>${vo.qnaNo}</td>
										<td><a href="/mysiteB/qna?a=read&qnaNo=${vo.qnaNo}">${vo.title}</a></td>
										<c:if test="${vo.memNo != 0 }">
										<td>${vo.memName}</td>
										</c:if>
										<c:if test="${vo.memNo == 0 }">
										<td>${vo.nickname}</td>
										</c:if>
										<td>${vo.type}</td>
										<td>${vo.regDate}</td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
						
			<div class="pager">
				<c:set var="page" value="${(empty param.p)?1:param.p }" />
				<c:set var="startNum" value="${page-(page-1)%5}"/> 
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5),'.') }"/>
								
				<div class="indexer margin-top " >
					<h6 class="hidden">현재 페이지</h3>
					<div>${(empty param.p)?1:param.p }/${lastNum} pages</div>
				
				</div>
				
				<ul class="-list- center">
					
						<c:if test="${startNum>1 }">
							<a class="btn btn-prev" href="/mysiteB/qna?a=list&p=${startNum-1 }&t=${param.t}}">◀</a>
						</c:if>
						<c:forEach var="i" begin="0" end="4">
						<c:if test="${(startNum+i) <= lastNum}">	
							<a class="${(param.p==(startNum+i))?'selected':'' }"></a><a href="/mysiteB/qna?a=list&p=${startNum+i}&t=${param.t}">${startNum+i}</a>						
						</c:if>
						</c:forEach>
						
						<c:if test="${startNum+4<lastNum }">
							<a href="/mysiteB/qna?a=list&p=${startNum+5}&t=${param.t}"class="btn btn-next">▶</a>
						</c:if>
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