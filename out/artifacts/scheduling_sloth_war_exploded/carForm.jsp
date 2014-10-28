<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="car.id"/>
	<s:textfield name="car.brand" key="lbl.brand" size="40" maxlength="100" requiredLabel="true"/>
	<s:textfield name="car.color" key="lbl.color" size="10" maxlength="50" requiredLabel="true"/>
	<s:textfield name="car.wheelCount" key="lbl.wheelCount" size="1" maxlength="1" requiredLabel="true"/>
	<s:textfield name="car.yearOfProduction" key="lbl.yearOfProduction" size="4" maxlength="4"/>
	<s:checkbox name="car.isDieselEngine" key="lbl.isDieselEngine"/>

	<%-- The buttons --%>
	<s:submit key="btn.save" action="SaveCar"/>
	<s:submit key="btn.cancel" action="CancelCar"/>
</s:form>
