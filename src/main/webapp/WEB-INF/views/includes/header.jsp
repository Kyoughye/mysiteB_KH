<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div id="header">

	<ul>
		<c:choose>
			<c:when test="${authUser == null }">

				<!-- Header -->
				<header id="header" class="alt">
					<a href="/mysiteB/main" class="logo"><strong>shopping
							mall</strong> <span>by B team</span></a>
					<nav>
						<a href="#menu">Menu</a>
					</nav>
				</header>

				<!-- Menu -->
				<nav id="menu">
					<ul class="links">
						<li><a href="/mysiteB/main">Home</a></li>
						<li><a href="/mysiteB/user?a=mypage">cart</a></li>
					</ul>
					<ul class="actions stacked">
						<li><a href="/mysiteB/user?a=loginform"
							class="button primary fit">로그인</a></li>
						<li><a href="/mysiteB/user?a=joinform" class="button fit">회원 가입</a></li>
					</ul>
				</nav>

			</c:when>
			<c:otherwise>

				<!-- Header -->
				<header id="header" class="alt">
					<a href="/mysiteB/main" class="logo"><strong>shopping
							mall</strong> <span>by B team</span></a>
					<nav>
						<a href="/mysiteB/user?a=mypage&no=${authUser.memId }">${authUser.memName }님</a>
						<a href="#menu">Menu</a>
					</nav>
				</header>

				<!-- Menu -->
				<nav id="menu">
					<ul class="links">
						<li><a href="/mysiteB/main">Home</a></li>
						<li><a href="/mysiteB/user?a=mypage">cart</a></li>
					</ul>
					<ul class="actions stacked">
						<li><a href="/mysiteB/user?a=logout" class="button primary fit">로그 아웃</a></li>

					</ul>
				</nav>
			</c:otherwise>
		</c:choose>

	</ul>
</div>
<!-- /header -->



