<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
	<s:actionerror/>
	<%-- The lesson table --%>
	<table class="table">
		<tr>
			<th></th>
			<th><s:text name="lbl.startDate"/></th>
            <th><s:text name="lbl.endDate"/></th>
		</tr>
		<s:iterator value="lessonList">
			<tr>
				<td><s:radio name="lessonID" list="#{id:''}" theme="simple"/></td>
				<td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
			</tr>
		</s:iterator>
	</table>
	<%-- The buttons --%>
	<s:submit key="btn.add" action="AddLesson" cssClass="btn btn-primary"/>
	<s:submit key="btn.edit" action="EditLesson" cssClass="btn btn-primary"/>
	<s:submit key="btn.delete" action="DeleteLesson" cssClass="btn btn-primary"/>
</s:form>