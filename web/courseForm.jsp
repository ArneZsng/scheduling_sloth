
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h1><s:text name="txt.course"/></h1>

<s:form cssClass="form-horizontal" role="form">
	<%-- Form fields for the course's attributes --%>
	<s:hidden name="course.id"/>
    <div class="form-group">
        <s:label for="course.name" cssClass="col-sm-2 control-label" key="lbl.name" />
        <div class="col-sm-10">
            <s:textfield name="course.name" size="40" maxlength="100" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.breakTime" cssClass="col-sm-2 control-label" key="lbl.changeTime" />
        <div class="col-sm-10">
            <s:textfield type="number" name="course.breakTime" size="4" maxlength="4" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.lecturer.id" cssClass="col-sm-2 control-label" key="lbl.lecturer" />
        <div class="col-sm-10">
            <s:select name="course.lecturer.id" list="lecturerList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.cohort.id" cssClass="col-sm-2 control-label" key="lbl.cohort" />
        <div class="col-sm-10">
            <s:select name="course.cohort.id" list="cohortList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="course.century.id" cssClass="col-sm-2 control-label" key="lbl.century" />
        <div class="col-sm-10">
            <s:select name="course.century.id" list="centuryList" listKey="id" listValue="name" requiredLabel="true" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <s:label for="numberOfLessons" cssClass="col-sm-2 control-label" key="lbl.numberOfLessons" />
        <div class="col-sm-10">
            <s:textfield type="number" name="numberOfLessons" size="2" maxlength="2" requiredLabel="true"  cssClass="form-control"/>
        </div>
    </div>

	<%-- The buttons --%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <s:submit key="btn.next" action="EditCourseLessons" cssClass="btn btn-primary"/>
            <s:submit key="btn.cancel" action="CancelCourse" cssClass="btn btn-danger"/>
        </div>
    </div>

</s:form>
