<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The century table --%>
	<table class="table">
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
                <td><s:property value="cohort.name"/></td>
				<td><s:property value="numberOfStudents"/></td>
                <td><s:property value="breakTime"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddCentury" cssClass="btn btn-primary"/>
	<s:submit key="btn.edit" action="EditCentury" cssClass="btn btn-primary"/>
	<s:submit key="btn.delete" action="DeleteCentury" cssClass="btn btn-primary"/>
</s:form>