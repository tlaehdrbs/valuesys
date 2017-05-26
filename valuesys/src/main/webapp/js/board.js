function BoardWriteProc()
{
	if($("#boardTitle").val() == "" || $("#boardTitle").val() == null)
	{
		alert("제목을 입력해주세요.")
		return false;
	}
	document.BoardWriteForm.submit();
}

//글수정하기
function boardUpdateProc()
{
	if(document.BoardUpdateForm.boardTitle.value=="" || document.BoardUpdateForm.boardTitle.value==null)
	{
		alert("제목을 입력하세요");
		document.BoardUpdateForm.boardTitle.focus();
		return false;
	}
	 
	alert("글이 성공적으로 수정되었습니다!");
	//document.BoardUpdateForm.submit();
	$("#BoardUpdateForm").submit();
}

//글 삭제하기
function boardDelete(boardNumber)
{
	var del = confirm("이 글을 정말 삭제할까요?");
	if(del)
	{
		$.get("../view/boardDelete.do", {boardNumber:boardNumber}, function(){
			alert("글이 삭제되었습니다.")
			location.href = 'board.do';
		})
	}
	else
	{
		alert("글 삭제가 취소되었습니다.")
		return false;
	}
}

//좋아요 버튼 구현
function boardLike(boardNumber)
{
	alert("좋아요를 하였습니다!")
	$.post("../view/boardLike.do", {boardNumber:boardNumber}, function(){
		location.href = 'boardDetail.do?boardNumber='+boardNumber;
	})
}




//댓글 페이징 처리 부분
function replyListPaging(pageNum)
{
	var boardNumber = $("#boardNumber").val()
	
	$.get("boardReply.do", {pageNum : pageNum, boardNumber : boardNumber}, function(result)
	{			
		$("#replyDiv").html(result);
	})
}

//댓글 수정
function updateReply(replyNumber, replyContent)
{
	var contentDiv = $("#contentDiv" + replyNumber);
	var functionUl = $("#functionUl" + replyNumber);
	
	contentDiv.html('<div class="input-group" style="margin-bottom: 20px;"><textarea class="form-control" rows="3" style="resize: none;" id="updateReply' +replyNumber + '">' 
			+ replyContent + '</textarea><span class="input-group-btn"><button class="btn-u" type="button" style="height: 74px;" onclick="boardReplyUpdate(' + replyNumber + ')">수정</button>' 
			+ '</span></div>');
	functionUl.html('<li><a href="javascript:updateCancelReply(' + "'" + replyNumber + "', '" + replyContent + "'" 
			+ ')"><i class="expand-list rounded-x fa fa-pencil"></i> 취소</a></li>');
}

//댓글 수정 진행
function boardReplyUpdate(replyNumber)
{
	var replyContent = $("#updateReply"+replyNumber).val()
	var boardNumber = $("#boardNumber").val()
	$.get("../view/updateReply.do", {replyNumber:replyNumber, replyContent:replyContent, boardNumber:boardNumber}, function(result)
	{
		alert("댓글 수정이 완료되었습니다")
		$("#replyDiv").html(result);
	})
}

//댓글 수정 취소
function updateCancelReply(replyNumber, replyContent)
{
	var contentDiv = $("#contentDiv" + replyNumber);
	var functionUl = $("#functionUl" + replyNumber);
	
	contentDiv.html('<p>' + replyContent + '</p>');
	functionUl.html('<li><a href="javascript:updateReply(' + "'" + replyNumber + "', '" + replyContent + "'" 
			+ ')"><i class="expand-list rounded-x fa fa-pencil"></i> 수정</a></li><li><a href="javascript:deleteReply(' 
			+"'" + replyNumber + "'" + ')"><i class="expand-list rounded-x fa fa-times"></i> 삭제</a></li>');
}

//댓글 삭제
 function deleteReply(replyNumber)
 {
	 var deleteReply = confirm("댓글을 삭제하시겠습니까?")
	 
	 if(deleteReply)
	{
		 var boardNumber = $("#boardNumber").val()
		 $.get("../view/deleteReply.do", {replyNumber:replyNumber, boardNumber:boardNumber}, function(result)
		{
			 $("#replyDiv").html(result);
		 })
	}
	else
	{
		return false;
	}
 }


 
 
 //조회수 0인 글 알림
 function alarm()
 {
	 $.get("../view/boardAlarm.do", function(result)
	{
		 $("#alarm").append(result);
	 })
 }
 
 
 
 
 
 






