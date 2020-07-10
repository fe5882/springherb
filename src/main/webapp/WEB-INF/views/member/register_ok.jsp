<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="memVo" class="com.herbmall.member.model.MemberVO" scope="page"></jsp:useBean>
<jsp:useBean id="memService" class="com.herbmall.member.model.MemberService" scope="session"></jsp:useBean>
<%
	//register에서 post방식으로 submit됨
	
	//1.
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email3 = request.getParameter("email3");
		
		String hp = "", email = "";
		
		if(hp2 != null && !hp2.isEmpty() && hp3 != null && !hp3.isEmpty()){
			hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		
		if(email1 != null && !email1.isEmpty()){
			if(email2.equals("etc")){
				if(email3 != null && !email3.isEmpty()){
					email = email1 + "@" + email3;
				}
			}else{
					email = email1 + "@" + email2;
				}
				
			
		}
		
		
		memVo.setAddress(address);
		memVo.setAddressDetail(addressDetail);
		memVo.setEmail(email);
		memVo.setHp(hp);
		memVo.setName(name);
		memVo.setPwd(pwd);
		memVo.setUserid(userid);
		memVo.setZipcode(zipcode);
		
		
		
		
		//2.
		String msg = "회원가입 실패!", url = "/member/register.jsp";
		
		try{
			int cnt = memService.insertMember(memVo);
			if(cnt>0){
				msg = "회원가입 되었습니다.";
				url = "/index.jsp";
			}
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		//3. 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
%>

<jsp:forward page="../common/message.jsp"></jsp:forward>
<!-- forward로 이동하면 request 공유함
현재 페이지와 message.jsp는 같은 request를 갖는다. -->
</body>
</html>





