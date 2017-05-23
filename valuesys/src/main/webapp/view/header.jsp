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
		공지사항 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="../view/board.do" style="text-decoration: none; color: black">자유게시판</a>
	</div>
	<hr style="clear: both; color: gray;">