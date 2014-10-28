
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="lesson.id"/>
	<s:textfield name="room.startDate" key="lbl.startDate" size="5" maxlength="10" requiredLabel="true"/>
	<s:textfield name="room.endDate" key="lbl.endDate" size="5" maxlength="10" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveLesson"/>
	<s:submit key="btn.cancel" action="CancelLesson"/>
</s:form>