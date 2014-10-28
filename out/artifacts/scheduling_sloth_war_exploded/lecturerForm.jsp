
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="lecturer.id"/>
	<s:textfield name="lecturer.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="lecturer.breakTime" key="lbl.changeTime" size="3" maxlength="10" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveLecturer"/>
	<s:submit key="btn.cancel" action="CancelLecturer"/>
</s:form>
