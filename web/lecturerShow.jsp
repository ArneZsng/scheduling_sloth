<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<a href="<s:url action="ShowLecturerList"/>"><span aria-hidden="true">&larr;</span> <s:text name="txt.backTo"/> <s:text name="txt.lecturers"/></a></li>

<h1>
    <s:property value="lecturer.name"/>
    <div class="pull-right">
        <%-- The edit button --%>
        <s:url action="EditLecturer" var="edit" >
            <s:param name="lecturerId"><s:property value="lecturer.id"/></s:param>
        </s:url>
        <a type="button" class="btn btn-primary btn-large" href="<s:property value="#edit" />">
            <span class="glyphicon glyphicon-pencil"></span> <s:text name="btn.edit"/>
        </a>
        <%-- The delete button --%>
        <s:url action="DeleteLecturer" var="delete" >
            <s:param name="lecturerId"><s:property value="lecturer.id"/></s:param>
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
        <s:property value="lecturer.breakTime"/> <s:text name="txt.minutes"/>
    </dd>
</dl>
<s:actionerror/>
<%-- The lecturer table --%>
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
    <s:iterator value="lecturer.courses">
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