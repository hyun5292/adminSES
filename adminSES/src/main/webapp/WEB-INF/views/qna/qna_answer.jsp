<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive sidebar template with sliding effect and dropdown menu based on bootstrap 3">
<title>SES 관리자모드</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<style>
.log-div {
	height: 100%;
}

.short-div {
	height: 50%;
}

.imgchart {
	height: 30%;
}

table {
	font-size: 1.6rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<!-- navigation -->
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="logo-wrapper waves-light" align="center"
					style="padding: 10px 0px 0px 0px">
					<a href="main">
					<img width="90%"
						src="${pageContext.request.contextPath}/resources/images/mainmark.png"
						class="img-fluid flex-center"></a>
				</div>
				&nbsp;
				<!-- sidebar-header  -->
				<div class="sidebar-menu">
					<ul>
						<li class="sidebar"><a href="qna"> <i
								class="fas fa-comments"></i> <span>받은문의</span>
						</a></li>
						<li class="sidebar-menu"><a> <i class="fa fa-user"></i> <span>사용자관리</span>
						</a>
							<ul>
								<li><a href="m_search">회원</a></li>
								<li><a href="m_mail">메일전송</a></li>
							</ul></li>
						<li class="sidebar"><a href="pay_service"> <i
								class="far fa-credit-card"></i> <span>유료서비스</span>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- navigation -->

		<!-- contents -->
		<main class="page-content">
		<div class="container-fluid">
			<div class="row-fluid" style="width: 110%">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3>받은문의-답변</h3>
							</div>
							<div class="panel-body">
								<!-- Contents -->
								<center>
									<br /> <br />
									<table class="table table-hover" style="width: 90%;">
										<tbody>
											<tr>
												<td align="center"
													style="background-color: #337ab7; color: White;">번호</td>
												<td>1</td>
											</tr>
											<tr>
												<td align="center"
													style="background-color: #337ab7; color: White;">제목</td>
												<td>문의드립니다1</td>
											</tr>
											<tr>
												<td align="center"
													style="background-color: #337ab7; color: White;">작성자</td>
												<td>Honggildong</td>
											</tr>
											<tr>
												<td align="center"
													style="background-color: #337ab7; color: White;">날짜</td>
												<td>2020-11-01</td>
											</tr>
											<tr>
												<td colspan="2" height="400px"
													style="background-color: #337ab7;"><textarea
														style="width: 100%; height: 100%; font-size: 20px;"
														readonly="readonly">빠른 답변 바람</textarea></td>
											</tr>
										</tbody>
									</table>
									<br /> <br />
									<table style="width: 90%;">
										<tr>
											<td>
												<h3 align="left">답변</h3>
											</td>
										</tr>
										<tr>
											<td><textarea
													style="width: 100%; height: 200px; font-size: 20px;"
													readonly="readonly">답변 드렸습니다</textarea></td>
										</tr>
										<tr><td height="10px"></td>
										</tr>
										<tr>
											<td align="center"><button type="button" class="btn btn-secondary">답변하기</button></td>
										</tr>
									</table>
									<br /> <br />
								</center>
							</div>
						</div>
						<br />
						<hr />
						</table>
					</div>
				</div>
			</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>