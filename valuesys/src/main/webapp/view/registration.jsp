<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css'
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet" href="../assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="../assets/css/headers/header-default.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="../assets/plugins/animate.css">
<link rel="stylesheet" href="../assets/plugins/line-icons/line-icons.css">
<link rel="stylesheet" href="../assets/plugins/font-awesome/css/font-awesome.min.css">

<!-- CSS Page Style -->
<link rel="stylesheet" href="../assets/css/pages/page_log_reg_v2.css">

<script language="JavaScript" src="../js/registration.js?v=3"></script>

<link rel="stylesheet" href="../css/body.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">

</head>
<body style="background-color: white;">
	<div id="container">
		<jsp:include page="header.jsp" flush="false" />
		<center>	
		<div id="content" style="background-color: white;">
			<div class="reg-block" style="border: 1px dotted gray;">
				<div class="reg-block-header">
					<h2>회원가입</h2>
					<p>
						이미 계정이 있으신가요? 
						<a class="color-green" href="login.do">로그인</a>
					</p>
				</div>
				<form name="registrationForm" action="registration.do" method="post">
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="icon-user-follow"></i></span>
						<input type="text" id="usersName" name="usersName" class="form-control" placeholder="이름">
					</div>
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="icon-star"></i></span>					
						<input type="text" id="usersId"  name="usersId" class="form-control" placeholder="ID" onblur="checkId()">
					</div>
					<div align="center">
						<span id="idCheck"></span>
					</div>
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" id="usersPassword" name="usersPassword" class="form-control" placeholder="패스워드 (영문+숫자  8~12자리)">
					</div>
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="fa fa-key"></i></span>
						<input type="password" id="usersPassword1" name="usersPassword1" class="form-control" placeholder="패스워드 확인">
					</div>
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="icon-screen-smartphone"></i></span>
						<input type="text" id="usersPhone" name="usersPhone" class="form-control" placeholder="연락처 (- 생략)">
					</div>
					<div class="input-group margin-bottom-10">
						<span class="input-group-addon"><i class="icon-envelope-open"></i></span>
						<input type="text" id="usersEmail" name="usersEmail" class="form-control" placeholder="이메일">
					</div>
					<hr>
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							<input type="button" id="ok" class="btn-u btn-block" onclick="checkNull()" value="가입하기">
						</div>
					</div>
				</form>
			</div>
		</div>
		</center>
		<jsp:include page="footer.jsp" flush="false" />
	</div>
	
	<script type="text/javascript" src="../assets/plugins/jquery/jquery.min.js"></script>
	
</html>

