<%@ page language="java" pageEncoding="UTF-8"%>

<%
	List<CommentVO> cList = (List<CommentVO>)request.getAttribute("cList");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	%>


<%
		if(cList != null && !cList.isEmpty()){%>
			<table  border = "1" style = "width: 500px">
			<colgroup><col style = "width: 20%"><col style = "width: 50%"><col style = "width: 30%"></colgroup>
				<%for(int i = 0; i < cList.size(); i++){
					CommentVO cVo = cList.get(i);
					%>
					<tr>
						<td><%=cVo.getName() %></td>
						<td><%=cVo.getContent() %></td>
						<td><%=sdf.format(cVo.getRegdate()) %></td>
					</tr>
				<%}%>
			</table>
		<%}
	%>
