<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<s:url action="Home"/>"><s:text name="txt.classroomScheduling"/></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:text name="txt.editCoreData"/><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<s:url action="ShowLecturerList"/>"><s:text name="txt.lecturers"/></a></li>
                        <li><a href="<s:url action="ShowRoomList"/>"><s:text name="txt.rooms"/></a></li>
                        <li><a href="<s:url action="ShowCohortList"/>"><s:text name="txt.cohorts"/></a></li>
                        <li><a href="<s:url action="ShowCenturyList"/>"><s:text name="txt.centuries"/></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:text name="txt.editCourses"/><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<s:url action="ShowCourseList"/>"><s:text name="txt.courses"/></a></li>
                        <li><a href="<s:url action="ShowLessonList"/>"><s:text name="txt.lessons"/></a></li>
                    </ul>
                </li>
                <li><a href="#"><s:text name="txt.generateTestdata"/></a></li>
            </ul>
            <s:url var="urlen" includeParams="all" value="">
                <s:param name="request_locale">en</s:param>
            </s:url>
            <s:url var="urlde" includeParams="all" value="">
                <s:param name="request_locale">de</s:param>
            </s:url>
            <ul class="nav navbar-nav navbar-right">
                    <li class="${request.locale.language.equals('en')?'active':''}"><a href="${urlen}"><s:text name="txt.english"/></a></li>
                    <li class="${request.locale.language.equals('de')?'active':''}"><a href="${urlde}"><s:text name="txt.german"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>