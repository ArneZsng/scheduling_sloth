
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.room"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the room's attributes --%>
	<s:hidden name="room.id"/>
    <div class="form-group">
        <s:label for="room.name" cssClass="col-sm-2 control-label" key="lbl.name" />
        <div class="col-sm-10">
            <s:textfield name="room.name" size="40" maxlength="100" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <s:label for="room.availableSeats" cssClass="col-sm-2 control-label" key="lbl.availableSeats" />
        <div class="col-sm-10">
            <s:textfield type="number" name="room.availableSeats" size="5" maxlength="5" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="room.breakTime" cssClass="col-sm-2 control-label" key="lbl.changeTime" />
        <div class="col-sm-10">
            <s:textfield type="number" name="room.breakTime" size="4" maxlength="4" value="%{defaultBreakTime}" cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveRoom" cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelRoom" cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>