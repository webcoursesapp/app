<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header"><spring:message code="editCourse.form.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="editCourse.form.header" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="course">
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
									<spring:message var="titleLabel" code="addCourse.form.item.title" />
									<form:input class="form-control" placeholder="${titleLabel}" path="title" type="text" />
								</div>
								<div class="form-group">
									<spring:message var="descriptionLabel" code="addCourse.form.item.description" />
									<form:input class="form-control" placeholder="${descriptionLabel}" path="description" type="text" />
								</div>
								<c:if test="${course.password==''}">
								<div class="form-group">
									<spring:message var="passwordLabel" code="addCourse.form.item.password" />
									<form:input class="form-control" placeholder="${passwordLabel}" path="password" type="password" />
								</div>
								</c:if>
								<div class="form-group">
									<label><spring:message code="addCourse.form.item.courseVisibility" /></label> 
										<label class="radio-inline"> 
											<form:radiobutton path="active" value="true" /> 
											<spring:message code="addCourse.form.item.active" />
										</label> 
										<label class="radio-inline"> 
											<form:radiobutton path="active" value="false" /> 
											<spring:message code="addCourse.form.item.unactive" />
										</label>
								</div>
								<spring:message var="submitLabel" code="addCourse.form.item.addCourseSubmit" />
								<input name="submit" class="btn btn-success btn-sm" type="submit" value="${submitLabel}" />
							</fieldset>
						</form:form>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>