
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the course's attributes --%>
	<s:hidden name="course.id"/>
    <div class="form-group">
        <label for="course.name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-10">
            <s:textfield name="course.name" key="lbl.name" size="40" maxlength="100" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="course.breakTime" class="col-sm-2 control-label">Change Time</label>
        <div class="col-sm-10">
            <s:textfield name="course.breakTime" key="lbl.changeTime" size="4" maxlength="4" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveCourse" cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelCourse" cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>
