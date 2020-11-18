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
	
	function SearchQna() {
		var mId = $('#mId').val();
		var qStartDT = $('#qStartDT').val();
		var qEndDT = $('#qEndDT').val();
		
		location.href = "emp_qna_search?mId="+mId+"&qStartDT="+qStartDT+"&qEndDT="+qEndDT+";";
	}
	
	function SearchPayLog() {
		var mId = $('#mId').val();
		var pStartDT = $('#pStartDT').val();
		var pEndDT = $('#pEndDT').val();
		location.href = "emp_paylog_search?mId="+mId+"&pStartDT="+pStartDT+"&pEndDT="+pEndDT+";";
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
						<div class="panel-heading">회원정보</div>
						<div class="panel-body">
						<input type="hidden" id="mId" name="mId" value="${mdto.getM_ID()}">
							<table class="tblInfo" width="100%">
								<tbody>
									<tr>
										<td align="right">아이디</td>
										<td width="10px"></td>
										<td>${mdto.getM_ID()}</td>
										<td align="right">전화번호</td>
										<td width="10px"></td>
										<td>0${mdto.getM_TEL1()}-${mdto.getM_TEL2()}-${mdto.getM_TEL3()}</td>
										<td align="right"><a href="pay_service">유료서비스</a> 가입 여부</td>
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
										<td>000,000 원</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td colspan="6"></td>
										<td align="right">총 납부 금액</td>
										<td width="10px"></td>
										<td>000,000,000 원</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<table width="100%">
						<tr>
							<td width="60%" align="right">
								<div class="input-group input-daterange" id="QnaDT" name="QnaDT">
									<div class="input-group input-daterange">
										<input type="text" class="form-control" id="qStartDT" name="qStartDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${qStartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="qEndDT" name="qEndDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${qEndDT}">
									</div>
								</div>
							</td>
							<td width="40%" align="right">
								<button type="button" onclick="SearchQna()" class="btn btn-secondary">검색</button>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10px"></td>
						</tr>
					</table>
					<input type="hidden" value="${qpgnum}" name="qpgnum" id="qpgnum">
					<input type="hidden" value="${ppgnum}" name="ppgnum" id="ppgnum">
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
											href="${mgQLink}&qpgnum=1"
											style="text-decoration: none">${qprev}${qprev}</a> <a
											href="${mgQLink}&qpgnum=${qbefore}"
											style="text-decoration: none">${qprev}</a> <c:forEach
												items="${qpg}" var="qp">
												<a
													href="${mgQLink}&qpgnum=${qp}"
													style="text-decoration: none">${qp}</a>
											</c:forEach> <a
											href="${mgQLink}&qpgnum=${qafter}"
											style="text-decoration: none">${qnext}</a> <a
											href="${mgQLink}&qpgnum=${qlast}"
											style="text-decoration: none">${qnext}${qnext}</a></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<table width="100%">
						<tr>
							<td width="60%" align="right">
								<div class="input-group input-daterange" id="payDT" name="payDT">
									<div class="input-group input-daterange">
										<input type="text" class="form-control" id="pStartDT" name="pStartDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${pStartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="pEndDT" name="pEndDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${pEndDT}">
									</div>
								</div>
							</td>
							<td width="50%" align="right">
								<button type="button" onclick="SearchPayLog()" class="btn btn-secondary">검색</button>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10px"></td>
						</tr>
					</table>
					<div class="panel panel-default">
						<div class="panel-heading">결제내역</div>
						<div class="panel-body">
							<table class="table table-list-search"
								style="text-align: center;">
								<thead style="text-align: center;">
									<tr>
										<th width="15%"><center>번호</center></th>
										<th width="30%"><center>항목</center></th>
										<th width="25%"><center>금액</center></th>
										<th width="30%"><center>날짜</center></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pdtos}" var="pdto">
										<tr>
											<td>${pdto.getNUM()}</td>
											<td>${pdto.getPL_TITLE()}</td>
											<td>${pdto.getPL_PRICE()}원</td>
											<td>${pdto.getPL_DATE()}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td colspan="4" align="center"><a
											href="${mgPLLink}&ppgnum=1"
											style="text-decoration: none">${pprev}${pprev}</a> <a
											href="${mgPLLink}&ppgnum=${pbefore}"
											style="text-decoration: none">${pprev}</a> <c:forEach
												items="${plpg}" var="pp">
												<a href="${mgPLLink}&ppgnum=${pp}"
													style="text-decoration: none">${pp}</a>
											</c:forEach> <a
											href="${mgPLLink}&ppgnum=${pafter}"
											style="text-decoration: none">${pnext}</a> <a
											href="emp_paylog_search?mId=${mdto.getM_ID()}&pStartDT=${pStartDT}&pEndDT=${pEndDT}&qpgnum=1&ppgnum=${plast}"
											style="text-decoration: none">${pnext}${pnext}</a></td>
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
		$("#payDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#payDT").datepicker("setDate", new Date());
		$("#payDT").each(function() {
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