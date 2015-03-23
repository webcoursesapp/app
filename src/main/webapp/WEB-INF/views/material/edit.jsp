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
					EDIT <spring:message code="addMaterial.form.title" /> <c:out value="${material.course.title}"></c:out>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="addMaterial.form.header" />
						</h3>
					</div>

					<div class="panel-body">
						<form:form modelAttribute="material" enctype="multipart/form-data">
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
									<spring:message var="titleLabel" code="addMaterial.form.item.title" />
									<form:input class="form-control" placeholder="${titleLabel}" path="title" type="text" />
								</div>
								<div class="form-group">
									<spring:message var="descriptionLabel" code="addMaterial.form.item.description" />
									<form:textarea class="form-control" placeholder="${descriptionLabel}" path="description" type="text" rows="5" />
								</div>
								<div class="form-group">
									<spring:message var="fileLabel" code="addMaterial.form.item.fileChoose" />
									<form:input class="form-control" title="${fileLabel}" placeholder="${material.file}" path="uploadFile" type="file" />
								</div>
								<div class="form-group">
									<input class="form-control" id="disabledInput" type="text" placeholder="${material.course.title}" disabled="">
								</div>
								<spring:message var="submitLabel" code="addMaterial.form.item.addMaterialSubmit" />
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