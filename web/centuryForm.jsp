
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the century's attributes --%>
	<s:hidden name="century.id"/>
    <div class="form-group">
        <label for="century.name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <s:textfield name="century.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
    <label for="century.cohort" class="col-sm-2 control-label">Cohort</label>
    <div class="col-sm-10">
        <s:select name="century.cohort.id" key="lbl.cohort" list="cohortList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
    </div>
    </div>
        <div class="form-group">
        <label for="century.numberOfStudents" class="col-sm-2 control-label">Number Of Students</label>
        <div class="col-sm-10">
            <s:textfield name="century.numberOfStudents" key="lbl.numberOfStudents" size="3" maxlength="8" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="century.breakTime" class="col-sm-2 control-label">Break Time</label>
        <div class="col-sm-10">
            <s:textfield name="century.breakTime" key="lbl.breakTime" size="4" maxlength="4" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveCentury"  cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelCentury"  cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>
