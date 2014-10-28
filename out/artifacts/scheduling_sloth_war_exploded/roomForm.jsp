
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="room.id"/>
	<s:textfield name="room.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="room.availableSeats" key="lbl.availableSeats" size="3" maxlength="8" requiredLabel="true"/>
    <s:textfield name="room.changeTime" key="lbl.changeTime" size="3" maxlength="10" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveRoom"/>
	<s:submit key="btn.cancel" action="CancelRoom"/>
</s:form>