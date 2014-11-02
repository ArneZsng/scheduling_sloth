<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%-- The application's main template --%>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title"/></title>
		<s:head/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/stylesheets/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/stylesheets/main.css">
    </head>
	<body>
		<!-- Header -->
        <tiles:insertAttribute name="header"/>

		<!-- Content -->
        <div class="container">
		    <tiles:insertAttribute name="content"/>
        </div>

        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/bootstrap.min.js"></script>
	</body>
</html>
