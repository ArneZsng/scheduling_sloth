<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lessons"/> <s:text name="txt.for"/> <s:property value="course.name"/></h1>
<s:actionerror/>

<%-- The lesson table --%>
<s:form cssClass="form-horizontal" role="form">
    <s:hidden name="course.id"/>
    <s:hidden name="course.name"/>
    <s:hidden name="course.breakTime"/>
    <s:hidden name="course.lecturer.id"/>
    <s:hidden name="course.cohort.id"/>
    <s:hidden name="course.century.id"/>

    <table class="table">
        <thead>
        <tr>
            <th><s:text name="lbl.rooms"/></th>
            <th><s:text name="lbl.startDate"/></th>
            <th><s:text name="lbl.endDate"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="course.getLessons()" status="rowstatus">
            <tr>
                <s:hidden name="course.lessons[%{#rowstatus.index}].id"/>
                <td>
                    <s:select name="course.lessons[%{#rowstatus.index}].rooms.id" value="selectedRooms" list="roomList" listKey="id" listValue="name" multiple="true" cssClass="form-control"/>
                </td>
                <td>
                    <div class='input-group date datetimepicker'>
                        <s:textfield name="course.lessons[%{#rowstatus.index}].startDate" value="%{startDate}" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </td>
                <td>
                    <div class='input-group date datetimepicker'>
                            <s:textfield name="course.lessons[%{#rowstatus.index}].endDate" value="%{endDate}" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveCourse"  cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelCourse"  cssClass="btn btn-danger"/>
        </div>
    </div>
</s:form>
