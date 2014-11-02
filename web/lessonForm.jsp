
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the car's attributes --%>
	<s:hidden name="lesson.id"/>
    <div class="form-group">
        <label for="room.startDate" class="col-sm-2 control-label">Start Date</label>
        <div class="col-sm-10">
            <s:textfield name="room.startDate" key="lbl.startDate" size="5" maxlength="10" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="room.endDate" class="col-sm-2 control-label">End Date</label>
        <div class="col-sm-10">
            <s:textfield name="room.endDate" key="lbl.endDate" size="5" maxlength="10" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.save" action="SaveLesson"/>
            <s:submit key="btn.cancel" action="CancelLesson"/>
        </div>
    </div>

</s:form>