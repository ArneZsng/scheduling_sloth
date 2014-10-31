<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.welcome"/></h1>
<div>
    <ul>
        <li><a href="${pageContext.servletContext.contextPath}/ShowLecturerList"><s:text name="txt.lecturers"/></a></li>
        <li><a href="${pageContext.servletContext.contextPath}/ShowRoomList"><s:text name="txt.rooms"/></a></li>
        <li><a href="${pageContext.servletContext.contextPath}/ShowCohortList"><s:text name="txt.cohorts"/></a></li>
        <li><a href="${pageContext.servletContext.contextPath}/ShowCenturyList"><s:text name="txt.centuries"/></a></li>
    </ul>
</div>