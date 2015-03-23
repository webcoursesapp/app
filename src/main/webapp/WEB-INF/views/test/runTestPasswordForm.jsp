<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<spring:message code="addTest.form.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="addTest.form.header" />
						</h3>
					</div>
					<div class="panel-body" id="testForm">
						<form:form method="POST">
							<c:set var="errors">
								<form:errors path="*" />
							</c:set>
							<c:if test="${not empty errors}">
								<div class="alert alert-danger alert-dismissable">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
									${errors}
								</div>
							</c:if>

							<c:if test="${not empty err}">
								<div class="alert alert-danger alert-dismissable">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
									<c:forEach items="${err}" var="error">
										${error}
										<br />
									</c:forEach>
								</div>
							</c:if>
							<fieldset>
								<div class="form-group">
									<spring:message var="titleLabel" code="addTest.form.item.title" />
									<input class="form-control" id="disabledInput" placeholder="${test.title}" type="text" disabled="" />
								</div>
								<div class="form-group">
									<input class="form-control" id="disabledInput" placeholder="${test.time} minutes" type="text" disabled="" />
								</div>

								<div class="form-group">
									<spring:message var="passwordLabel" code="addTest.form.item.password" />
									<c:if test="${empty test.password}">
										<spring:message var="passwordLabel" code="addTest.form.item.passwordEmpty" />
									</c:if>
									<input class="form-control" placeholder="${passwordLabel}" name="password" type="text"
										<c:if test="${empty test.password}">disabled=""</c:if> />
								</div>
								<div class="form-group pull-right">
									<c:url value="/course/show" var="showCourse">
										<c:param name="id" value="${test.course.courseId}" />
									</c:url>
									<a href="<c:out value='${showCourse}'/>"> <span class="btn btn-success btn-sm"><spring:message
												code="addQuestion.form.item.cancel" /></span></a>

									<spring:message var="submitLabel" code="addQuestion.form.item.submit" />
									<input name="submit" class="btn btn-success btn-sm" type="submit" value="${submitLabel}" />
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>