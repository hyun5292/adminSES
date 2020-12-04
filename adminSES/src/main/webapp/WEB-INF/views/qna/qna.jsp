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
.qnalist {
	font-size: 2.0rem;
}

table {
	font-size: 1.6rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function SearchDT() {
		var StartDT = $('#StartDT').val();
		var EndDT = $('#EndDT').val();

		location.href = "sch_qna?StartDT=" + StartDT + "&EndDT=" + EndDT;
	}
</script>
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
									<td class="qnalist" align="left">받은 문의내역</td>
									<td width="1%"></td>
									<td align="right">
										<table width="50%">
											<tr>
												<td>
													<div class="input-group input-daterange" id="searchDT">
														<div class="input-group input-daterange">
															<input type="text" class="form-control" id="StartDT"
																name="StartDT" data-date-format="yyyy-mm-dd"
																maxlength="15" value="${StartDT}">
															<div class="input-group-addon">to</div>
															<input type="text" class="form-control" id="EndDT"
																name="EndDT" data-date-format="yyyy-mm-dd"
																maxlength="15" value="${EndDT}">
														</div>
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td align="right"><button type="button"
											onclick="SearchDT()" class="btn btn-secondary">검색</button></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-list-search" style="text-align: center;">
							<thead style="text-align: center;">
								<tr>
									<th width="10%"><center>번호</center></th>
									<th width="40%"><center>제목</center></th>
									<th width="20%"><center>아이디</center></th>
									<th width="20%"><center>날짜</center></th>
									<th width="10%"><center>답변</center></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dtos}" var="dto">
									<tr>
										<td>${dto.getNUM()}</td>
										<td><a href="qna_answer"><c:if
													test="${dto.getQ_REPLY() eq ''}">
													<span class="badge badge-pill badge-warning">New</span>
												</c:if> <c:if test="${dto.getQ_REPLY() ne ''}">
														&nbsp;&nbsp;&nbsp;
													</c:if>${dto.getQ_TITLE()}</a></td>
										<td>${dto.getM_ID()}</td>
										<td>${dto.getQ_DATE()}</td>
										<td>${dto.getQ_chkREPLY()}</td>
										<input type="hidden" id="Qnum" name="Qnum"
											value="${dto.getQ_NUM()}">
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr align="center">
									<td colspan="4"><a href="${QLink}pgnum=1"
										style="text-decoration: none">${prev}${prev}</a> <a
										href="${QLink}pgnum=${before}" style="text-decoration: none">${prev}</a>
										<c:forEach items="${pg}" var="p">
											<a href="${QLink}pgnum=${p}" style="text-decoration: none">${p}</a>
										</c:forEach> <a href="${QLink}pgnum=${after}"
										style="text-decoration: none">${next}</a> <a
										href="${QLink}pgnum=${last}" style="text-decoration: none">${next}${next}</a></td>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		$("#searchDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#searchDT").datepicker("setDate", new Date());
		$("#searchDT").each(function() {
			var $inputs = $(this).find('input');

			$inputs.datepicker();
			if ($inputs.length >= 2) {
				var $from = $inputs.eq(0);
				var $to = $inputs.eq(1);
				$from.on('changeDate', function(e) {
					var d = new Date(e.date.valueOf());
					$to.datepicker('setStartDate', d); // 종료일은 시작일보다 빠를 수 없다.
				});
				$to.on('changeDate', function(e) {
					var d = new Date(e.date.valueOf());
					$from.datepicker('setEndDate', d); // 시작일은 종료일보다 늦을 수 없다.
				});
			}
		})
	</script>
</body>
</html>