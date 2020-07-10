<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.member.model.MemberVO"%>
<%@page import="com.herbmall.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%@ include file="../login/loginCheck.jsp" %>

<jsp:useBean id="memService" class = "com.herbmall.member.model.MemberService" scope = "session"></jsp:useBean>
<%
	//index.jsp에서 회원정보 수정 눌렀을때 get방식으로 넘어옴
	String userid = (String)session.getAttribute("userid");
	
		  
	

	MemberVO vo = null;
	try{
		vo = memService.selectMember(userid);
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	

	String zipcode = vo.getZipcode();
	String address = vo.getAddress();
	String addressdetail = vo.getAddressDetail();
	String hp = vo.getHp();
	String hp1 = "", hp2 = "", hp3 = "";
	String email1 = "", email2 = "";
	String hArr[] = new String[3];
	
	String email = vo.getEmail();
	if(zipcode == null || zipcode.isEmpty()){
		zipcode = "";
	}
	
	if(address == null || address.isEmpty()){
		address = "";
	}
	
	if(addressdetail == null || addressdetail.isEmpty()){
		addressdetail = "";
	}
	if(hp != null && !hp.isEmpty()){
		hArr = hp.split("-");
		hp1 = hArr[0];
		hp2 = hArr[1];
		hp3 = hArr[2];
		
			
		/* hp1 = hp.substring(0, hp.indexOf('-'));
		hp2 = hp.substring(hp.indexOf("-") + 1, hp.lastIndexOf("-"));
		hp3 = hp.substring(hp.lastIndexOf("-") + 1); */
	}
	String[] emailList = {"naver.com", "hanmail.net", "nate.com", "gmail.com"};
	boolean isEtc = false;	//직접입력인 경우 true
	String [] eArr = new String[2];
	if(email != null && !email.isEmpty()){
		/* email1 = email.substring(0, email.indexOf("@"));
		email2 = email.substring(email.indexOf("@") + 1); */
		eArr = email.split("@");	
		email1 = eArr[0]; 
		email2 = eArr[1]; 
		int count = 0;
		
		for(int i = 0; i < emailList.length; i++){
			if(email2.equals(emailList[i])){
				count++;
				break;
			}
		
		}
		
		if(count == 0){
			isEtc = true;
		}
		
	}
	
	
%>
<script type="text/javascript" src = "../js/member.js"></script>
<script type="text/javascript">
	$(function(){
		
		$('#wr_submit').click(function(){
			
			if($('#pwd').val().length < 1){
				alert('비밀번호를 입력하세요')
				$('#pwd').focus();
				return false;
			}
			
			if($('#pwd').val() != $('#pwd2').val()){
				alert('비밀번호가 일치하지 않습니다.');
				$('#pwd2').focus();
				return false;
			}
			
			if(!validate_phone($('#hp2').val()) || !validate_phone($('#hp3').val())){
				alert('전화번호는 숫자만 입력 가능합니다.');
				$('#hp2').focus();
				event.preventDefault();
			}
			
			
		});
		
		
		
	});
	
	
	
</script>

<style type="text/css">
	.width_80{
		width:80px;
	}
	.width_350{
		width:350px;
	}	
</style>
<article>
<div class="divForm">
<form name="frm1" method="post" action="memberEdit_ok.jsp">
<fieldset>
	<legend>회원 정보 수정</legend>
    <div>        
        <label for="name">성명</label>
        <span><%=vo.getName() %></span>
    </div>
    <div>
        <label for="userid">회원ID</label>
        <span><%=userid %></span>
    </div>
    <div>
        <label for="pwd">비밀번호</label>
        <input type="Password" name="pwd" id="pwd" value = "<%=vo.getPwd()%>">
    </div>
    <div>
        <label for="pwd2">비밀번호 확인</label>
        <input type="Password" name="pwd2" id="pwd2" value = "<%=vo.getPwd()%>">
    </div>
    <div>
        <label for="zipcode">주소</label>
        <input type="text" name="zipcode" id="zipcode" ReadOnly  
        	title="우편번호" class="width_80" value = "<%=zipcode%>">
        <input type="Button" value="우편번호 찾기" id="btnZipcode" title="새창열림"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="address" ReadOnly title="주소"  class="width_350" value = "<%=address%>"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="addressDetail" title="상세주소"  class="width_350" value = "<%=addressdetail%>">
    </div>
    <div>
        <label for="hp1">핸드폰</label>&nbsp;<select name="hp1" id="hp1" title="휴대폰 앞자리" >
        	
            <option value="010" <%if(hp1.equals("010")){%> selected <% } %>>010</option>
            <option value="011" <%if(hp1.equals("011")){%> selected <% } %>>011</option>
            <option value="016" <%if(hp1.equals("016")){%> selected <% } %>>016</option>
            <option value="017" <%if(hp1.equals("017")){%> selected <% } %>>017</option>
            <option value="018" <%if(hp1.equals("018")){%> selected <% } %>>018</option>
            <option value="019" <%if(hp1.equals("019")){%> selected <% } %>>019</option>
       	</select>
        -
        <input type="text" name="hp2" id="hp2" maxlength="4" title="휴대폰 가운데자리" value = "<%=hp2 %>"
        	class="width_80">-
        <input type="text" name="hp3" id="hp3" maxlength="4" title="휴대폰 뒷자리" value = "<%=hp3%>"
        	class="width_80">
    </div>
    <div>
        <label for="email1">이메일 주소</label>
        <input type="text" name="email1"  id="email1" title="이메일주소 앞자리" value = "<%=email1%>">@
        <select name="email2" id="email2"  title="이메일주소 뒷자리">
           <%for(int i = 0; i <emailList.length; i++){%>
            		<option value = "<%=emailList[i] %>" <%if(email2.equals(emailList[i])){%> selected <%}%>> <%=emailList[i] %>
            	
            	<%}%> 
            </option>
            <%-- <option value="naver.com" 
            	<%if(email2.equals("naver.com")){ %>
            		selected="selected"
            	<%} %>
            >naver.com</option>
            <option value="hanmail.net"
            	<%if(email2.equals("hanmail.net")){ %>
            		selected="selected"
            	<%} %>
            >hanmail.net</option>
            <option value="nate.com" 
            	<%if(email2.equals("nate.com")){ %>
            		selected="selected"
            	<%} %>
            >nate.com</option>
            <option value="gmail.com"
            	<%if(email2.equals("gmail.com")){ %>
            		selected="selected"
            	<%} %>
            >gmail.com</option> --%>
            
            
            
            <option value="etc" <%if(isEtc){%> selected = "seleceted"<%} %>>직접입력</option>
        </select>
        <input type="text" name="email3" id="email3" title="직접입력인 경우 이메일주소 뒷자리"
        	style="visibility: <%if(isEtc){%>visible" value = "<%=email2 %>"<%}else if(!isEtc){ %> hidden "<%} %> >
    </div>
    <div class="center">
         <input type="submit" id="wr_submit" value="회원수정">
    </div>
</fieldset>

   
        
</form>
</div>
</article>

<%@ include file="../inc/bottom.jsp"%>











