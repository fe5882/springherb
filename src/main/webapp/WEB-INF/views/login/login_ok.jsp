<%@page import="com.herbmall.member.model.MemberVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//login.jsp에서 로그인 버튼 클릭시 post방식으로 submit 됨
	//=>파라미터인 아이디, 비밀번호 이용해서 로그인 처리
	//=>로그인 성공하면 세션에 아이디 저장, 쿠키에 아이디 저장
	//1.
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String saveId = request.getParameter("saveId");
		String pwd = request.getParameter("pwd");
		MemberVO vo = null;
	//2.
		String msg = "로그인 처리실패", url = "/login/login.jsp";
		MemberService memService = new MemberService();
		try{
			int result = memService.loginCheck(userid, pwd);
			vo = memService.selectMember(userid);	
			if(result == memService.LOGIN_OK){	//로그인 성공
				//[1] 세션에 저장
				session.setAttribute("userid", userid);
				session.setAttribute("userName", vo.getName());
				
				//[2] 쿠키에 저장
				if(saveId != null){	//체크한경우 - on
					Cookie ck = new Cookie("ck_userid", userid);
					ck.setMaxAge(1000*24*60*60);	//쿠키 유효기간 : 1000일
					ck.setPath("/");	//지정한 경로와 하위 경로에 쿠키 전송
					response.addCookie(ck);
				}else{	//체크하지 않은 경우 - null
					Cookie ck = new Cookie("ck_userid", userid);
					ck.setMaxAge(0);	//쿠키 유효기간 : 0 => 쿠키 제거됨
					ck.setPath("/");	//지정한 경로와 하위 경로에 쿠키 전송
					response.addCookie(ck);
				}
				
				
				msg = vo.getName() + "님 로그인 되었습니다.";
				url = "/index.jsp";
			}else if(result == memService.PWD_DISAGREE){
				msg = "비밀번호가 일치하지 않습니다.";				
			}else if(result == memService.ID_NONE){
				msg = "해당 아이디가 존재하지 않습니다.";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	//3.
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);
%>

<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>