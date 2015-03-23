<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<!-- DLA USERA ZALOGOWANEGO -->
					<li><a href="<%=request.getContextPath()%>"><i class="glyphicon glyphicon-home"></i> <spring:message
								code="LeftManuBar.item.home" /></a></li>
					<li><a href="#"><i class="glyphicon glyphicon-book"></i> <spring:message code="LeftManuBar.item.courses" /><span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=request.getContextPath()%>/course/list"><spring:message code="LeftManuBar.item.showAllCourses" /></a></li>
							<li><a href="<%=request.getContextPath()%>/course/my"><spring:message code="LeftManuBar.item.myCourses" /></a></li>
							<li><a href="<%=request.getContextPath()%>/course/manage"><spring:message code="LeftManuBar.item.coursesList" /></a></li>
							<li><a href="<%=request.getContextPath()%>/course/add"><spring:message code="LeftManuBar.item.addCourse" /></a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-table fa-fw"></i> <spring:message code="LeftManuBar.item.groups" /><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=request.getContextPath()%>/group/list"><spring:message code="LeftManuBar.item.managingGroups" /></a></li>
							<li><a href="<%=request.getContextPath()%>/group/add"><spring:message code="LeftManuBar.item.addGroup" /></a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-table fa-fw"></i> <spring:message code="LeftManuBar.item.tests" /><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=request.getContextPath()%>/test/list"><spring:message code="LeftManuBar.item.tests.added" /></a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-edit fa-fw"></i> <spring:message code="LeftManuBar.item.results" /><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=request.getContextPath()%>/result/my"><spring:message code="LeftManuBar.item.myResults" /></a></li>
							<li><a href="<%=request.getContextPath()%>/result/members"><spring:message code="LeftManuBar.item.membersResults" /></a></li>
						</ul> <!-- /.nav-second-level --></li>
<%-- 					<li><a href="<%=request.getContextPath()%>/messages"><i class="fa fa-sitemap fa-fw"></i> <spring:message --%>
<%-- 								code="LeftManuBar.item.messages" /></a></li> --%>
					<li><a href="<%=request.getContextPath()%>/settings"><i class="glyphicon glyphicon-cog"></i> <spring:message
								code="LeftManuBar.item.settings" /></a></li>
					<li><a href="<%=request.getContextPath()%>/user/show"><i class="glyphicon glyphicon-user"></i> <spring:message
								code="LeftManuBar.item.profile" /></a></li>
					<li><a href="<%=request.getContextPath()%>/about"><i class="glyphicon glyphicon-info-sign"></i> <spring:message
								code="LeftManuBar.item.about" /></a></li>
					<li><a href="<%=request.getContextPath()%>/help"><i class="glyphicon glyphicon-question-sign"></i> <spring:message
								code="LeftManuBar.item.help" /></a></li>
					<li><a href="<c:url value="/j_spring_security_logout" />"><i class="glyphicon glyphicon-log-out"></i> <spring:message
								code="topNavigation.item.logout" /></a></li>
				</c:when>
				<c:otherwise>
					<!-- DLA USERA NIEZALOGOWANEGO -->
					<li><a href="<%=request.getContextPath()%>/home"><i class="glyphicon glyphicon-home"></i> <spring:message
								code="LeftManuBar.item.home" /></a></li>
					<li><a href="<%=request.getContextPath()%>/course/list"><i class="glyphicon glyphicon-book"></i> <spring:message
								code="LeftManuBar.item.showAllCourses" /></a></li>
					<li><a href="<%=request.getContextPath()%>/about"><i class="glyphicon glyphicon-info-sign"></i> <spring:message
								code="LeftManuBar.item.about" /></a></li>
					<li><a href="<%=request.getContextPath()%>/help"><i class="glyphicon glyphicon-question-sign"></i> <spring:message
								code="LeftManuBar.item.help" /></a></li>
					<spring:url value="/login" var="loginUrl" htmlEscape="true" />
					<li><a href="${loginUrl}"><i class="glyphicon glyphicon-log-in"></i> <spring:message code="LeftManuBar.item.login" /></a></li>
					<spring:url value="/register" var="registerUrl" htmlEscape="true" />
					<li><a href="${registerUrl}"><i class="glyphicon glyphicon-plus-sign"></i> <spring:message code="LeftManuBar.item.register" /></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>