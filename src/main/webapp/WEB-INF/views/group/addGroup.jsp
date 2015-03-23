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
				<h3 class="page-header">
					<spring:message code="addGroup.form.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="addGroup.form.header" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="group">
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
									<spring:message var="nameLabel" code="addGroup.form.item.name" />
									<form:input class="form-control" placeholder="${nameLabel}" path="name" type="text" />
								</div>
								<div class="form-group">
									<spring:message var="courseLabel" code="addGroup.form.item.course" />
									<form:select id="select2Form" items="${courses}" placeholder="${courseLabel}" path="courseId" itemValue="courseId"
										itemLabel="title" />
								</div>
								<spring:message var="submitLabel" code="addGroup.form.item.addGroupSubmit" />
								<input name="submit" class="btn btn-success" type="submit" value="${submitLabel}" />
							</fieldset>
						</form:form>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>