<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<a href="<s:url action="Home"/>"><span aria-hidden="true">&larr;</span> <s:text name="txt.backTo"/> <s:text name="txt.schedule"/></a></li>

<h1>
    <s:property value="course.name"/>
    <div class="pull-right">
        <%-- The edit button --%>
        <s:url action="EditCourse" var="edit" >
            <s:param name="courseId"><s:property value="course.id"/></s:param>
        </s:url>
        <a type="button" class="btn btn-primary btn-large" href="<s:property value="#edit" />">
            <span class="glyphicon glyphicon-pencil"></span> <s:text name="btn.edit"/>
        </a>
        <%-- The delete button --%>
        <s:url action="DeleteCourse" var="delete" >
            <s:param name="courseId"><s:property value="course.id"/></s:param>
        </s:url>
        <a type="button" class="btn btn-danger btn-large" href="<s:property value="#delete" />">
            <span class="glyphicon glyphicon-remove"></span> <s:text name="btn.delete"/>
        </a>
    </div>
</h1>
<dl class="dl-horizontal">
    <dt>
        <s:text name="lbl.breakTime"/>
    </dt>
    <dd>
        <s:property value="course.breakTime"/> <s:text name="txt.minutes"/>
    </dd>
    <dt>
        <s:text name="lbl.cohortOrCentury"/>
    </dt>
    <dd>
        <s:property value="audienceName"/>
    </dd>
    <dt>
        <s:text name="lbl.lecturer"/>
    </dt>
    <dd>
        <s:property value="course.lecturer.name"/>
    </dd>
</dl>
<s:actionerror/>

<%-- The schedule --%>
<table class="table">
    <thead>
    <tr>
        <th><s:text name="lbl.startDate"/></th>
        <th><s:text name="lbl.endDate"/></th>
        <th><s:text name="lbl.rooms"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="course.lessons">
        <tr>
            <td><s:property value="startDate"/></td>
            <td><s:property value="endDate"/></td>
            <td><s:property value="rooms"/></td>
            <td class="rightCell">
                <s:url action="DeleteCourseLesson" var="delete" escapeAmp="false">
                    <s:param name="courseLessonId"><s:property value="id"/></s:param>
                    <s:param name="courseId"><s:property value="course.id"/></s:param>
                </s:url>
                <a type="button" class="btn btn-danger btn-sm" href="<s:property value="#delete" />">
                    <span class="glyphicon glyphicon-remove"></span>
                </a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>