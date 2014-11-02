<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table class="table">
		<tr>
			<th></th>
			<th><s:text name="lbl.name"/></th>
            <th><s:text name="lbl.major"/></th>
			<th><s:text name="lbl.year"/></th>
		</tr>
		<s:iterator value="cohortList">
			<tr>
				<td><s:radio name="cohortId" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="name"/></td>
                <td><s:property value="major"/></td>
				<td><s:property value="year"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddCohort" cssClass="btn btn-primary"/>
	<s:submit key="btn.edit" action="EditCohort" cssClass="btn btn-primary"/>
	<s:submit key="btn.delete" action="DeleteCohort" cssClass="btn btn-primary"/>
</s:form>