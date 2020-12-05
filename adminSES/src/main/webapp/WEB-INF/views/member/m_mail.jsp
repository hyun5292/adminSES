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
table {
	font-size: 1.3rem;
}

.tblSearch {
	font-size: 1.3rem;
	border: 1px solid;
}

panel-heading {
	font-size: 1.4rem;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
	$('.input-daterange input').each(function() {
		$(this).datepicker('clearDates');
	});

	function SearchMember() {
		var mKind = $('#mKind').val();
		var mId = $('#mId').val();
		var mName = $('#mName').val();
		var result = "m_search";

		if (mKind == null || mKind == "") {
			mKind = "일반";
		}

		if (mKind == "직원") {
			result = "sch_emailA";
		} else {
			result = "sch_emailG";
		}

		location.href = result + "?mKind=" + mKind + "&mId=" + mId + "&mName="
				+ mName;
	}

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

	function SelectMembers() {
		var result = "";
		if (document.getElementById('chkGeneral').checked == true) {
			result += "전체 일반 회원";
		}
		if (document.getElementById('chkServiceUser').checked == true) {
			if (result != "") {
				result += ",";
			}
			result += "유료서비스 회원";
		}
		if (document.getElementById('chkAdmin').checked == true) {
			if (result != "") {
				result += ",";
			}
			result += "관리자";
		}

		result += "\n";

		$('#getMembers').text(result);
	}

	function AddMember(mId) {
		var result = $('#getMembers').val();
		if ($('#getMembers').val() != "") {
			result += ",";
			result += mId;
		} else {
			result += mId;
		}
		$('#getMembers').text(result);
	}

	function RemoveMembers() {
		$('#getMembers').text("");
	}
	
	function check() {
		var members = $('#getMembers').val();
		var title = $('#inputTitle').val();
		var message = $('#message').val();
		
		if(members == "") {
			alert("받을사람을 선택하세요!!");
			document.mailform.getMembers.focus();
			return false;
		} else if(title == "") {
			alert("제목을 입력하세요!!");
			document.mailform.inputTitle.focus();
			return false;
		} else if(message == "") {
			alert("내용을 선택하세요!!");
			document.mailform.message.focus();
			return false;
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
		<div class="container-fluid"></div>
		<div class="row-fluid" style="width: 110%">
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
				<div class="panel panel-default">
					<div class="panel-heading">회원 선택</div>
					<div class="panel-body" align="center">
						<table width="100%" height="100%">
							<tr>
								<td align="right"><button type="button"
										class="btn btn-secondary" onclick="SearchMember()">조회</button></td>
							</tr>
							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td>
									<table class="tblSearch" width="100%" height="100%">
										<tr>
											<td align="right">회원분류</td>
											<td width="5px"></td>
											<td><select class="custom-select" id="mKind"
												name="mKind">
													<option value="${mKind}" selected="true">${mKind}</option>
													<option value="일반">일반</option>
													<option value="직원">직원</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">아이디</td>
											<td width="5px"></td>
											<td><input type="text" class="form-control" id="mId"
												name="mId" width="90%" placeholder="아이디" value="${mId}"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
										<tr>
											<td align="right">성명</td>
											<td width="5px"></td>
											<td><input type="text" class="form-control" name="mName"
												id="mName" width="90%" placeholder="성명" value="${mName}"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<td colspan="4" align="right">
									<hr />
									<button type="button" class="btn btn-secondary"
										onClick="SelectMembers()">선택</button>
									<button type="button" class="btn btn-secondary"
										onClick="RemoveMembers()">초기화</button>
								</td>
							</tr>
							<tr>
								<td height="10px"></td>
							</tr>
							<tr>
								<td>
									<table border="0" width="100%">
										<tr>
											<td><input type="checkbox" name="survey" id="chkGeneral"
												${chkGVal}> 전체 일반 회원</td>
											<td width="5px"></td>
											<td><input type="checkbox" name="survey"
												id="chkServiceUser" ${chkSVal}> 유료서비스 회원</td>
											<td width="5px"></td>
											<td><input type="checkbox" name="survey" id="chkAdmin"
												${chkAdVal}> 관리자</td>
										</tr>
									</table>
									<hr />
								</td>
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
												<th width="15%"><center></center></th>
												<th width="15%"><center>분류</center></th>
												<th width="30%"><center>아이디</center></th>
												<c:set var="mKind" value="${mKind}" />
												<c:choose>
													<c:when test="${mKind eq '직원'}">
														<th width="40%"><center>성명</center></th>
													</c:when>
													<c:otherwise>
														<th width="40%"><center>유료서비스</center></th>
													</c:otherwise>
												</c:choose>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${dtos}" var="dto">
												<tr>
													<td><button type="button" class="btn btn-secondary"
															onClick="AddMember('${dto.getM_ID()}')">선택</button></td>
													<td>${dto.getM_KIND()}</td>
													<td>${dto.getM_ID()}</td>
													<c:set var="mKind" value="${mKind}" />
													<c:choose>
														<c:when test="${mKind eq '직원'}">
															<td>${dto.getM_NAME()}</td>
														</c:when>
														<c:otherwise>
															<td>${dto.getM_SERVICE_CHK()}</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="4"></td>
											</tr>
											<tr align="center">
												<td colspan="4"><a href="${mlink}?pgnum=1${mlink2}"
													style="text-decoration: none">${prev}${prev}</a> <a
													href="${mlink}?pgnum=${before}${mlink2}"
													style="text-decoration: none">${prev}</a> <c:forEach
														items="${pg}" var="p">
														<a href="${mlink}?pgnum=${p}${mlink2}"
															style="text-decoration: none">${p}</a>
													</c:forEach> <a href="${mlink}?pgnum=${after}${mlink2}"
													style="text-decoration: none">${next}</a> <a
													href="${mlink}?pgnum=${last}${mlink2}"
													style="text-decoration: none">${next}${next}</a></td>
											</tr>
										</tfoot>
									</table>
								</td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
				<form action="doSend" class="form-horizontal" name="mailform"
					method="post" onsubmit="return check()">
					<div class="panel panel-default">
						<div class="panel-heading">메일 작성</div>
						<div class="panel-body" align="center">
							<table width="100%" height="100%">
								<tr>
									<td width="17%" align="right">받는사람</td>
									<td width="3%"></td>
									<td width="80%"><textarea class="form-control"
											id="getMembers" name="getMembers" rows="2" disabled="true"></textarea></td>
								</tr>
								<tr>
									<td colspan="3" height="10px"></td>
								</tr>
								<tr>
									<td width="17%" align="right">제목</td>
									<td width="3%"></td>
									<td width="80%"><input type="text" class="form-control"
										id="inputTitle" placeholder="제목"></td>
								</tr>
								<tr>
									<td colspan="3" height="10px"></td>
								</tr>
								<tr>
									<td width="17%" align="right">내용</td>
									<td width="3%"></td>
									<td width="80%"><textarea class="form-control"
											id="message" name="message" rows="23"></textarea></td>
								</tr>
								<tr>
									<td colspan="3" height="10px"></td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<button type="submit" id="sendbtn" name="sendbtn" class="btn btn-secondary">전송</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</form>
			</div>
		</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>