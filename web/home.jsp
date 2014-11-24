<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.schedule"/></h1>
<h4><s:property value="startDate" /> - <s:property value="endDate" /></h4>
<s:actionerror/>

<%-- The schedule form --%>
<s:form cssClass="form-horizontal" role="form" method="GET">
    <div class="form-group">
        <s:label for="week" cssClass="col-sm-2 control-label" key="lbl.week" />
        <div class="col-sm-2">
            <s:select name="week" list="weeks" value="defaultWeek" cssClass="form-control" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="year" cssClass="col-sm-2 control-label" key="lbl.year" />
        <div class="col-sm-2">
            <s:select name="year" list="years" value="defaultYear" cssClass="form-control" />
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.showSchedule" action="Home" cssClass="btn btn-primary"/>
        </div>
    </div>
</s:form>

<%-- The bottom navigation --%>
<s:url action="Home" var="previousWeek" escapeAmp="false">
    <s:param name="week"><s:property value="weekOfPreviousWeek"/></s:param>
    <s:param name="year"><s:property value="yearOfPreviousWeek"/></s:param>
</s:url>
<s:url action="Home" var="nextWeek" escapeAmp="false">
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
        <th><s:text name="lbl.lecturer"/></th>
        <th><s:text name="lbl.century"/></th>
        <th><s:text name="lbl.cohort"/></th>
        <th><s:text name="lbl.startDate"/></th>
        <th><s:text name="lbl.endDate"/></th>
        <th><s:text name="lbl.rooms"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="lessonList">
        <s:url action="ShowCourse" var="show" >
            <s:param name="courseId"><s:property value="course.id"/></s:param>
        </s:url>
        <tr>
            <td>
                <a href="<s:property value="#show" />">
                    <s:property value="course.name" />
                </a>
            </td>
            <td><s:property value="course.lecturer.name"/></td>
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