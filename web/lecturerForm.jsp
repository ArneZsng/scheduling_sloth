
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the lecturer's attributes --%>
	<s:hidden name="lecturer.id"/>
	<s:textfield name="lecturer.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="lecturer.breakTime" key="lbl.breakTime" size="4" maxlength="4" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveLecturer"/>
	<s:submit key="btn.cancel" action="CancelLecturer"/>
</s:form>
