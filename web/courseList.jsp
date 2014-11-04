<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The course table --%>
	<table class="table">
		<tr>
			<th></th>
			<th><s:text name="lbl.name"/></th>
            <th><s:text name="lbl.changeTime"/></th>
		</tr>
		<s:iterator value="courseList">
			<tr>
				<td><s:radio name="courseID" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="name"/></td>
                <td><s:property value="breakTime"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddCourse" cssClass="btn btn-primary"/>
	<s:submit key="btn.edit" action="EditCourse" cssClass="btn btn-primary"/>
	<s:submit key="btn.delete" action="DeleteCourse" cssClass="btn btn-primary"/>
</s:form>