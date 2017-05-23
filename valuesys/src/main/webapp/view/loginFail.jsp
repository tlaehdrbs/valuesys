<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css'
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet"
	href="../assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="../assets/css/headers/header-default.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="../assets/plugins/animate.css">
<link rel="stylesheet" href="../assets/plugins/line-icons/line-icons.css">
<link rel="stylesheet"
	href="../assets/plugins/font-awesome/css/font-awesome.min.css">

<!-- CSS Page Style -->
<link rel="stylesheet" href="../assets/css/pages/page_log_reg_v2.css">

</head>

<body style="background-color: white;">
	<div id="container">
		<jsp:include page="header.jsp" flush="false" />
		<div class="reg-block-cus" style="border: 1px dotted gray;">
			<div class="reg-block-header">
				<h2>로그인 결과</h2>				
			</div>		
			<h3 align="center">아이디와 패스워드가 일치하지 않습니다.</h3>
			<h3 align="center">다시 확인해주세요.</h3>
			<br>
			<div class="row">
				<span class="col-md-5 col-md-offset-1">
					<button type="button" class="btn-u btn-block" onclick="location.href='login.do'">로그인</button>
				</span>
				<span class="col-md-5 col-md-offset-0">
					<button type="button" class="btn-u btn-block" onclick="location.href='main.do'">메인으로</button>
				</span>				
			</div>		
		</div>
		<jsp:include page="footer.jsp" flush="false" />
	</div>
	<!-- JS Global Compulsory -->
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
        });
</script>

</body>
</html>
