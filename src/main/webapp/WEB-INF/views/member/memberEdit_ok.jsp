<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.member.model.MemberService"%>
<%@page import="com.herbmall.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<%
	// memberEdit에서 정보 수정 클릭시 post방식으로 submit됨
	// 수정된내용을 db에 update해야함
	//1.
	request.setCharacterEncoding("utf-8");
	
	//session에 있는 userid를 읽어와서 db작업 준비
	
	String m_userid = (String)session.getAttribute("userid");
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
	
	//휴대폰과 email은 null체크 후 합쳐서 db에 등록한다
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
	
	MemberService memService = new MemberService();
	MemberVO vo = new MemberVO();
	
	vo.setZipcode(zipcode);
	vo.setAddress(address);
	vo.setAddressDetail(addressDetail);
	vo.setHp(hp);
	vo.setEmail(email);
	vo.setUserid(m_userid);
	
	
	
	//2. db작업
	int cnt = 0;
	try{
		cnt = memService.editMember(vo);
		int result = memService.loginCheck(m_userid, pwd);
		if(result != memService.LOGIN_OK){	%>
			<script type="text/javascript">
				alert('비밀번호를 확인하세요');
				location.href = "memberEdit.jsp";
				event.preventDefault();
			</script><%		
		}else{
			if(cnt > 0){%>
			<script type="text/javascript">
				alert('회원수정 성공');
				location.href="memberEdit.jsp";
			</script>
			<%}else{%>
			<script type="text/javascript">
				alert('회원수정 실패');
				history.back();
			</script>
			<%}
					
		}%>
		
	<%}catch(SQLException e){
		e.printStackTrace();
	}
	//3.
	
%>



</body>
</html>