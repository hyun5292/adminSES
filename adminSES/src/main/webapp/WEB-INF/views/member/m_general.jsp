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
	font-size: 1.5rem;
}

table {
	font-size: 1.3rem;
}

thead {
	font-size: 1.3rem;
}

#QnafromDT {
	font-size: 1.3rem;
	width: 100px;
}

#QnatoDT {
	font-size: 1.3rem;
	width: 100px;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});

	function SearchQDT() {
		var mId = $('#mId').val();
		var qStartDT = $('#qStartDT').val();
		var qEndDT = $('#qEndDT').val();
		var result = "";

		if (qStartDT != null && qEndDT != null) {
			result += "&qStartDT=" + qStartDT + "&qEndDT=" + qEndDT;
		}

		location.href = "g_dt_search?mId=" + mId + result;
	}

	function SearchLDT() {
		var mId = $('#mId').val();
		var lStartDT = $('#lStartDT').val();
		var lEndDT = $('#lEndDT').val();
		var result = "";

		if (lStartDT != null && lEndDT != null) {
			result += "&lStartDT=" + lStartDT + "&lEndDT=" + lEndDT;
		}

		location.href = "g_dt_search?mId=" + mId + result;
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
				<div class="sidebar-header">
					<div class="user-info">
						<span class="user-name"><strong>${eId}</strong><a
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
						<li class="sidebar"><a
							href="pay_service?mId=${mdto.getM_ID()}"> <i
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
						<div class="panel-heading">회원정보</div>
						<div class="panel-body">
							<input type="hidden" id="mId" name="mId"
								value="${mdto.getM_ID()}">
							<table class="tblInfo" width="100%">
								<tbody>
									<tr>
										<td align="right">아이디</td>
										<td width="10px"></td>
										<td>${mdto.getM_ID()}</td>
										<td align="right">전화번호</td>
										<td width="10px"></td>
										<td>0${mdto.getM_TEL1()}-${mdto.getM_TEL2()}-${mdto.getM_TEL3()}</td>
										<td align="right"><a
											href="pay_service?mId=${mdto.getM_ID()}">유료서비스</a> 가입 여부</td>
										<td width="10px"></td>
										<td>${mdto.getM_SERVICE_CHK()}</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td align="right">성명</td>
										<td width="10px"></td>
										<td>${mdto.getM_NAME()}</td>
										<td align="right">이메일</td>
										<td width="10px"></td>
										<td>${mdto.getM_EMAIL1()}@${mdto.getM_EMAIL2()}</td>
										<td align="right">유료서비스 가입일</td>
										<td width="10px"></td>
										<td>${mdto.getM_SERVICE_DATE1()}년
											${mdto.getM_SERVICE_DATE2()}월 ${mdto.getM_SERVICE_DATE3()}일</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td align="right">동의한 SNS</td>
										<td width="10px"></td>
										<td colspan="4">${FB}&nbsp;${KT}&nbsp;${N}&nbsp;${G}</td>
										<td align="right">유료서비스 월 납부 금액</td>
										<td width="10px"></td>
										<td>19,900 원</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td colspan="6"></td>
										<td align="right">총 납부 금액</td>
										<td width="10px"></td>
										<td><fmt:formatNumber value="${totalPay}" pattern="#,###" />
											원</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<!--<input type="hidden" id="qpgnum" name="qpgnum" value="${qpgnum}">-->
					<table width="100%">
						<tr>
							<td width="60%" align="right">
								<div class="input-group input-daterange" id="QnaDT" name="QnaDT">
									<div class="input-group input-daterange">
										<input type="text" class="form-control" id="qStartDT"
											name="qStartDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${qStartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="qEndDT"
											name="qEndDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${qEndDT}">
									</div>
								</div>
							</td>
							<td width="40%" align="right">
								<button type="button" onclick="SearchQDT()"
									class="btn btn-secondary">검색</button>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10px"></td>
						</tr>
					</table>
					<div class="panel panel-default">
						<div class="panel-heading">문의내역</div>
						<div class="panel-body">
							<table class="table table-list-search"
								style="text-align: center;">
								<thead style="text-align: center;">
									<tr>
										<th width="15%"><center>번호</center></th>
										<th width="40%"><center>제목</center></th>
										<th width="30%"><center>날짜</center></th>
										<th width="15%"><center>답변</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${qdtos}" var="qdto">
										<tr>
											<td>${qdto.getNUM()}</td>
											<td>${qdto.getQ_TITLE()}</td>
											<td>${qdto.getQ_DATE()}</td>
											<td>${qdto.getQ_chkREPLY()}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td colspan="4" align="center"><a
											href="${QLink}&lpgnum=${lpgnum}&qpgnum=1"
											style="text-decoration: none">${qprev}${qprev}</a> <a
											href="${QLink}&lpgnum=${lpgnum}&qpgnum=${qbefore}"
											style="text-decoration: none">${qprev}</a> <c:forEach
												items="${qpg}" var="qp">
												<a href="${QLink}&lpgnum=${lpgnum}&qpgnum=${qp}"
													style="text-decoration: none">${qp}</a>
											</c:forEach> <a href="${QLink}&lpgnum=${lpgnum}&qpgnum=${qafter}"
											style="text-decoration: none">${qnext}</a> <a
											href="${QLink}&lpgnum=${lpgnum}&qpgnum=${qlast}"
											style="text-decoration: none">${qnext}${qnext}</a></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<!--<input type="hidden" id="lpgnum" name="lpgnum" value="${lpgnum}">-->
					<table width="100%">
						<tr>
							<td width="60%" align="right">
								<div class="input-group input-daterange" id="LogDT" name="LogDT">
									<div class="input-group input-daterange">
										<input type="text" class="form-control" id="lStartDT"
											name="lStartDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${lStartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="lEndDT"
											name="lEndDT" data-date-format="yyyy-mm-dd" maxlength="15"
											value="${lEndDT}">
									</div>
								</div>
							</td>
							<td width="40%" align="right">
								<button type="button" onclick="SearchLDT()"
									class="btn btn-secondary">검색</button>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10px"></td>
						</tr>
					</table>
					<div class="panel panel-default">
						<div class="panel-heading">활동내역</div>
						<div class="panel-body">
							<table class="table table-list-search"
								style="text-align: center;">
								<thead style="text-align: center;">
									<tr>
										<th width="15%"><center>번호</center></th>
										<th width="55%"><center>활동</center></th>
										<th width="30%"><center>날짜</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ldtos}" var="ldto">
										<tr>
											<td>${ldto.getNUM()}</td>
											<td>${ldto.getL_ACTIVITY()}</td>
											<td>${ldto.getL_DATE()}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td colspan="4" align="center"><a
											href="${LLink}&qpgnum=${qpgnum}&lpgnum=1"
											style="text-decoration: none">${lprev}${lprev}</a> <a
											href="${LLink}&qpgnum=${qpgnum}&lpgnum=${lbefore}"
											style="text-decoration: none">${lprev}</a> <c:forEach
												items="${lpg}" var="lp">
												<a href="${LLink}&qpgnum=${qpgnum}&lpgnum=${lp}"
													style="text-decoration: none">${lp}</a>
											</c:forEach> <a href="${LLink}&qpgnum=${qpgnum}&lpgnum=${lafter}"
											style="text-decoration: none">${lnext}</a> <a
											href="${LLink}&qpgnum=${qpgnum}&lpgnum=${llast}"
											style="text-decoration: none">${lnext}${lnext}</a></td>
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
		$("#LogDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#LogDT").datepicker("setDate", new Date());
		$("#LogDT").each(function() {
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
		$("#QnaDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#QnaDT").datepicker("setDate", new Date());
		$("#QnaDT").each(function() {
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