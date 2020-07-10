<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../inc/top.jsp"%>
<style type = "text/css">
	#agree{
		width: 820px;
	}
	#agree form[name=frmAgree] .align_right{
		margin: 20px 0;
	}
</style>
<script type="text/javascript">
	$(function(){
			$('form[name = frmAgree]').submit(function(){
				if(!$('#chkAgree').is(':checked')){
					alert("약관에 동의하셔야 합니다!");
					$('#chkAgree').focus();
					event.preventDefault();
				}
			});
	});
</script>
	<article class = "agree">
		<h2>회원 약관</h2>
		<iframe src = "<c:url value='/inc2/provision.html'/>" width="820" height="300"></iframe>
		<form name="frmAgree" method="post" action="<c:url value='/member/register.do'/>">
			<div class = "align_right">
				<input type = "checkbox" name = "chkAgree" id = "chkAgree">
				<label for = "chkAgree">약관에 동의합니다.</label>
			</div>
			<div class = "align_center">
				<input type = "submit" value = "확인">
				<input type = "reset" value = "취소">
			</div>
		</form>
	</article>

<%@ include file = "../inc/bottom.jsp"%>
