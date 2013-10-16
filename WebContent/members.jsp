<%@page import="org.apache.poi.hssf.record.formula.MemErrPtg"%>
<%@page import="org.scoovy.positionmanager.model.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	@SuppressWarnings("unchecked")
 	List<Member> members = (List<Member>)request.getAttribute("members");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member List</title>
</head>
<body>
	<ul>
	<%for(Member member : members){ %>
		<li>
			<a href="<%request.getContextPath();%>/member?memberId=<%=member.getId() %>"><%=member.getEducationNumber() %></a>
		</li>
	<%} %>
	</ul>
</body>
</html>