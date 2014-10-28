<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table style="border-collapse: collapse; border: #CCC;" border="1">
		<tr>
			<th></th>
			<th><s:text name="lbl.name"/></th>
			<th><s:text name="lbl.breakTime"/></th>
		</tr>
		<s:iterator value="lecturerList">
			<tr>
				<td><s:radio name="lecturerId" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="name"/></td>
				<td><s:property value="breakTime"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddLecturer"/>
	<s:submit key="btn.edit" action="EditLecturer"/>
	<s:submit key="btn.delete" action="DeleteLecturer"/>
</s:form>