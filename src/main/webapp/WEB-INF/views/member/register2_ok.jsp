<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.member.model.MemberVO"%>
<%@page import="com.herbmall.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register2_ok.jsp</title>
</head>
<body>
<%
	//register2에서 post방식으로 submit됨
	request.setCharacterEncoding("utf-8");
	//1

%>
<jsp:useBean id="vo" class="com.herbmall.member.model.MemberVO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="vo"/>

<%
	//2	
	MemberDAO dao = new MemberDAO();
	
		try{
		int cnt = dao.insertMember(vo);
		
		if(cnt > 0){%>
			<script type="text/javascript">
				alert("회원가입 완료");
				location.href ="../index.jsp";
			</script>
		<%}else{%>
			<script type="text/javascript">
				alert("회원가입 실패");
				history.back();
			</script>
		<%}
	}catch(SQLException e){
		e.printStackTrace();
	}
%>
</body>
</html>






