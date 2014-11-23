
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.event"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the course's attributes --%>
	<s:hidden name="course.id"/>
    <s:hidden name="collisionFlag"/>
    <div class="form-group">
        <s:label for="course.name" cssClass="col-sm-2 control-label" key="lbl.name" />
        <div class="col-sm-10">
            <s:textfield name="course.name" size="40" maxlength="100" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.breakTime" cssClass="col-sm-2 control-label" key="lbl.changeTime" />
        <div class="col-sm-3">
            <s:textfield type="number" name="course.breakTime" size="4" maxlength="4" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <s:label for="course.breakTime" cssClass="control-label" key="txt.minutes" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.lecturer.id" cssClass="col-sm-2 control-label" key="lbl.lecturer" />
        <div class="col-sm-10">
            <s:select name="course.lecturer.id" list="lecturerList" listKey="id" listValue="name" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.cohort.id" cssClass="col-sm-2 control-label" key="lbl.cohortOrCentury" />
        <div class="col-sm-5">
            <s:select name="course.cohort.id"
                      list="cohortList"
                      listKey="id"
                      listValue="name"
                      cssClass="form-control"
                      headerKey="-1"
                      headerValue="-- Cohort --"/>
        </div>
        <div class="col-sm-5">
            <s:select name="course.century.id"
                      list="centuryList"
                      listKey="id"
                      listValue="name"
                      cssClass="form-control"
                      headerKey="-1"
                      headerValue="-- Century --"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="startDate" cssClass="col-sm-2 control-label" key="lbl.startDate" />
        <div class="col-sm-10">
            <div class='input-group date datetimepicker'>
                <s:textfield name="startDate" value="%{startDate}" size="40" maxlength="100" cssClass="form-control"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <s:label for="endDate" cssClass="col-sm-2 control-label" key="lbl.endDate" />
        <div class="col-sm-10">
            <div class='input-group date datetimepicker'>
                <s:textfield name="endDate" value="%{endDate}" size="40" maxlength="100" cssClass="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <s:label for="rooms" cssClass="col-sm-2 control-label" key="lbl.rooms" />
        <div class="col-sm-6">
            <s:select name="rooms" list="roomList" listKey="id" listValue="name" cssClass ="form-control" multiple="true" />
        </div>
        <div class="col-sm-4">
            <s:submit key="btn.showAvailableRooms"
                      action="ShowRoomListAvailable"
                      cssClass="btn btn-info"
                      target="_blank"
                      onclick="this.form.target='_blank';return true;" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="numberOfRepetitions" cssClass="col-sm-2 control-label" key="lbl.numberOfRepetitions" />
        <div class="col-sm-3">
            <s:textfield type="number" name="numberOfRepetitions" size="2" maxlength="2" cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:if test="collisionFlag">
                <s:submit value="btn.checkAgain" action="EditCourseRecheck" cssClass="btn btn-primary"/>
                <s:submit value="btn.ignoreAndsubmit" action="EditCourseLessons" cssClass="btn btn-default"/>
            </s:if>
            <s:else>
                <s:submit value="btn.submit" action="EditCourseLessons" cssClass="btn btn-primary"/>
            </s:else>

            <s:submit key="btn.cancel" action="CancelCourse" cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>
