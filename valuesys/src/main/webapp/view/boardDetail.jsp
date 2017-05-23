<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">
    
<!-- Web Fonts -->
<link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet" href="../assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="../assets/css/headers/header-default.css">
<link rel="stylesheet" href="../assets/css/footers/footer-v1.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="../assets/plugins/animate.css">
<link rel="stylesheet" href="../assets/plugins/line-icons/line-icons.css">
<link rel="stylesheet" href="../assets/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/plugins/cube-portfolio/cubeportfolio/css/cubeportfolio.min.css">    
<link rel="stylesheet" href="../assets/plugins/cube-portfolio/cubeportfolio/custom/custom-cubeportfolio.css">
<link rel="stylesheet" href="../assets/css/pages/blog.css">
<link rel="stylesheet" href="../assets/css/pages/blog_magazine.css">
<link rel="stylesheet" href="../assets/css/pages/profile.css">

<!-- CSS Page Style -->
<link rel="stylesheet" href="../assets/css/pages/page_search_inner_tables.css">


<script type="text/javascript">
function shopBoardReplyWrite()
{
	if(${sessionScope.id == null})
	{
		alert("댓글은 로그인 후 사용 가능합니다.");
	}	
	else
	{    		
		var boardNumber = $("#boardNumber").val();
		var replyContent = $("#replyContent").val();
		$("#replyContent").val("");
		$.get("shopBoardReplyWrite.do", {boardNumber : boardNumber, replyContent : replyContent}, function(result)
		{			
			$("#replyDiv").html(result);
		})    		
	}    	
}
</script>

<title>We POS</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" flush="false" />
		
		<div class="container content" style="min-height: 700px;">			
			<div class="blog-page">
				<div class="col-md-10 col-md-offset-1">
					<div class="blog margin-bottom-40">
						<h2>[글제목]: &nbsp; ${selectBoard.boardTitle}</h2>
						<div class="blog-post-tags">
							<ul class="list-unstyled list-inline blog-info">
	                            <li><i class="fa fa-calendar"></i> ${selectBoard.boardDate}</li>
	                            <li><i class="fa fa-pencil"></i> ${selectBoard.boardWriter}</li>
	                            <li><i class="fa fa-comments"></i> ${selectBoard.boardReadCount}회 조회</li>
                        	</ul>
						</div>						
						<c:if test="${selectBoard.boardFile != null}">
							<div class="blog-img">
								<img class="img-responsive" src="${selectBoard.boardFile}" alt="" style="width: 80%;">
							</div>
						</c:if>	
						<pre style="font-size: 20px; background-color: white; border: 0px;">${shopBoard.boardContent}</pre>
						<hr>
						<b>첨부파일 : </b>
						<c:if test="${selectBoard.boardFile == null}">
							첨부된 파일이 없습니다.	
						</c:if>	
						<c:if test="${selectBoard.boardFile != null}">							
							<a href="file.do?boardFile=${fileName}">${selectBoard.boardFile}</a>
						</c:if>
						<div align="right">						
							<c:if test="${sessionScope.id eq shopBoard.totalId}">
								<button class="btn-u" type="button" onclick="location.href='shopBoardUpdate.do?boardNumber=${shopBoard.boardNumber}&menuType=${menuType}'">수정</button>																		
								<button class="btn-u" type="button" onclick="shopBoardDelete(${shopBoard.boardNumber}, ${shopBoard.shopCode}, '${fileName}', '${menuType}')">삭제</button>
							</c:if>
							<button class="btn-u" type="button" onclick="location.href='../common/shopBoardSelectMenu.do?shopCode=${shopBoard.shopCode}&menuType=${menuType}'">목록으로</button>							
						</div>
					</div>
					<div class="headline" ><h3>댓글</h3></div>					
					<input type="hidden" id="boardNumber" name="boardNumber" value="${shopBoard.boardNumber}">
					<div class="input-group">
						<textarea class="form-control" rows="3" id="replyContent" name="replyContent" placeholder="댓글은 로그인 후 이용 가능합니다." style="resize: none;"></textarea>
						<span class="input-group-btn">
							<button class="btn-u" type="button" style="height: 74px;" onclick="shopBoardReplyWrite()">등록</button>			
						</span>
					</div>	
					<div id="replyDiv">						
					</div>		
				</div>
			</div>				
		</div>
				
		<jsp:include page="footer.jsp" flush="false" />		
	</div>
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
    jQuery(document).ready(function() {
        App.init();
        replyListPaging(1);
        });  
</script>
</body>
</html>