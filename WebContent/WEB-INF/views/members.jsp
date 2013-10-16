<%@page import="org.apache.poi.hssf.record.formula.MemErrPtg"%>
<%@page import="org.scoovy.positionmanager.model.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	@SuppressWarnings("unchecked")
 	List<Member> members = (List<Member>)request.getAttribute("members");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<%for(Member member : members){ %>
		<li><%=member.getEducationNumber() %></li>
	<%} %>
	</ul>
</body>
</html>