<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The car table --%>
	<table style="border-collapse: collapse; border: #CCC;" border="1">
		<tr>
			<th></th>
			<th><s:text name="lbl.brand"/></th>
			<th><s:text name="lbl.color"/></th>
			<th><s:text name="lbl.wheelCount"/></th>
			<th><s:text name="lbl.yearOfProduction"/></th>
			<th><s:text name="lbl.isDieselEngine"/></th>
		</tr>
		<s:iterator value="carList">
			<tr>
				<td><s:radio name="carId" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="brand"/></td>
				<td><s:property value="color"/></td>
				<td><s:property value="wheelCount"/></td>
				<td><s:property value="yearOfProduction"/></td>
				<td><s:property value="isDieselEngine"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddCar"/>
	<s:submit key="btn.edit" action="EditCar"/>
	<s:submit key="btn.delete" action="DeleteCar"/>
</s:form>