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
<script type="text/javascript">
	function check() {
		if ($('#QTitle').val() == "") {
			alert("제목을 입력하세요!!");
			$('#QTitle').focus();
			return false;
		} else if ($('#QWriter').val() == "") {
			alert("작성자를 입력하세요!!");
			$('#QWriter').focus();
			return false;
		} else if ($('#QContent').val() == "") {
			alert("내용을 입력하세요!!");
			$('#QContent').focus();
			return false;
		} else if ($('#QReply').val() == "") {
			alert("답변을 입력하세요!!");
			$('#QReply').focus();
			return false;
		} else {
			return true;
		}
	}
	
	function DeleteQ() {
		var Qnum = $('#Qnum').val();

		location.href = "delete_qna?Qnum="+Qnum;
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
		<div class="container-fluid">
			<div class="row-fluid" style="width: 110%">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3>
									받은문의-
									<c:set var="Qnum" value="${dto.getQ_NUM()}" />
									<c:choose>
										<c:when test="${empty Qnum}">작성</c:when>
										<c:otherwise>
											답변
										</c:otherwise>
									</c:choose>
								</h3>
							</div>
							<div class="panel-body">
								<!-- Contents -->
								<center>
									<form action="${formAction}" class="form-horizontal"
										id="QnaForm" name="QnaForm" method="post"
										onsubmit="return check()">
										<input type="hidden" id="Qnum" name="Qnum"
											value="${dto.getQ_NUM()}">
										<table width="100%" height="100%">
											<tr>
												<td width="5%" align="right">번호</td>
												<td width="5%"></td>
												<td width="90%"><c:set var="Qnum"
														value="${dto.getQ_NUM()}" /> <c:choose>
														<c:when test="${empty Qnum}">
														${NewQnum}
													</c:when>
														<c:otherwise>
														${dto.getQ_NUM()}
													</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td colspan="3" height="10px"></td>
											</tr>
											<tr>
												<td width="5%" align="right">제목</td>
												<td width="5%"></td>
												<td width="90%"><input type="text" class="form-control"
													id="QTitle" name="QTitle" placeholder="제목"
													value="${dto.getQ_TITLE()}"
													<c:if test="${!empty Qnum}">disabled</c:if>></td>
											</tr>
											<tr>
												<td colspan="3" height="10px"></td>
											</tr>
											<tr>
												<td width="5%" align="right">작성자</td>
												<td width="5%"></td>
												<td width="90%"><c:choose>
														<c:when test="${empty Qnum}">
															<select class="custom-select" id="QWriter" name="QWriter">
																<c:forEach items="${sKinds}" var="dto">
																	<option value="${dto}">${dto}</option>
																</c:forEach>
															</select>
														</c:when>
														<c:otherwise>
															<input type="text" class="form-control" id="QWriter"
																name="QWriter" placeholder="작성자"
																value="${dto.getM_ID()}"
																<c:if test="${!empty Qnum}">disabled</c:if>></td>
												</c:otherwise>
												</c:choose>
											</tr>
											<tr>
												<td colspan="3" height="10px"></td>
											</tr>
											<tr>
												<td width="5%" align="right">비밀번호</td>
												<td width="5%"></td>
												<td width="90%"><input type="text" class="form-control"
													id="QPwd" name="QPwd" placeholder="작성자"
													<c:choose>
													<c:when test="${empty Qnum}">
														value="9944"
													</c:when>
													<c:otherwise>
														value="${dto.getQ_PWD()}"
													</c:otherwise>
												</c:choose>
													disabled></td>
											</tr>
											<tr>
												<td colspan="3" height="10px"></td>
											</tr>
											<tr>
												<td width="5%" align="right">날짜</td>
												<td width="5%"></td>
												<td width="90%">
													<table>
														<tr>
															<td width="40%">
																<div class="input-group input-daterange" id="QnaDT">
																	<div class="input-group input-daterange">
																		<input type="text" class="form-control" id="qnaDT"
																			name="qnaDT" data-date-format="yyyy-mm-dd"
																			maxlength="15"
																			<c:choose>
										<c:when test="${empty Qnum}">value="${today}"</c:when>
										<c:otherwise>
											value="${dto.getQ_DATE()}"
										</c:otherwise>
									</c:choose>
																			<c:if test="${!empty Qnum}">disabled</c:if>>
																	</div>
																</div>
															</td>
															<td width="60%"></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="3" height="10px"></td>
											</tr>
											<tr>
												<td width="10%" align="right">내용</td>
												<td width="3%"></td>
												<td width="87%"><textarea class="form-control"
														id="QContent" name="QContent" rows="10"
														<c:if test="${!empty Qnum}">disabled</c:if>>${dto.getQ_CONTENT()}</textarea></td>
											</tr>
											<tr>
												<td colspan="3" height="50px"></td>
											</tr>
											<tr>
												<td width="10%" align="right">답변</td>
												<td width="3%"></td>
												<td width="87%"><textarea class="form-control"
														id="QReply" name="QReply" rows="5">${dto.getQ_REPLY()}</textarea></td>
											</tr>
											<tr>
												<td colspan="3" height="50px"></td>
											</tr>
											<tr>
												<td colspan="3" align="center"><button type="submit"
														class="btn btn-secondary">답변하기</button>&nbsp;&nbsp;<button type="button"
														class="btn btn-secondary" onclick="DeleteQ()">삭제</button></td>
											</tr>
										</table>
									</form>
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		$("#QnaDT").datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
			format : "yyyy/mm/dd",
			endDate : "today"
		});
		$("#QnaDT").datepicker("setDate", new Date());
	</script>
</body>
</html>