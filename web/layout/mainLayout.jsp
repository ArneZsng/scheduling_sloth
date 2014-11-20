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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/stylesheets/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/stylesheets/main.css">
    </head>
	<body>
        <s:if test="hasActionErrors() || hasFieldErrors()">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <s:iterator value="actionErrors">
                    <p class="errorMessage"><s:property escapeHtml="false" /></p>
                </s:iterator>
                <s:iterator value="fieldErrors">
                    <s:iterator value="value">
                        <p class="errorMessage"><s:property escapeHtml="false" /></p>
                    </s:iterator>
                </s:iterator>

            </div>
        </s:if>
        <s:if test="hasActionMessages()">
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <s:iterator value="actionMessages">
                    <p class="actionMessage"><s:property /></p>
                </s:iterator>
            </div>
        </s:if>
        <!-- Header -->
        <tiles:insertAttribute name="header"/>

		<!-- Content -->
        <div class="container">
		    <tiles:insertAttribute name="content"/>
        </div>

        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/moment-with-locales.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/javascripts/scheduling_sloth.js"></script>
    </body>
</html>
