<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginCheck.jsp</title>
</head>
<body>
<%
	String l_userid = (String)session.getAttribute("userid");
	
	if(l_userid == null || l_userid.isEmpty()){%>
	<script>
	   alert('로그인이 필요합니다.');
	   location.href = "<%=request.getContextPath()%>/login/login.jsp"
	   	
	</script>
	
	<%	return;
	}
%>
</body>
</html>