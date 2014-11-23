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

<%-- The schedule form --%>
<s:form cssClass="form-horizontal" role="form" method="GET">
    <s:hidden name="lecturerId"/>
    <div class="form-group">
        <s:label for="week" cssClass="col-sm-2 control-label" key="lbl.week" />
        <div class="col-sm-2">
            <s:select key="week" list="weeks" value="defaultWeek" cssClass="form-control" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="year" cssClass="col-sm-2 control-label" key="lbl.year" />
        <div class="col-sm-2">
            <s:select key="year" list="years" value="defaultYear" cssClass="form-control" />
        </div>
    </div>

    <%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.showSchedule" action="ShowLecturer" cssClass="btn btn-primary"/>
        </div>
    </div>
</s:form>

<%-- The bottom navigation --%>
<s:url action="ShowLecturer" var="previousWeek" escapeAmp="false">
    <s:param name="lecturerId"><s:property value="lecturerId"/></s:param>
    <s:param name="week"><s:property value="weekOfPreviousWeek"/></s:param>
    <s:param name="year"><s:property value="yearOfPreviousWeek"/></s:param>
</s:url>
<s:url action="ShowLecturer" var="nextWeek" escapeAmp="false">
    <s:param name="lecturerId"><s:property value="lecturerId"/></s:param>
    <s:param name="week"><s:property value="weekOfNextWeek"/></s:param>
    <s:param name="year"><s:property value="yearOfNextWeek"/></s:param>
</s:url>
<nav>
    <ul class="pager">
        <li class="previous">
            <a href="<s:property value="#previousWeek" />">
                <span aria-hidden="true">&larr;</span> <s:text name="txt.previousWeek"/>
            </a>
        </li>
        <li class="next">
            <a href="<s:property value="#nextWeek" />">
                <s:text name="txt.nextWeek"/> <span aria-hidden="true">&rarr;</span>
            </a>
        </li>
    </ul>
</nav>

<%-- The schedule --%>
<table class="table">
    <thead>
    <tr>
        <th><s:text name="lbl.event"/></th>
        <th><s:text name="lbl.century"/></th>
        <th><s:text name="lbl.cohort"/></th>
        <th><s:text name="lbl.startDate"/></th>
        <th><s:text name="lbl.endDate"/></th>
        <th><s:text name="lbl.rooms"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="lessonList">
        <tr>
            <td><s:property value="course.name"/></td>
            <td><s:property value="course.century.name"/></td>
            <td><s:property value="course.cohort.name"/></td>
            <td><s:property value="startDate"/></td>
            <td><s:property value="endDate"/></td>
            <td><s:property value="rooms"/></td>
            <td class="rightCell">
                <s:url action="EditCourse" var="edit">
                    <s:param name="courseId"><s:property value="course.id"/></s:param>
                </s:url>
                <a type="button" class="btn btn-primary btn-sm" href="<s:property value="#edit" />">
                    <span class="glyphicon glyphicon-pencil"></span>
                </a>
                <s:url action="DeleteCourseLesson" var="delete" escapeAmp="false">
                    <s:param name="courseLessonId"><s:property value="id"/></s:param>
                    <s:param name="week"><s:property value="week"/></s:param>
                    <s:param name="year"><s:property value="year"/></s:param>
                </s:url>
                <a type="button" class="btn btn-danger btn-sm" href="<s:property value="#delete" />">
                    <span class="glyphicon glyphicon-remove"></span>
                </a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>