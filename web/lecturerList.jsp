<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lecturers"/></h1>
<%-- The lecturer table --%>
<table class="table table-hover">
    <thead>
        <tr>
            <th><s:text name="lbl.name"/></th>
            <th><s:text name="lbl.breakTime"/></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
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
<s:url action="AddLecturer" var="add" />
<a type="button" class="btn btn-primary btn-large" href="<s:property value="#add" />">
    <span class="glyphicon glyphicon-plus"></span> <s:text name="btn.add"/>
</a>