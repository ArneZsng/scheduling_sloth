<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table style="border-collapse: collapse; border: #CCC;" border="1">
		<tr>
			<th></th>
			<th><s:text name="lbl.name"/></th>
            <th><s:text name="lbl.availableSeats"/></th>
			<th><s:text name="lbl.changeTime"/></th>
		</tr>
		<s:iterator value="roomList">
			<tr>
				<td><s:radio name="roomId" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="name"/></td>
                <td><s:property value="availableSeats"/></td>
				<td><s:property value="changeTime"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddRoom"/>
	<s:submit key="btn.edit" action="EditRoom"/>
	<s:submit key="btn.delete" action="DeleteRoom"/>
</s:form>