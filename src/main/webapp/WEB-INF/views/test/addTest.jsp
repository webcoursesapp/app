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
						<form:form modelAttribute="test">
							<c:set var="errors">
								<form:errors path="*" />
							</c:set>
							<c:if test="${not empty errors}">
								<div class="alert alert-danger alert-dismissable">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
									${errors}
								</div>
							</c:if>
							<fieldset>
								<div class="form-group">
									<spring:message var="titleLabel" code="addTest.form.item.title" />
									<form:input class="form-control" placeholder="${titleLabel}" path="title" type="text" />
								</div>
								<div class="form-group">
									<spring:message var="passwordLabel" code="addTest.form.item.password" />
									<form:input class="form-control" placeholder="${passwordLabel}" path="password" type="text" />
								</div>
								<div class="form-group">
									<label><spring:message code="addTest.form.item.courseVisibility" /></label> <label class="radio-inline"> <form:radiobutton
											path="visibility" value="true" /> <spring:message code="addTest.form.item.active" />
									</label> <label class="radio-inline"> <form:radiobutton path="visibility" value="false" /> <spring:message
											code="addTest.form.item.unactive" />
									</label>
								</div>
								<div class="form-group">
									<spring:message var="timeLabel" code="addTest.form.item.time" />
									<form:input class="form-control" placeholder="${timeLabel}" path="time" type="text" />
								</div>
								<div class="form-group">
									<label><spring:message code="addTest.form.item.date" /></label>
									<div class="input-daterange radio-inline">
										<input type="text" class="input-small form-control" style="width: 30%;" name="startDate" id="startDate"
											value="<fmt:formatDate value="${test.startDate}" pattern="MM/dd/yyyy"/>" /> <span class="input-group-addon"
											style="float: left;"><spring:message code="addTest.form.item.dateTo" /></span><input type="text"
											class="input-small form-control" style="width: 30%;" name="endDate" id="endDate"
											value="<fmt:formatDate value="${test.endDate}" pattern="MM/dd/yyyy"/>" />
									</div>
								</div>
								<div class="checkbox">
									<label> <form:checkbox path="random" id="randomCheckbox" /> <spring:message code="addTest.form.item.questionsRandom" />
									</label>
								</div>
								<div class="form-group" id="questionsCount">
									<spring:message var="questionsCountLabel" code="addTest.form.item.questionsCount" />
									<form:input class="form-control" placeholder="${questionsCountLabel}" path="questionsCount" type="text" />
								</div>
								<div class="form-group pull-right">
									<spring:message var="submitLabel" code="addTest.form.item.addTestSubmit" />
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