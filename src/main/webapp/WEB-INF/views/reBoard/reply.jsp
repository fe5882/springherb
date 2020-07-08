<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mystyle.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>

<title>자료실 답변하기 - 허브몰</title>
<script type="text/javascript">
	$(function(){
		$('.btList').click(function(){
			location.href 
				= "<c:url value='/reBoard/list.do'/>";	
		});
		
		$('form[name=frmWrite]').submit(function(){
			if($('#title').val()==''){
				alert('제목을 입력하세요');
				$('#title').focus();
				event.preventDefault();
			}else if($('#name').val().length<1){
				alert('이름을 입력하세요');
				$('#name').focus();
				event.preventDefault();
			}else if(!$('#pwd').val()){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}
			
		});
	});
	
</script>

</head>
<body>
<div class="divForm">
<form name="frmWrite" method="post" 
	action="<c:url value='/reBoard/reply.do'/>">
 <fieldset>
	<legend>답변하기</legend>
		<!-- 답변하기에 필요한 필드  -->
		<input type = "text" name = "groupNo" value = "${vo.groupNo }">
		<input type = "text" name = "step" value = "${vo.step }">
		<input type = "text" name = "sortNo" value = "${vo.sortNo }">
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" value = "Re: ${vo.title }" readonly="readonly" />
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" />
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" />
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" />
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div>
        <div class="center">
            <input type = "submit" value="답변"/>
            <input type = "Button" class="btList" value="글목록"  />         
        </div>
    </fieldset>
</form>
</div>   


              
</body>
</html>








