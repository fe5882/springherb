<%@page import="com.mymvc.pd.model.PdDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	List<PdDTO> pdList = (List<PdDTO>) request.getAttribute("pdList");

	DecimalFormat df = new DecimalFormat("#,###");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<h1>상품 목록</h1>
<table border="1" style="width:500px">
	<tr>
		<th>번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>등록일</th>
	</tr>
	<!-- 반복 시작 -->
	<%
		if(pdList != null && !pdList.isEmpty()){
		for(int i=0;i<pdList.size();i++){
			PdDTO dto=pdList.get(i);%>
			<tr>
				<td><%=dto.getNo() %></td>
				<td><a href="<%=request.getContextPath() %>/pd/pdDetail.do?no=<%=dto.getNo() %>">
					<%=dto.getPdName() %></a></td>
				<td style="text-align: right">
					<%=df.format(dto.getPrice()) %>원</td>
				<td><%=sdf.format(dto.getRegdate()) %></td>
			</tr>
	<%	}//for
	}
	%>
	<!-- 반복 끝 -->
</table>
<hr>
<a href="<%=request.getContextPath() %>/pd/pdWrite.do">상품 등록</a>


