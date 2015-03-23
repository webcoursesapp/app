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
					<spring:message code="addMember.form.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="addMember.form.header" />
						</h3>
					</div>
					<c:set var="availibleUsers" value="${availibleUsers}" />
					<div class="panel-body">
						<form:form modelAttribute="member">
							<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div" />
							<fieldset>
								<div class="form-group">
									<input class="form-control" id="disabledInput" type="text" placeholder="${member.group.name}" disabled="">
								</div>
								<div class="form-group">
									<input class="form-control" id="disabledInput" type="text" placeholder="${member.course.title}" disabled="">
								</div>
								<div class="form-group">
									<spring:message var="userLabel" code="addMember.form.item.user" />
									<form:select id="select2Form" data-placeholder="${userLabel}" items="${availibleUsers}" path="userId" itemValue="userId" />
								</div>
								<spring:message var="submitLabel" code="addMember.form.item.addMemberSubmit" />
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