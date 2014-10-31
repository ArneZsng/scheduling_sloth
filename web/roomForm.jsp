
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the room's attributes --%>
	<s:hidden name="room.id"/>
	<s:textfield name="room.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="room.availableSeats" key="lbl.availableSeats" size="5" maxlength="5" requiredLabel="true"/>
    <s:textfield name="room.changeTime" key="lbl.changeTime" size="4" maxlength="4" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveRoom"/>
	<s:submit key="btn.cancel" action="CancelRoom"/>
</s:form>