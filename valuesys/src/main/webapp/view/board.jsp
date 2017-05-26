<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<script language="JavaScript" src="../js/board.js?v=1"></script>

</head>
<body style="background-color: white;" onload="alarm()" id="alarm">
	<div id="container">
		<jsp:include page="header.jsp" flush="false" />
		<center>
		<div id="content" style="width: 800px;background-color: white;">
			<div class="table-search-v1 panel panel-u margin-bottom-30" style="margin-top: 20px;'">
				<div class="panel-heading">
					<h3 class="panel-title"><i class="icon-note"></i> 자유 게시판 (총 ${getRowCount}개의 게시물이 있습니다.)</h3>	
				</div>	
				<%-- <input type="hidden" name="menuType" value="${menuType}"> --%>
				
				<div class="table-responsive">
					<table id="resultTable" class="table table-bordered table-striped table-hover tablesorter">
						<thead>
							<tr>
								<th width="10%" style="text-align: center;">글번호</th>
								<th width="50%" style="text-align: center;">제목</th>
								<th width="15%" style="text-align: center;">작성자</th>
								<th width="15%" style="text-align: center;">등록일</th>
								<th width="10%" style="text-align: center;">조회수</th>
							</tr>
						</thead>
						<c:if test="${getRowCount == 0}">
							<tr>
								<td colspan="5" style="text-align:center">등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${getRowCount != 0}">
							<c:forEach items="${boardList}" var="boardList">
								<tr style="text-align: center;">
									<td>${boardList.boardNumber}</td>
									<%-- <td><a href="../common/shopBoardDetail.do?boardNumber=${shopBoard.boardNumber}&menuType=${menuType}">${boardList.boardTitle}</a></td> --%>
									<td><a href="../view/boardDetail.do?boardNumber=${boardList.boardNumber }">${boardList.boardTitle}</a></td>
									<td>${boardList.boardWriter}</td>
									<td>${boardList.boardDate}</td>
									<td>${boardList.boardReadCount}</td>
								</tr>
							</c:forEach>
						</c:if>			
					</table>		
				</div>		
				
				${pagingHtml}
				
				<div class="row" style="margin-top: 5px; margin-bottom: 10px;">
					<div class="col-md-2 col-md-offset-4">			
						<select class="form-control" name="searchBoardType" id="searchBoardType">
							<c:forEach items="${searchBoardTypeList}" var="data">
								<c:if test="${searchBoardType eq data.key}">
									<option value="${data.key}" selected="selected">${data.value}</option>					
								</c:if>	
								<c:if test="${searchBoardType ne data.key}">
									<option value="${data.key}">${data.value}</option>					
								</c:if>				
							</c:forEach>				
						</select>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" class="form-control" name="searchBoardText" id="searchBoardText" value="${searchBoardText}">
							<span class="input-group-btn">
								<button class="btn-u" type="button" style="height: 34px" onclick="searchShopBoard()">							
									<i class="fa fa-search"></i>
								</button>
							</span>	
						</div>
					</div>
					<c:if test="${sessionScope.id != null}">
						<div class="col-md-3">
							<button class="btn-u" type="button" onclick="location.href='../view/boardWrite.do'"><i class="icon-pencil"></i> 글쓰기</button>				
						</div>			
					</c:if>
				</div>	
			</div>
			</div>
			</center>
		<jsp:include page="footer.jsp" flush="false" />
	</div>
			
</body>
</html>