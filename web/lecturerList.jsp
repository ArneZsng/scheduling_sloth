<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lecturers"/></h1>
<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table class="table">
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
	<s:submit key="btn.add" action="AddLecturer" cssClass="btn btn-primary"/>
	<s:submit key="btn.edit" action="EditLecturer" cssClass="btn btn-primary"/>
	<s:submit key="btn.delete" action="DeleteLecturer" cssClass="btn btn-primary"/>
</s:form>