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
					<spring:message code="register.form.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="register.header.title" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="user">
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
									<spring:message var="emailLabel" code="login.heading.form.email" />
									<form:input class="form-control" placeholder="${emailLabel}" path="email" type="email" />
								</div>
								<div class="form-group">
									<spring:message var="passwordLabel" code="login.heading.form.password" />
									<form:input class="form-control" placeholder="${passwordLabel}" path="password" type="password" />
								</div>
								<div class="form-group">
									<spring:message var="firstNameLabel" code="showUser.tab.profile.firstName" />
									<form:input class="form-control" placeholder="${firstNameLabel}" path="name" type="text" />
								</div>

								<div class="form-group">
									<spring:message var="lastNameLabel" code="showUser.tab.profile.lastName" />
									<form:input class="form-control" placeholder="${lastNameLabel}" path="surname" type="text" />
								</div>
								<div class="form-group">
									<spring:message var="countryLabel" code="showUser.tab.profile.country" />
									<form:select id="select2Form" placeholder="${countryLabel}" items="${countryList}" itemLabel="name" itemValue="name" path="country" />
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<spring:message var="registerLabel" code="register.form.registerSubmit" />
								<input name="submit" class="btn btn-lg btn-success btn-block" type="submit" value="${registerLabel}" />
							</fieldset>
						</form:form>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>