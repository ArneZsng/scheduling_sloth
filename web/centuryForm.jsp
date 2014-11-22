
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.century"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the century's attributes --%>
	<s:hidden name="century.id"/>
    <div class="form-group">
        <s:label for="century.name" cssClass="col-sm-2 control-label" key="lbl.name" />
        <div class="col-sm-10">
            <s:textfield name="century.name" size="40" maxlength="100" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="century.cohort.id" cssClass="col-sm-2 control-label" key="lbl.cohort" />
        <div class="col-sm-10">
            <s:select name="century.cohort.id" list="cohortList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
        <div class="form-group">
            <s:label for="century.numberOfStudents" cssClass="col-sm-2 control-label" key="lbl.numberOfStudents" />
        <div class="col-sm-10">
            <s:textfield type="number" name="century.numberOfStudents" size="4" maxlength="4" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="century.breakTime" cssClass="col-sm-2 control-label" key="lbl.breakTime" />
        <div class="col-sm-10">
            <s:textfield type="number" name="century.breakTime" size="4" maxlength="4" requiredLabel="true" value="%{defaultBreakTime}" cssClass="form-control"/>
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
