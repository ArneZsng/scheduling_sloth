
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the century's attributes --%>
	<s:hidden name="century.id"/>
	<s:textfield name="century.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="century.cohort" key="lbl.cohort" size="3" maxlength="10" requiredLabel="true"/>
    <s:textfield name="century.numberOfStudents" key="lbl.numberOfStudents" size="3" maxlength="8" requiredLabel="true"/>
    <s:textfield name="century.breakTime" key="lbl.breakTime" size="4" maxlength="4" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveCentury"/>
	<s:submit key="btn.cancel" action="CancelCentury"/>
</s:form>