<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%-- The application's main template --%>
<html>
	<head>
		<title><tiles:insertAttribute name="title"/></title>
		<s:head/>
	</head>
	<body>
		<!-- Header -->
		<tiles:insertAttribute name="header"/>
		<hr/>
		<!-- Content -->
		<tiles:insertAttribute name="content"/>
	</body>
</html>
