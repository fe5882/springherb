<%@ page language="java" pageEncoding="UTF-8"%>
<%
String no = request.getParameter("no");
%>

<div>
		<h3>댓글</h3>
		<form name="frmComment" method="post" action="comment_ok.do" style = "width: 600px">
			<div style = "float: left">
				<label for="name">이름</label>
				<input type = "text" name = "name">
			</span>
			<div style = "float: right; margin-left: 100px">
				<label for = "pwd">비밀번호</label>
				<input type = "password" name = "pwd">
				<input type = "hidden" name = "no" value = "<%=no%>">
				
			</div>
			<div>
				<textarea rows="5" cols="80" style = "margin-top: 10px" name = "content"></textarea>
			</div>
			<div class="center">
				<input type = "submit" value = "확인" action="comment_ok.do">
			</div>
		</form>
	</div>
