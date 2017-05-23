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

<!-- <script language="JavaScript" src="../js/common/registrationJs.js?v=3"></script> -->

<link rel="stylesheet" href="../css/body.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">

<script>
	function checkLogin()
	{
		var id = document.loginForm.id.value;
		var password = document.loginForm.password.value;
		$("#checkState").val($("input[type='checkbox']").is(":checked"));
		
		if(id == "" || password == "")
		{
			alert("아이디와 패스워드를 입력하세요.");
			return false;	
		}
		document.loginForm.submit();
	}
	
	function getCookie(cookieId)
	{
		if(cookieId != null && cookieId != "")
		{
			$("#id").val(cookieId);
			$("#password").focus();
			$("input[type='checkbox']").attr("checked", true);
		}
		else
		{
			$("#id").focus();
			$("input[type='checkbox']").attr("checked", false);
		}
	}
</script>

</head>
<body style="background-color: white;">
	<div id="container">
		<jsp:include page="header.jsp" flush="false" />
		<div class="reg-block" style="border: 1px dotted gray;">
			<div class="reg-block-header">
				<h2>로그인</h2>
			</div>
			<form name="loginForm" action="login.do" method="post">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon"><i class="icon-user"></i></span>
					<input type="text" id="id" name="id" class="form-control" placeholder="ID">
				</div>
				<div class="input-group margin-bottom-30">
					<span class="input-group-addon"><i class="fa fa-key"></i></span>
					<input type="password" id="password" name="password" class="form-control" placeholder="패스워드" onKeyDown="if (event.keyCode==13) checkLogin()">
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" >
						<p>아이디 저장</p>
					</label>
					<input type="hidden" id="checkState" name="checkState">
				</div>				
				<span class="input-group-addon">
					<a class="color-green" href="registration.do">회원가입</a>
				</span>	
				<hr>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<button type="button" class="btn-u btn-block" onclick="checkLogin()">로그인</button>										
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="footer.jsp" flush="false" />
	</div>
	<!-- Script -->
	<script type="text/javascript"
		src="../assets/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="../assets/plugins/jquery/jquery-migrate.min.js"></script>
	<script type="text/javascript"
		src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript" src="../assets/plugins/back-to-top.js"></script>
	<script type="text/javascript"
		src="../assets/plugins/backstretch/jquery.backstretch.min.js"></script>
	<!-- JS Customization -->
	<script type="text/javascript" src="../assets/js/custom.js"></script>
	<!-- JS Page Level -->
	<script type="text/javascript" src="../assets/js/app.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
       	 App.init();
       	 getCookie("${cookieId}");
        });
	</script>
</body>
</html>
