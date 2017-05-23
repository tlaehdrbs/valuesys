// Null 체크
function checkNull()
{
	if($('#usersName').val()=="" || $('#usersName').val()==null ||$('#usersName').val().indexOf(' ') > -1)
	{
		alert("올바른 이름을 입력하세요");
		$('#usersName').focus();
		return false;
	}
	if($('#usersId').val()=="" || $('#usersId').val()==null)
	{
		alert("아이디를 입력하세요");
		$('#usersId').focus();
		return false;
	}
	if($('#usersPassword').val()=="" || $('#usersPassword').val()==null)
	{
		alert("암호를 입력하세요");
		$('#usersPassword').focus();
		return false;
	}
	if($('#usersPassword1').val()=="" || $('#usersPassword1').val()==null)
	{
		alert("암호확인을 입력하세요");
		$('#usersPassword1').focus();
		return false;
	}
	if($('#usersPhone').val()=="" || $('#usersPhone').val()==null || $('#usersPhone').val().indexOf(' ') > -1)
	{
		alert("올바른 연락처를 입력하세요");
		$('#usersPhone').focus();
		return false;
	}
	if($('#usersEmail').val()=="" || $('#usersEmail').val()==null)
	{
		alert("이메일을 입력하세요");
		$('#usersEmail').focus();	
		return false;
	}
	else
		checkPhone();
}

// 연락처 유효성 검사
function checkPhone(){
	var exptext= /^[0-9]{9,12}$/;
	if(exptext.test($('#usersPhone').val())==false ||  $('#usersPhone').val().indexOf(' ') > -1){
		alert("올바른 연락처 형식이 아닙니다.");
		$('#usersPhone').focus();
		return false;
	}
	if (/(\w)\1\1\1\1/.test($('#usersPhone').val())) {
		alert('연락처에 같은 문자를 5번 이상 반복하실 수 없습니다.');
		$('#usersPhone').focus();
		return false;
	}
	else{
		checkEmail();
	}	
}

// 이메일 유효성 검사
function checkEmail(){
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(exptext.test($('#usersEmail').val())==false ||  $('#usersEmail').val().indexOf(' ') > -1){
		alert("올바른 이메일 형식이 아닙니다.");
		$('#usersEmail').focus();
		return false;
	}else{
		checkPwd();
	}

}

// 아이디 중복 및 유효성 검사
function checkId()
{	
	var exptext= /^[a-z0-9]{4,12}$/;
	var chk_num = $('#usersId').val().search(/[0-9]/g);
	var chk_eng = $('#usersId').val().search(/[a-z]/ig);
	
	// 아이디 글자수 제한
	if(exptext.test($('#usersId').val())==false || $('#usersId').val().indexOf(' ')>-1){
		$('#idCheck').html("아이디는 영문+숫자 4~12자리");
		$('#idCheck').attr("style","color:red");
		return false;
	}
	// 아이디 영문자+숫자 조합
	if (chk_num < 0 || chk_eng < 0) {
		$('#idCheck').html("아이디는 영문+숫자 4~12자리");
		$('#idCheck').attr("style","color:red");
		return false;
	}
	// 아이디에 같은 문자 반복 제한
	if (/(\w)\1\1\1/.test($('#usersId'))) {
		$('#idCheck').html('아이디에 같은 문자를 4번 이상 반복하실 수 없습니다.');
		$('#idCheck').attr("style","color:red");
		return false;
	}
	$.ajax({	 
		url : 'checkId.do?userId=' + $('#userId').val(),
		type : 'post',
		success : function(data) {
			$('#idCheck').html(data);
			if($('#checkIdFromUsers').val()==1 || $('#checkIdFromMgr').val()==1 || $('#checkIdFromList').val()==1){
				$('#idCheck').attr("style","color:red");
				return false;
			}				
			if($('#checkIdFromUsers').val()==0 || $('#checkIdFromMgr').val()==0 || $('#checkIdFromList').val()==0){
				$('#idCheck').attr("style","color:blue");
				return false;
			}
				
	       	}
	});	
	//alert($('#result').val());
}

// 비밀번호 유효성 검사 및 폼 전송	
function checkPwd()
{
	var pwd=$('#usersPassword').val();
	var pwd1=$('#usersPassword1').val();
	var chk_num = pwd.search(/[0-9]/g);
	var chk_eng = pwd.search(/[a-z]/ig);
	var exptext=/^[a-zA-Z0-9]{8,12}$/;

	// 비밀번호 형식 및 공백문자
	if (!exptext.test(pwd) || pwd.indexOf(' ') > -1)
	{
		alert('패스워드는 영문+숫자 8~12자리');
		$('#usersPassword').val("");
		$('#usersPassword1').val("");
		$('#usersPassword').focus();
		return false;
	}

	if (chk_num < 0 || chk_eng < 0) {
		alert('패스워드는 영문+숫자 8~12자리');
		$('#usersPassword').val("");
		$('#usersPassword1').val("");
		$('#usersPassword').focus();
		return false;
	}

	if (/(\w)\1\1\1/.test(pwd)) {
		alert('패스워드에 같은 문자를 4번 이상 반복하실 수 없습니다.');
		$('#usersPassword').val("");
		$('#usersPassword1').val("");
		$('#usersPassword').focus();
		return false;
	}
	
	if(pwd==pwd1){
		alert("회원가입 성공!");
		document.registrationForm.submit();
		
	}
		
	else
	{
		alert("패스워드가 일치하지 않습니다.");
		$('#usersPassword1').val("");
		$('#usersPassword1').focus();
		return false;
	}
}

