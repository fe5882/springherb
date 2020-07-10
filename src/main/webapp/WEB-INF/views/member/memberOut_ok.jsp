<%@page import="com.herbmall.member.model.MemberService"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = '../login/loginCheck.jsp' %>
<jsp:useBean id="memService" class="com.herbmall.member.model.MemberService" scope="session" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//memberOut에서 탈퇴버튼 눌렀을때 memberOut.jsp에서 post로 submit됨
	//비밀번호 확인 작업 후 outdate 컬럼에 sysdate로 업데이트 시킴
	//session invalidate시킴
	//쿠키삭제
	request.setCharacterEncoding("utf-8");

	String d_userid = (String)session.getAttribute("userid");
	String pwd = request.getParameter("pwd");
	
	try{
		int result = memService.loginCheck(d_userid, pwd);
		if(result != memService.LOGIN_OK){%>
			<script type="text/javascript">
				alert('비밀번호를 확인하세요');
				location.href = "memberOut.jsp";
				event.preventDefault();
			</script><%		
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	//2.db작업
	String url = "/member/memberOut.jsp", msg = "회원 탈퇴 실패";
	try{
		int cnt = memService.updateOutdate(d_userid);
			
		if(cnt > 0){
			session.invalidate();
			Cookie [] ckArr = request.getCookies();
			for(Cookie c : ckArr){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
			}
			request.setAttribute("msg", "탈퇴되었습니다");
			request.setAttribute("url", "/index.jsp");
		
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	
%>
<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>