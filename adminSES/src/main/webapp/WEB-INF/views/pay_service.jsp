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
	font-size: 1.4rem;
	border: 1px solid;
}

.tblSearch {
	font-size: 1.4rem;
	border: 1px solid;
}

table {
	font-size: 1.3rem;
}

panel-heading {
	font-size: 1.4rem;
}

.payUserInfo {
	font-size: 1.5rem;
	border: 1px solid;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});
	
	function DoEnable() {
        if(document.getElementById('joinChk_s')[0].checked == true) {
        	document.getElementById('payChk_s').attr("disabled", false);
        } else if(document.getElementById('joinChk_s')[1].checked == true) {
        	document.getElementById('payChk_s').attr("disabled", true);
        }
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
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<div class="panel panel-default">
						<div class="panel-heading">유료서비스 정보 및 검색</div>
						<div class="panel-body" align="center">
							<table width="100%" height="100%">
								<tr>
									<td>
										<table class="tblInfo" border="1" width="100%" height="100%">
											<tr>
												<td colspan="3" align="center">유료서비스(납부 말일:20일)</td>
											</tr>
											<tr>
												<td align="right" width="48%">총 이용자 수</td>
												<td width="4%"></td>
												<td width="48%">${Usercnt} 명</td>
											</tr>
											<tr>
												<td align="right" width="48%">이번 달 예정 금액</td>
												<td width="4%"></td>
												<td width="48%">000,000,000 원</td>
											</tr>
											<tr>
												<td align="right" width="48%">현재 금액</td>
												<td width="4%"></td>
												<td width="48%">000,000,000 원</td>
											</tr>
										</table>
									<td>
								</tr>
								<tr>
									<td height="5px"></td>
								</tr>
								<tr>
									<td>
										<table class="tblSearch" width="100%" height="100%">
											<tr>
												<td align="right" width="30%">가입여부</td>
												<td width="5px"></td>
												<td>
													<input type="radio" name="joinChk_s" id="jRd_join" value="Yes" onclick="DoEnable();" checked> 가입
													<input type="radio" name="joinChk_s" id="jRd_nojoin" value="No" onclick="DoEnable();"> 비가입

												</td>
											</tr>
											<tr>
												<td align="right" width="30%">납부</td>
												<td width="5px"></td>
												<td>
													<input type="radio" name="payChk_s" id="pRd_pay" value="Yes" checked> 납부
													<input type="radio" name="payChk_s" id="pRd_nopay" value="No"> 미납
												</td>
											</tr>
											<tr>
												<td align="right" width="30%">아이디</td>
												<td width="5px"></td>
												<td><input type="text" class="form-control"
													id="inputID" width="90%" placeholder="아이디"></td>
											</tr>
											<tr>
												<td height="10px"></td>
											</tr>
											<tr>
												<td align="right" width="30%">성명</td>
												<td width="5px"></td>
												<td><input type="text" class="form-control"
													id="inputName" width="90%" placeholder="성명"></td>
											</tr>
											<tr>
												<td height="5px"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="5px"></td>
								</tr>
								<tr>
									<td align="right"><button type="button"
											class="btn btn-secondary">조회</button></td>
								</tr>
								<tr>
									<td height="5px"></td>
								</tr>
								<tr>
									<td>
										<table class="table table-list-search"
											style="text-align: center;">
											<thead style="text-align: center;">
												<tr>
													<th width="15%"><center>번호</center></th>
													<th width="25%"><center>아이디</center></th>
													<th width="30%"><center>가입여부</center></th>
													<th width="30%"><center>납부여부</center></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>00</td>
													<td>아이디</td>
													<td>가입</td>
													<td>미납</td>
												</tr>
												<tr>
													<td>00</td>
													<td>아이디</td>
													<td>가입</td>
													<td>미납</td>
												</tr>
												<tr>
													<td>00</td>
													<td>아이디</td>
													<td>가입</td>
													<td>미납</td>
												</tr>
												<tr>
													<td>00</td>
													<td>아이디</td>
													<td>가입</td>
													<td>미납</td>
												</tr>
												<tr>
													<td>00</td>
													<td>아이디</td>
													<td>가입</td>
													<td>미납</td>
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
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<div class="panel panel-default">
						<div class="panel-heading">선택된 회원 유료서비스 정보</div>
						<div class="panel-body" align="center">
							<table class="payUserInfo" border="1" width="100%" height="550px">
								<tr height="15%">
									<td align="right">아이디</td>
									<td width="10px"></td>
									<td>Honggildong</td>
								</tr>
								<tr height="15%">
									<td align="right">성명</td>
									<td width="10px"></td>
									<td>홍길동</td>
								</tr>
								<tr height="15%">
									<td align="right">유료서비스</td>
									<td width="10px"></td>
									<td>가입</td>
								</tr>
								<tr height="15%">
									<td align="right">유료서비스 가입일</td>
									<td width="10px"></td>
									<td>0000-00-00</td>
								</tr>
								<tr height="15%">
									<td align="right">이번 달 납부 여부</td>
									<td width="10px"></td>
									<td>납부</td>
								</tr>
								<tr height="15%">
									<td align="right">총 납부 금액</td>
									<td width="10px"></td>
									<td>000,000,000 원</td>
								</tr>
								<tr>
									<td height="10%" colspan="3">
										<table>
											<tr>
												<td><button type="button" class="btn btn-secondary">서비스 가입</button></td>
												<td><button type="button" class="btn btn-secondary">서비스 해지</button></td>
												<td><button type="button" class="btn btn-secondary">납부 처리</button></td>
												<td><button type="button" class="btn btn-secondary">미납 처리</button></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
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