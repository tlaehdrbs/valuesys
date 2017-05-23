function BoardWriteProc()
{
	if($("#boardTitle").val() == "" || $("#boardTitle").val() == null)
	{
		alert("제목을 입력해주세요.")
		return false;
	}
	document.BoardWriteForm.submit();
}