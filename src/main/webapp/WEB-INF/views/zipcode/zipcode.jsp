<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	//[1] register.jsp에서 우편번호 찾기 눌렀을때 get방식으로 이동
	//[2] zipcode.jsp에서 찾기 버튼을 눌렀을때 post방식으로 submit
	
	//1
	request.setCharacterEncoding("utf-8");
	String dong = request.getParameter("dong");
	String curPage = request.getParameter("currentPage");
	int currentPage = Integer.parseInt(curPage);
	
	
	//3 페이징 처리
	int pageSize = 10;
	int blockSize = 5;
	int totalRecord = list.size();
	
	PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>zipcode/zipcode.jsp</title>
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/css/mainstyle.css'/>"/>
<script type="text/javascript" src = "<c:url value = '/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$('#dong').focus();
		
		$('form[name = frmZip]').submit(function(){
			if($('#dong').val().length < 1){
				alert('지역명을 입력하세요.');
				$('#dong').focus();
				return false;
			}
		});
		
		
		
	});
	
	function setZipcode(zipcode, address){
		$(opener.document).find("#zipcode").val(zipcode);
		$(opener.document).find("input[name=address]").val(address);
		
		self.close();
	}
	
</script>
<style type="text/css">
	.box2{
		width: 490px;
	}
	
	#divZip{
		margin: 10px 0;
	}
	
	label{
		font-size: 0.9em;
	}
	.divPage{
		text-align: center;
	}
</style>
</head>

<body>
	<h1>우편번호 검색</h1>
	<p>찾고 싶으신 주소의 동(읍, 면)을 입력하세요</p>
	<form name = "frmZip" method="post" action="<c:url value='/zipcode/zipcode.do'/>">
		<label for = "area">지역명</label>
		<input type = "text" name = "dong" id = "dong" value = "${param.dong }">
		<input type = "submit" value = "찾기">
	</form>
	<div id = "divZip" class = "align_center">
		<table class = "box2" 
			summary = "우편번호 검색 결과에 관한 표로써, 우편번호, 주소에 대한 정보를 제공합니다.">
			<colgroup>
				<col style = "width: 20%">
				<col style = "width: *">
			</colgroup>
			<thead>
				<tr>
					<th scope = "col">우편번호</th>
					<th scope="col">주소</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${!empty list }">
			<c:forEach var="vo" items="${list}">
			<c:set var="address" value="${vo.sido } ${vo.gugun} ${vo.dong}"></c:set>
			<c:set var="bunji" value="${vo.startbunji }"></c:set>
			<c:if test="${!empty vo.endbunji }">
			<c:set var="bunji" value="${bunji } ~ ${vo.endbunji }"></c:set>
			</c:if>
			<tr>
					<td>${vo.zipcode }</td>
						<td><a href="#" onclick="setZipcode('${vo.zipcode }','${address }')">
									${address } ${bunji }
								</a>
						</td>		
					</tr> 
				
			</c:forEach>
			</c:if>			
				<!-- 반복시작 -->
				<%-- <%if(list == null || list.isEmpty()){	%>
			<tr>
				<td colspan = "2"></td>
					
			</tr>
				<%}else{
  					int num = pageVo.getNum();
  					int curPos = pageVo.getCurPos();
  					
	  				for(int i = 0; i < pageVo.getPageSize(); i++){
	  					if(num-- < 1) break;
	  				vo = list.get(curPos++);
	  				String endbunji = vo.getEndbunji();
	  				if(endbunji == null || endbunji.isEmpty()){
	  					endbunji = "";
	  				}else{
	  					endbunji = " ~ " + endbunji;
	  				}
	  				String address = vo.getSido() + " " + vo.getGugun() + " " + vo.getDong();
	  			%>
	  			
				<tr>
					<td><%=vo.getZipcode()%></td>
						<td><a href="#" onclick="setZipcode('<%=vo.getZipcode()%>','<%=address%>')">
									<%=address %> <%=vo.getStartbunji() + endbunji %>
								</a>
						</td>		
					</tr> 
					<%} %>
				<%} %>
				<!-- 반복끝 --> --%>
			</tbody>
		</table>
		<%-- <%} %>
		<div class="divPage">
			<%if(pageVo.getFirstPage()>1){ %>
		<a href="zipcode.jsp?currentPage=<%=pageVo.getFirstPage() - 1%>&dong=<%=dong%>">
			<img src="../images/first.JPG" alt="이전 블럭으로 이동">
		</a>
		<%} %>
		</div>
		<%for(int i=pageVo.getFirstPage();i<=pageVo.getLastPage();i++){ 
		if(i > pageVo.getTotalPage()) break;
		%>
		<%if(i!=pageVo.getCurrentPage()){%>
			<a href="zipcode.jsp?currentPage=<%=i%>&dong=<%=dong%>">[<%=i %>]</a>
		<%}else{ %>
			<span style="color:blue;font-weight:bold"><%=i %></span>
		<%}//if %>					
		<%}//for %>	
	<!-- 다음 블럭으로 이동 ▶ -->
	<%if(pageVo.getLastPage() < pageVo.getTotalPage()){ %>
		<a href="zipcode.jsp?currentPage=<%=pageVo.getLastPage() + 1%>&dong=<%=dong%>">
			<img src="../images/last.JPG" alt="다음 블럭으로 이동">
		</a>
	<%} %>--%>
	</div> 
</body>
</html>