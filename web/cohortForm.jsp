
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.cohort"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the cohorts's attributes --%>
	<s:hidden name="cohort.id"/>
    <div class="form-group">
        <s:label for="cohort.name" cssClass="col-sm-2 control-label" key="lbl.name" />
        <div class="col-sm-10">
            <s:textfield name="cohort.name" size="40" maxlength="100" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="cohort.major" cssClass="col-sm-2 control-label" key="lbl.major" />
        <div class="col-sm-10">
            <s:textfield name="cohort.major" size="30" maxlength="80" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="cohort.year" cssClass="col-sm-2 control-label" key="lbl.year" />
        <div class="col-sm-10">
            <s:textfield type="number" name="cohort.year" size="4" maxlength="4" cssClass="form-control"/>
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