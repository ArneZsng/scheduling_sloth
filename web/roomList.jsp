<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.rooms"/></h1>
<%-- The room table --%>
<table class="table table-hover">
    <thead>
    <tr>
        <th><s:text name="lbl.name"/></th>
        <th><s:text name="lbl.availableSeats"/></th>
        <th><s:text name="lbl.changeTime"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="roomList">
        <s:url action="ShowRoom" var="show" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <s:url action="EditRoom" var="edit" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <s:url action="DeleteRoom" var="delete" >
            <s:param name="roomId"><s:property value="id"/></s:param>
        </s:url>
        <tr>
            <td>
                <a href="<s:property value="#show" />">
                    <s:property value="name"/>
                </a>
            </td>
            <td><s:property value="availableSeats"/></td>
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
<s:url action="AddRoom" var="add" />
<a type="button" class="btn btn-primary btn-large" href="<s:property value="#add" />">
    <span class="glyphicon glyphicon-plus"></span> <s:text name="btn.add"/>
</a>