
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lessons"/> <s:text name="txt.for"/> <s:property value="course.name"/></h1>

<%-- The lesson table --%>
<s:form cssClass="form-horizontal" role="form" action="ShowRoomListAvailable">
    <s:hidden name="course.id"/>
    <s:hidden name="course.name"/>
    <s:hidden name="course.breakTime"/>
    <s:hidden name="course.lecturer.id"/>
    <s:hidden name="course.cohort.id"/>
    <s:hidden name="course.century.id"/>
    <s:hidden name="collisionFlag"/>
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
        <s:iterator value="course.lessons" status="rowstatus">
            <tr>
                <s:hidden name="course.lessons[%{#rowstatus.index}].id"/>
                <td>
                    <s:select name="course.lessons[%{#rowstatus.index}].rooms.id" value="%{getRoomIdsFromList(rooms)}" list="roomList" listKey="id" listValue="name" multiple="true" cssClass="form-control"/>
                </td>
                <td>
                    <div class='input-group date datetimepicker'>
                        <s:textfield name="course.lessons[%{#rowstatus.index}].startDate" value="%{startDate}" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <div>
                        <%--<input type="submit"
                               name="availableRooms[0]"
                               value="Show Available Rooms"
                               class="btn btn-info room-button"
                               onclick="this.form.target='_blank';return true;"
                               target="_blank">--%>
                        <s:submit key="btn.showAvailableRooms"
                                  name="availableRooms[%{#rowstatus.index}]"
                                  cssClass="btn btn-info room-button"
                                  target="_blank"
                                  onclick="this.form.target='_blank';return true;" />
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
            <s:if test="collisionFlag">
                <s:submit key="btn.ignoreAndsubmit" action="SaveCourse" cssClass="btn btn-primary"/>
            </s:if>
            <s:else>
                <s:submit key="btn.submit" action="SaveCourse" cssClass="btn btn-primary"/>
            </s:else>

            <s:submit key="btn.cancel" action="CancelCourse" cssClass="btn btn-danger"/>
        </div>
    </div>
</s:form>
