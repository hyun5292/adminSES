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
.tblInfo {
	font-size: 1.6rem;
}

table {
	font-size: 1.3rem;
}

thead {
	font-size: 1.3rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});

	function SearchEmpLog() {
		var mId = $('#mId').val();
		var StartDT = $('#StartDT').val();
		var EndDT = $('#EndDT').val();
		location.href = "el_sch?mId=" + mId + "&StartDT=" + StartDT + "&EndDT=" + EndDT;
	}
	
	function DobtnYesAuth() {
		var mId = $('#mId').val();
		location.href = "doAuth?mId=" + mId;
	}
	
	function DobtnNoAuth() {
		var mId = $('#mId').val();
		location.href = "dontAuth?mId=" + mId;
	}
	
	function ModifyPWD() {
		location.href = "modify";
	}
	
	function isAuth() {
		var eAuth = $('#eAuth').val();
		var mId = $('#mId').val();
		var sessId = $('#eId').val();
		
		if (eAuth == 'Y') {
			$("#btnDontAuth").show();
			$("#btnDoAuth").hide();
		} else if (eAuth == 'N') {
			$("#btnDontAuth").hide();
			$("#btnDoAuth").show();
		}
		
		if(mId == sessId) {
			$("#btnModifyPWD").show();
		} else {
			$("#btnModifyPWD").hide();
		}
	}
	window.onload = isAuth;
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
				<div class="sidebar-header">
					<div class="user-info">
						<span class="user-name"><strong>${eId}
							<input type="hidden" id="eId" name="eId" value="${eId}"></strong><a
							href="logout"> <i class="fa fa-power-off"></i>
						</a></span> <span class="user-role">Administrator</span> <span
							class="user-status"> <i class="fa fa-circle"></i> <span>Online</span>
						</span>
					</div>
				</div>
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
							<table class="tblInfo" width="100%">
								<tr>
									<td width="10%">직원 정보</td>
									<td width="75%" align="right">
										<button type="button" id="btnModifyPWD" name="btnModifyPWD"
											class="btn btn-secondary" onclick="ModifyPWD()">비밀번호 변경</button>
									</td>
									<td width="2%"></td>
									<td width="13%"><button type="button" id="btnDoAuth" name="btnDoAuth"
											class="btn btn-secondary" onclick="DobtnYesAuth()">관리자 권한 부여</button>
										<button type="button" id="btnDontAuth" name="btnDontAuth"
											class="btn btn-secondary"onclick="DobtnNoAuth()">관리자 권한 해제</button></td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<table border="0" class="tblInfo" width="100%">
								<tbody>
									<tr>
										<td align="right" width="10%">아이디</td>
										<td width="1%"></td>
										<td width="20%">
											<table width="60%">
												<tr>
													<td>${dto.getM_ID()}</td>
												</tr>
											</table>
										</td>
										<td align="right" width="10%">전화번호</td>
										<td width="1%"></td>
										<td width="45%">
											0${dto.getM_TEL1()}-${dto.getM_TEL2()}-${dto.getM_TEL3()}</td>
										<td align="right" width="5%">직급</td>
										<td width="1%"></td>
										<td width="10%">${dto.getE_RANK()}</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td align="right">성명</td>
										<td width="10px"></td>
										<td width="20%">
											<table width="60%">
												<tr>
													<td>${dto.getM_NAME()}</td>
												</tr>
											</table>
										</td>
										<td align="right">이메일</td>
										<td width="1%"></td>
										<td colspan="4">${dto.getE_EMAIL1()}@${dto.getE_EMAIL2()}</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td>관리 권한</td>
										<td>${dto.getE_AUTH()}</td>
										<td align="right" colspan="2">입사 및 퇴사일</td>
										<td width="1%"></td>
										<td colspan="4">${dto.getE_ENTER_DT()}&nbsp;&nbsp;to&nbsp;&nbsp;
											${dto.getE_RESIGN_DT()}</td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" id="mId" name="mId" value="${dto.getM_ID()}">
							<input type="hidden" id="eAuth" name="eAuth" value="${dto.getE_AUTH()}">
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="width: 110%">
				<div class="col-md-10">
					<table width="100%">
						<tr>
							<td width="52%"></td>
							<td width="40%" align="right">
								<div class="input-group input-daterange" id="searchDT">
									<div class="input-group input-daterange">
										<input type="text" class="form-control" id="StartDT"
											name="StartDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${StartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="EndDT"
											name="EndDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${EndDT}">
									</div>
								</div>
							</td>
							<td width="8%" align="right">
								<button type="button" onclick="SearchEmpLog()"
									class="btn btn-secondary">검색</button>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10px"></td>
						</tr>
					</table>
					<table class="table table-list-search" style="text-align: center;">
						<thead style="text-align: center;">
							<tr>
								<th width="15%"><center>번호</center></th>
								<th width="45%"><center>활동</center></th>
								<th width="40%"><center>날짜</center></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dtos}" var="dto">
								<tr>
									<td>${dto.getNUM()}</td>
									<td>${dto.getEL_ACTIVITY()}</td>
									<td>${dto.getEL_DATE()}</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr align="center">
								<td colspan="4"><a href="${elLink}&pgnum=1"
									style="text-decoration: none">${prev}${prev}</a> <a
									href="${elLink}&pgnum=${before}" style="text-decoration: none">${prev}</a>
									<c:forEach items="${pg}" var="p">
										<a href="${elLink}&pgnum=${p}" style="text-decoration: none">${p}</a>
									</c:forEach> <a href="${elLink}&pgnum=${after}"
									style="text-decoration: none">${next}</a> <a
									href="${elLink}&pgnum=${last}" style="text-decoration: none">${next}${next}</a></td>
							</tr>
						</tfoot>
					</table>
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
		$("#joinDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#joinDT").datepicker("setDate", new Date());
		$("#searchDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#searchDT").datepicker("setDate", new Date());
		$("#joinDT").each(function() {
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