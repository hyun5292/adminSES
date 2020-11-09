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
					<a href="main"> <img width="90%"
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
						<div class="panel-heading">
							<table width="100%">
								<tr>
									<td>검색조건</td>
									<td colspan="10" align="right">
										<button type="button" class="btn btn-secondary">등록</button>
										<button type="button" class="btn btn-secondary">조회</button>
									</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<table width="100%">
								<tbody>
									<tr>
										<td align="right">회원분류</td>
										<td width="10px"></td>
										<td><select class="custom-select">
												<option>일반</option>
												<option>직원</option>
												<option>SNS사</option>
										</select></td>
										<td align="right">아이디</td>
										<td width="10px"></td>
										<td><input type="text" class="form-control" id="inputID"
											placeholder="아이디" width="80%"></td>
										<td width="10px"></td>
										<td align="right">성명</td>
										<td width="10px"></td>
										<td><input type="text" class="form-control"
											id="inputName" placeholder="성명" width="80%"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-10">
					<table class="table table-list-search" style="text-align: center;">
						<thead style="text-align: center;">
							<tr>
								<th width="10%"><center>번호</center></th>
								<th width="25%"><center>아이디</center></th>
								<th width="25%"><center>성명</center></th>
								<th width="40%"><center>전화번호</center></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
							<tr>
								<td>00</td>
								<td>Honggildong</td>
								<td>홍길동</td>
								<td>010-0000-0000</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr align="center">
								<td colspan="4"><< < 1 2 3 4 5 6 7 8 9 10 > >></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>