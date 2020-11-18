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

.tblInfouser {
	font-size: 2.0rem;
}

table {
	font-size: 1.3rem;
}

thead {
	font-size: 1.3rem;
}

#SnsUser {
	font-size: 1.6rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});

	//url 에서 parameter 추출
	function getParam(sname) {
		var params = location.search.substr(location.search.indexOf("?") + 1);
		var sval = "";
		params = params.split("&");
		for (var i = 0; i < params.length; i++) {
			temp = params[i].split("=");
			if ([ temp[0] ] == sname) {
				sval = temp[1];
			}
		}
		return sval;
	}
	
	function SearchSnsQLog() {
		var mId = getParam("mId");
		var StartDT = $('#StartDT').val();
		var EndDT = $('#EndDT').val();
		location.href = "sns_qsch?mId="+mId+"&StartDT="+StartDT+"&EndDT="+EndDT+";";
	}

	function isRegister() {
		var mId = getParam("mId");
		if (mId == null || mId == "") {
			$("#btnRegister").show();
			$("#btnRemove").hide();
		} else {
			$("#btnRegister").hide();
			$("#btnRemove").show();
		}
	}
	window.onload = isRegister;
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
							<table class="tblInfo" width="100%">
								<tr>
									<td>SNS사 담당자 정보</td>
									<td colspan="10" align="right">
										<button type="button" id="btnRegister" name="btnRegister"
											class="btn btn-secondary">등록</button>
										<button type="button" id="btnRemove" name="btnRemove"
											class="btn btn-secondary">수정</button>
									</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<table class="tblInfo" width="100%">
								<tbody>
									<tr>
										<td align="right" width="10%">성명</td>
										<td width="1%"></td>
										<td width="20%">
											<table width="90%">
												<tr>
													<td><input type="text" class="form-control" id="sName"
														name="sName" placeholder="성명" value="${dto.getM_NAME()}"></td>
												</tr>
											</table>
										</td>
										<td align="right" width="10%">SNS사</td>
										<td width="1%"></td>
										<td width="10%" font-size="1.3rem"><select
											class="custom-select" id="sKind" name="sKind">
												<option selected="true">${dto.getM_ID()}</option>
												<option>Naver</option>
												<option>Facebook</option>
												<option>Google</option>
												<option>Kakaotalk</option>
										</select>
										<td align="right" width="10%">전화번호</td>
										<td width="1%"></td>
										<td width="40%">
											<table border="0" width="77%">
												<tr>
													<td width="30%"><select class="custom-select"
														id="sTel1" name="sTel1">
															<option selected="true">0${dto.getM_TEL1()}</option>
															<option>010</option>
															<option>011</option>
															<option>012</option>
													</select></td>
													<td align="center">-</td>
													<td width="35%"><input type="text"
														class="form-control" id="sTel2" name="sTel2"
														value="${dto.getM_TEL2()}"></td>
													<td align="center">-</td>
													<td width="35%"><input type="text"
														class="form-control" id="sTel3" name="sTel3"
														value="${dto.getM_TEL3()}"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="9" height="10px"></td>
									</tr>
									<tr>
										<td align="right" width="10%">부서</td>
										<td width="1%"></td>
										<td width="20%">
											<table width="90%">
												<tr>
													<td><input type="text" class="form-control" id="sDept"
														name="sDept" placeholder="부서" value="${dto.getS_DEPT()}"></td>
												</tr>
											</table>
										</td>
										<td align="right" colspan="3">입사 및 퇴사일</td>
										<td colspan="6"><table width="100%">
												<tr>
													<td width="10px"></td>
													<td>
														<div class="input-group input-daterange" id="joinDT">
															<div class="input-group input-daterange">
																<input type="text" class="form-control" id="inDT"
																	name="inDT" data-date-format="yyyy-mm-dd"
																	maxlength="15"
																	value="${dto.getS_START_DT()}">
																<div class="input-group-addon">to</div>
																<input type="text" class="form-control" id="exitDT"
																	name="exitDT" data-date-format="yyyy-mm-dd"
																	maxlength="15"
																	value="${dto.getS_END_DT()}">
															</div>
														</div>
													</td>
												</tr>
											</table></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="width: 110%">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading" id="SnsUser">SNS사 이용자 수</div>
						<div class="panel-body" align="center">
							<table class="tblInfouser">
								<tr align="center">
									<td>이용자</td>
									<td width="5px"></td>
									<td>${svUsercnt}명</td>
									<td width="5px"></td>
									<td>/</td>
									<td width="5px"></td>
									<td>총 회원</td>
									<td width="5px"></td>
									<td>${gcnt}명</td>
									<td width="5px"></td>
									<td>=></td>
									<td width="5px"></td>
									<td>${userAvg}%</td>
								</tr>
							</table>
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
										<input type="text" class="form-control" id="StartDT" name="StartDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${StartDT}">
										<div class="input-group-addon">to</div>
										<input type="text" class="form-control" id="EndDT" name="EndDT"
											data-date-format="yyyy-mm-dd" maxlength="15" value="${EndDT}">
									</div>
								</div>
							</td>
							<td width="8%" align="right">
								<button type="button" onclick="SearchSnsQLog()" class="btn btn-secondary">검색</button>
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
								<th width="50%"><center>제목</center></th>
								<th width="35%"><center>날짜</center></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dtos}" var="dto">
								<tr>
									<td>${dto.getNUM()}</td>
									<td><a href="#">${dto.getQ_TITLE()}</a></td>
									<td>${dto.getQ_DATE()}</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5"></td>
							</tr>
							<tr align="center">
								<td colspan="5"><a
									href="${snsLink}&pgnum=1"
									style="text-decoration: none">${prev}${prev}</a> <a
									href="${snsLink}&pgnum=${before}"
									style="text-decoration: none">${prev}</a> <c:forEach
										items="${pg}" var="p">
										<a href="${snsLink}&pgnum=${p}"
											style="text-decoration: none">${p}</a>
									</c:forEach> <a href="${snsLink}&pgnum=${after}"
									style="text-decoration: none">${next}</a> <a
									href="${snsLink}&pgnum=${last}"
									style="text-decoration: none">${next}${next}</a></td>
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