<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="../css/header.css">	

	<c:if test="${sessionScope.id==null }">
		<div id="topbar">
			<font style="float: right"> <a href="../view/registration.do">회원가입</a> | <a href="../view/login.do">로그인</a></font>
		</div>
	</c:if>
	<c:if test="${sessionScope.id!=null }">
		<div id="topbar">
			<font style="float: right"> <font style="color: red"> ${sessionScope.id }</font>님 환영합니다!&nbsp; | &nbsp;<a href="../view/logout.do">로그아웃</a></font>
		</div>
	</c:if>
		<center>
			<font id="logo"><a href="../view/main.do" style="text-decoration: none;color: green">VALUESYS</a></font>
		</center>
	<div id="menu">
		<a href="#" onmousemove="change()" style="text-decoration: none;color: black" id="notice">공지사항</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="../view/board.do" style="text-decoration: none; color: black">자유게시판</a>
	</div>
	<hr style="clear: both; color: gray;">
	
<!-- JS Global Compulsory -->           
<script type="text/javascript" src="../assets/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script> 
<!-- JS Implementing Plugins -->           
<script type="text/javascript" src="../assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="../assets/plugins/smoothScroll.js"></script>
<script type="text/javascript" src="../assets/plugins/cube-portfolio/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
<!-- JS Page Level -->
<script type="text/javascript" src="../assets/js/app.js"></script>
<script type="text/javascript" src="../assets/js/plugins/cube-portfolio/cube-portfolio-2.js"></script>

<script type="text/javascript">
	function change()
	{
		var notice = $("#notice").html();
		var notice = "기능 구현 안됨"
		$("#notice").attr("style", "color:red").attr("style", "text-decoration:none")
		$("#notice").html(notice)
	}
</script>