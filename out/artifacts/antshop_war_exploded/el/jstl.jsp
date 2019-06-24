<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.it666.domain.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("count", 100);
	%>
	<!-- 条件满足显示 -->
	<c:if test="${1==1 }">myxq</c:if>
	<c:if test="${count>50 }">
		<h1>大于:50</h1>
	</c:if>
	<c:if test="${count<=50 }">
		<h1>小于等于:50</h1>
	</c:if>

	<hr>
	<c:forEach begin="0" end="5" var="i">
		<!-- 存储到page域中 -->
		${i }<br>
		${pageScope.i }<br>
	</c:forEach>
	<hr>
	<%
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		request.setAttribute("strList", list);
	%>
	<c:forEach items="${strList }" var="str">
		${str }<br>
	</c:forEach>
	<hr>
	<%
		List<User> userList = new ArrayList<User>();
		User u1 = new User();
		u1.setUsername("m1");
		User u2 = new User();
		u2.setUsername("m2");
		User u3 = new User();
		u3.setUsername("m3");
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		request.setAttribute("userList", userList);
	%>
	<c:forEach items="${userList }" var="user">
		${user.username }<br>
	</c:forEach>
	<%
		Map<String, String> strMap = new HashMap<String, String>();
		strMap.put("1", "a1");
		strMap.put("2", "a2");
		strMap.put("3", "a3");
		strMap.put("4", "a4");
		session.setAttribute("strMap", strMap);
	%>
	<c:forEach items="${strMap }" var="entry">
		${entry.key }:${entry.value }<br>
	</c:forEach>


</body>
</html>