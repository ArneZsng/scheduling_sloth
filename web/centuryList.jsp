<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table style="border-collapse: collapse; border: #CCC;" border="1">
		<tr>
			<th></th>
			<th><s:text name="lbl.name"/></th>
            <th><s:text name="lbl.cohort"/></th>
			<th><s:text name="lbl.numberOfStudents"/></th>
            <th><s:text name="lbl.breakTime"/></th>
		</tr>
		<s:iterator value="centuryList">
			<tr>
				<td><s:radio name="centuryId" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="name"/></td>
                <td><s:property value="cohort"/></td>
				<td><s:property value="numberOfStudents"/></td>
                <td><s:property value="breakTime"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddCentury"/>
	<s:submit key="btn.edit" action="EditCentury"/>
	<s:submit key="btn.delete" action="DeleteCentury"/>
</s:form>