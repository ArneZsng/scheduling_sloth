<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lessons"/></h1>
<s:actionerror/>
<%-- The century table --%>
<table class="table">
    <thead>
    <tr>
        <th></th>
        <th><s:text name="lbl.name"/></th>
        <th><s:text name="lbl.lecturer"/></th>
        <th><s:text name="lbl.century"/></th>
        <th><s:text name="lbl.cohort"/></th>
        <th><s:text name="lbl.startDate"/></th>
        <th><s:text name="lbl.endDate"/></th>
        <th><s:text name="lbl.rooms"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="courseList">
        <s:url action="EditCourse" var="edit" >
            <s:param name="courseId"><s:property value="id"/></s:param>
        </s:url>
        <s:set var="numberOfLessons" value="lessons.size()" />
        <s:set var="firstEntry" value="true" />
        <s:iterator value="lessons">
            <tr>
                <s:if test="firstEntry">
                    <td rowspan="<s:property value="#numberOfLessons"/>"><a type="button" class="btn btn-primary btn-sm" href="<s:property value="#edit" />">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </a></td>
                    <td rowspan="<s:property value="#numberOfLessons"/>"><s:property value="name"/></td>
                    <td rowspan="<s:property value="#numberOfLessons"/>"><s:property value="lecturer.name"/></td>
                    <td rowspan="<s:property value="#numberOfLessons"/>"><s:property value="century.name"/></td>
                    <td rowspan="<s:property value="#numberOfLessons"/>"><s:property value="cohort.name"/></td>
                    <s:set var="firstEntry" value="false" />
                </s:if>

                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:property value="rooms"/></td>
                <td></td>
                <s:url action="DeleteCourseLesson" var="delete" >
                    <s:param name="courseLessonId"><s:property value="id"/></s:param>
                </s:url>
                <td class="rightCell">
                    <a type="button" class="btn btn-danger btn-sm" href="<s:property value="#delete" />">
                        <span class="glyphicon glyphicon-remove"></span>
                    </a>
                </td>
            </tr>
        </s:iterator>
    </s:iterator>
    </tbody>
</table>
<s:url action="AddCourse" var="add" />
<a type="button" class="btn btn-primary btn-large" href="<s:property value="#add" />">
    <span class="glyphicon glyphicon-plus"></span> <s:text name="btn.add"/>
</a>