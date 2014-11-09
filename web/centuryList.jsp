<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.centuries"/></h1>
<s:actionerror/>
<%-- The century table --%>
<table class="table table-hover">
    <thead>
    <tr>
        <th><s:text name="lbl.name"/></th>
        <th><s:text name="lbl.cohort"/></th>
        <th><s:text name="lbl.numberOfStudents"/></th>
        <th><s:text name="lbl.breakTime"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="centuryList">
        <s:url action="EditCentury" var="edit" >
            <s:param name="centuryId"><s:property value="id"/></s:param>
        </s:url>
        <s:url action="DeleteCentury" var="delete" >
            <s:param name="centuryId"><s:property value="id"/></s:param>
        </s:url>
        <tr>
            <td><s:property value="name"/></td>
            <td><s:property value="cohort.name"/></td>
            <td><s:property value="numberOfStudents"/></td>
            <td><s:property value="breakTime"/></td>
            <td class="rightCell">
                <a type="button" class="btn btn-primary btn-xs" href="<s:property value="#edit" />">
                    <span class="glyphicon glyphicon-pencil"></span> <s:text name="btn.edit"/>
                </a>
                <a type="button" class="btn btn-danger btn-xs" href="<s:property value="#delete" />">
                    <span class="glyphicon glyphicon-remove"></span> <s:text name="btn.delete"/>
                </a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>
<%-- The add button --%>
<s:url action="AddCentury" var="add" />
<a type="button" class="btn btn-primary btn-large" href="<s:property value="#add" />">
    <span class="glyphicon glyphicon-plus"></span> <s:text name="btn.add"/>
</a>