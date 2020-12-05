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
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<style>
.input-group {
	margin-top: 1em;
	margin-bottom: 1em;
}

.login-box {
	width: 300px;
	align: center;
	font-size: 1em;
	color: #aaa;
	margin-top: 1em;
	margin-bottom: 1em;
	padding-top: 0.5em;
	padding-bottom: 0.5em;
	line-height: 2.3em;
	align: center;
}
</style>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
function check(){
	//비밀번호 길이 체크(4~8자 까지 허용)
	if (document.pwForm.newPW.value.length<4 || document.pwForm.newPW.value.length>12) {
		alert("비밀번호를 4~12자까지 입력해주세요.")
		document.myform.mPw.focus()
		document.myform.mPw.select()
		return false;
	}
	 var pw = document.pwForm.newPW.value;
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if(pw.length < 10 || pw.length > 20){
		alert("10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}else if(pw.search(/\s/) != -1){
		alert("비밀번호는 공백 없이 입력해주세요.");
		return false;
	}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
		alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		return false;
	}
	if (document.pwForm.newPW.value != document.pwForm.chknewPW.value) {
		alert("입력한 2개의 비밀번호가 일치하지 않습니다!!");
		document.pwForm.chknewPW.focus();
		document.pwForm.chknewPW.select();
		return false;
	}
	if (document.pwForm.nowPW.value == document.pwForm.newPW.value) {
		alert("현 비밀번호와 새 비밀번호가 일치합니다!!");
		document.pwForm.newPW.focus();
		document.pwForm.newPW.select();
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
		<div class="container-fluid">
			<table width="100%" height="600px">
				<tr>
					<td align="center">
						<div class="panel-heading">
							<h3 class="panel-title">비밀번호 변경</h3>
						</div>
						<form action="doChgPW" name="pwForm" method="post"
							onsubmit="return check()">
							<div class="panel-body">
								<br />
								<table width="70%">
									<tr>
										<td width="30%" align="right">현 비밀번호&nbsp;&nbsp;</td>
										<td width="70%"><input type="password" name="nowPW"
											id="nowPW" class="form-control" placeholder="현 비밀번호"
											required="required" style="width: 70%"></td>
									</tr>
									<tr style="height: 10px;">
										<td></td>
									</tr>
									<tr>
										<td width="30%" align="right">새 비밀번호&nbsp;&nbsp;</td>
										<td width="70%"><input type="password" name="newPW"
											id="newPW" class="form-control" placeholder="새 비밀번호"
											required="required" style="width: 70%"></td>
									</tr>
									<tr style="height: 10px;">
										<td></td>
									</tr>
									<tr>
										<td width="30%" align="right">새 비밀번호 확인&nbsp;&nbsp;</td>
										<td width="70%"><input type="password" name="chknewPW"
											id="chknewPW" class="form-control" placeholder="새 비밀번호 확인"
											required="required" style="width: 70%"></td>
									</tr>
									<tr style="height: 20px;">
										<td></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="image"
											name="button" width="100px"
											src="${pageContext.request.contextPath}/resources/images/OK.png">
										</td>
									</tr>
								</table>
								<br />
							</div>
						</form>
						</div>
					</td>
				</tr>
			</table>
		</div>
		</main>
		<!-- contents -->
	</div>
</body>
</html>