
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lesson"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the lessons's attributes --%>
	<s:hidden name="lesson.id"/>
    <div class="form-group">
        <s:label for="lesson.startDate" cssClass="col-sm-2 control-label" key="lbl.startDate" />
        <div class="col-sm-10">
            <s:textfield type="datetime" name="lesson.startDate" size="5" maxlength="10" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="lesson.endDate" cssClass="col-sm-2 control-label" key="lbl.endDate" />
        <div class="col-sm-10">
            <s:textfield type="datetime" name="lesson.endDate" size="5" maxlength="10" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="lesson.room.id" cssClass="col-sm-2 control-label" key="lbl.room" />
        <div class="col-sm-10">
            <s:select name="lesson.room.id" list="roomList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveLesson"  cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelLesson"  cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>