<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lecturers"/></h1>
<s:form>
	<s:actionerror/>
	<%-- The lecturer table --%>
	<table class="table">
		<tr>
			<th><s:text name="lbl.name"/></th>
			<th><s:text name="lbl.breakTime"/></th>
            <th></th>
            <th></th>
		</tr>
		<s:iterator value="lecturerList">
            <s:url action="EditLecturer" var="edit" >
                <s:param name="lecturerId"><s:property value="id"/></s:param>
            </s:url>
            <s:url action="DeleteLecturer" var="delete" >
                <s:param name="lecturerId"><s:property value="id"/></s:param>
            </s:url>
			<tr>
				<td><s:property value="name"/></td>
				<td><s:property value="breakTime"/></td>
                <td><a href="<s:property value="#edit" />"><s:text name="btn.edit"/></a></td>
                <td><a href="<s:property value="#delete" />"><s:text name="btn.delete"/></a></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The add button --%>
	<s:submit key="btn.add" action="AddLecturer" cssClass="btn btn-primary"/>
</s:form>