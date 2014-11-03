
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.lecturer"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the lecturer's attributes --%>
    <s:hidden name="lecturer.id"/>
    <div class="form-group">
        <label for="lecturer.name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <s:textfield name="lecturer.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="lecturer.breakTime" class="col-sm-2 control-label">Break Time</label>
        <div class="col-sm-10">
            <s:textfield name="lecturer.breakTime" key="lbl.breakTime" size="4" maxlength="4" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveLecturer" cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelLecturer" cssClass="btn btn-danger"/>
        </div>
    </div>
</s:form>
