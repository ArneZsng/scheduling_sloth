
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="course.id"/>
	<s:textfield name="course.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="course.changeTime" key="lbl.changeTime" size="4" maxlength="4" requiredLabel="true"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveCourse"/>
	<s:submit key="btn.cancel" action="CancelCourse"/>
</s:form>
