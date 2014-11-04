
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the room's attributes --%>
	<s:hidden name="room.id"/>
    <div class="form-group">
        <label for="room.name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <s:textfield name="room.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
        </div>
    </div>
    <div class="form-group">
        <label for="room.availableSeats" class="col-sm-2 control-label">Available Seats</label>
        <div class="col-sm-10">
            <s:textfield name="room.availableSeats" key="lbl.availableSeats" size="5" maxlength="5" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="room.breakTime" class="col-sm-2 control-label">Change Time</label>
        <div class="col-sm-10">
            <s:textfield name="room.breakTime" key="lbl.changeTime" size="4" maxlength="4" requiredLabel="true"  cssClass="form-control"/>
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