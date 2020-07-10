<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../inc/top.jsp"%>
<%@ include file = "../login/loginCheck.jsp" %>
<%
	String d_userid = (String)session.getAttribute("userid");
	String pwd = (String)session.getAttribute("pwd");
	
	
	
	
%>
<script type="text/javascript">
	$(function(){
		$('#pwd').focus();
		
		$('input[type=submit]').click(function(){
			if($('#pwd').val().length < 1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else{
				if(!confirm("정말 탈퇴하시겠습니까?")){
				event.preventDefault();
				}
			}
		});
	});
</script>

<article class = "simpleForm">
<h2>회원탈퇴</h2>
<p class = "p">회원탈퇴하시겠습니까?</p>
<form name = "frmOut" method = "post" action = "memberOut_ok.jsp">
	<div>
		<label for = "pwd">비밀번호</label>
		<input type = "password" name="pwd" id = "pwd">
	</div>
	<div class = "align_center">
		<input type = "submit" value = "회원탈퇴">
		<input type = "reset" value = "취소">
		
	</div>

</form>
</article>
<%@ include file = "../inc/bottom.jsp"%>