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
	font-size: 1.8rem;
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
					<a href="main"> <img width="90%"
						src="${pageContext.request.contextPath}/resources/images/mainmark.png"
						class="img-fluid flex-center"></a>
				</div>
				&nbsp;
				<!-- sidebar-header  -->
				<div class="sidebar-search">
					<div>
						<div class="input-group">
							<table>
								<tr>
									<td><input type="text" class="form-control search-menu"
										placeholder="Search..."></td>
									<td>
										<div class="input-group-append">
											<span class="input-group-text"> <i
												class="fa fa-search" height="100%" aria-hidden="true"></i>
											</span>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<!-- sidebar-search  -->
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
			<div class="row-fluid" style="width: 130%">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="short-div">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="qna">받은 문의</a>
							</div>
							<div class="panel-body">
								<table class="table table-list-search">
									<thead>
										<tr>
											<th width="70%"><center>제목</center></th>
											<th width="30%"><center>아이디</center></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${dtos}" var="dto">
											<tr>
												<td>&nbsp;<c:if test="${dto.getQ_REPLY() eq ''}">
														<span class="badge badge-pill badge-warning">New</span>
													</c:if> 
													<c:if test="${dto.getQ_REPLY() ne ''}">
														&nbsp;&nbsp;&nbsp;
													</c:if> 
													<a href="#">${dto.getQ_TITLE()}</a></td>
												<td><center>${dto.getM_ID()}</center></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="short-div">
						<div class="panel panel-default">
							<div class="panel-heading">SES 현황</div>
							<div class="panel-body">
								<table class="table table-list-search"
									style="text-align: center;">
									<thead style="text-align: center;">
										<tr>
											<th width="50%"><center>전체 회원 수</center></th>
											<th width="50%"><center>전체 탈퇴 회원 수</center></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${MCnt}명</td>
											<td>${NoMCnt}명</td>
										</tr>
									</tbody>
								</table>
								<table class="table table-list-search"
									style="text-align: center;">
									<thead style="text-align: center;">
										<tr>
											<th width="50%"><center>유료서비스 이용자 수</center></th>
											<th width="50%"><center>유료서비스 이용률</center></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${SUsercnt}명</td>
											<td>${SUserpct}%</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="log-div">
						<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
							<img width="205px"
								src="${pageContext.request.contextPath}/resources/images/chart1.png">&nbsp;
							<img width="205px"
								src="${pageContext.request.contextPath}/resources/images/chart2.png">&nbsp;
							<img width="205px"
								src="${pageContext.request.contextPath}/resources/images/chart3.png">
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>


</body>

</html>