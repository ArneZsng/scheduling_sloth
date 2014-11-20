<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.availableRooms"/></h1>
<s:actionerror/>

<%-- The availability form --%>
<s:form cssClass="form-horizontal" role="form" method="GET">
    <div class="form-group">
        <s:label for="startDate" cssClass="col-sm-2 control-label" key="lbl.startDate" />
        <div class="col-sm-10">
            <div class='input-group date datetimepicker'>
                <s:textfield name="startDate" value="%{startDate}" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <s:label for="endDate" cssClass="col-sm-2 control-label" key="lbl.endDate" />
        <div class="col-sm-10">
            <div class='input-group date datetimepicker'>
                <s:textfield name="endDate" value="%{endDate}" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <s:label for="requiredSeats" cssClass="col-sm-2 control-label" key="lbl.requiredSeats" />
        <div class="col-sm-10">
            <s:textfield type="number" name="requiredSeats" size="5" maxlength="5" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>

    <%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.showAvailableRooms" action="ShowRoomListAvailable" cssClass="btn btn-primary"/>
        </div>
    </div>
</s:form>

<%-- The room table --%>
<table class="table table-hover">
    <thead>
    <tr>
        <th><s:text name="lbl.name"/></th>
        <th><s:text name="lbl.availableSeats"/></th>
        <th><s:text name="lbl.changeTime"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="roomList">
        <s:url action="ShowRoom" var="show" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <s:url action="EditRoom" var="edit" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <s:url action="DeleteRoom" var="delete" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <tr>
            <td>
                <a href="<s:property value="#show" />">
                    <s:property value="name"/>
                </a>
            </td>
            <td><s:property value="availableSeats"/></td>
            <td><s:property value="breakTime"/></td>
        </tr>
    </s:iterator>
    </tbody>
</table>