<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<a href="<s:url action="ShowCohortList"/>"><span aria-hidden="true">&larr;</span> <s:text name="txt.backTo"/> <s:text name="txt.cohorts"/></a></li>

<h1>
    <s:property value="cohort.name"/>
    <div class="pull-right">
        <%-- The edit button --%>
        <s:url action="EditCohort" var="edit" >
            <s:param name="cohortId"><s:property value="cohort.id"/></s:param>
        </s:url>
        <a type="button" class="btn btn-primary btn-large" href="<s:property value="#edit" />">
            <span class="glyphicon glyphicon-pencil"></span> <s:text name="btn.edit"/>
        </a>
        <%-- The delete button --%>
        <s:url action="DeleteCohort" var="delete" >
            <s:param name="cohortId"><s:property value="cohort.id"/></s:param>
        </s:url>
        <a type="button" class="btn btn-danger btn-large" href="<s:property value="#delete" />">
            <span class="glyphicon glyphicon-remove"></span> <s:text name="btn.delete"/>
        </a>
    </div>
</h1>
<dl class="dl-horizontal">
    <dt>
        <s:text name="lbl.major"/>
    </dt>
    <dd>
        <s:property value="cohort.major"/>
    </dd>
    <dt>
        <s:text name="lbl.year"/>
    </dt>
    <dd>
        <s:property value="cohort.year"/>
    </dd>
</dl>
<s:actionerror/>

<%-- The schedule form --%>
<s:form cssClass="form-horizontal" role="form" method="GET">
    <%-- Form fields for the cohort's attributes --%>
    <s:hidden name="cohortId"/>
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
            <s:submit key="btn.showSchedule" action="ShowCohort" cssClass="btn btn-primary"/>
        </div>
    </div>
</s:form>

<%-- The schedule --%>
<table class="table">
    <thead>
    <tr>
        <th><s:text name="lbl.event"/></th>
        <th><s:text name="lbl.lecturer"/></th>
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
            <td><s:property value="course.lecturer.name"/></td>
            <td><s:property value="startDate"/></td>
            <td><s:property value="endDate"/></td>
            <td><s:property value="rooms"/></td>
            <td class="rightCell">
                <s:url action="EditCourse" var="edit" >
                    <s:param name="courseId"><s:property value="course.id"/></s:param>
                </s:url>
                <a type="button" class="btn btn-primary btn-xs" href="<s:property value="#edit" />">
                    <span class="glyphicon glyphicon-pencil"></span> <s:text name="btn.edit"/>
                </a>
                <s:url action="DeleteCourse" var="delete" >
                    <s:param name="courseId"><s:property value="course.id"/></s:param>
                </s:url>
                <a type="button" class="btn btn-danger btn-xs" href="<s:property value="#delete" />">
                    <span class="glyphicon glyphicon-remove"></span> <s:text name="btn.delete"/>
                </a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>