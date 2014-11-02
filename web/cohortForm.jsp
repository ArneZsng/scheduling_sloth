
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the cohorts's attributes --%>
	<s:hidden name="cohort.id"/>
    <div class="form-group">
        <label for="cohort.name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <s:textfield name="cohort.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="cohort.major" class="col-sm-2 control-label">Major</label>
        <div class="col-sm-10">
            <s:textfield name="cohort.major" key="lbl.major" size="30" maxlength="80" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="cohort.year" class="col-sm-2 control-label">Year</label>
        <div class="col-sm-10">
            <s:textfield name="cohort.year" key="lbl.year" size="4" maxlength="4" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveCohort" cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelCohort" cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>