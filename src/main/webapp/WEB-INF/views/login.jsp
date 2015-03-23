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
					<spring:message code="login.heading.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="login.heading.title" />
						</h3>
					</div>
					<c:if test="${not empty error}">
						<div class="alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${error}
						</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="alert alert-info alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${msg}
						</div>
					</c:if>
					<div class="panel-body">
						<form role="form" name="loginForm" action="<c:url value='j_spring_security_check' />" method='POST'>
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="<spring:message code="login.heading.form.email"/>" name="username" type="email" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="<spring:message code="login.heading.form.password"/>" name="password" type="password"
										value="">
								</div>
								<div class="checkbox">
									<label> <input name="_spring_security_remember_me" type="checkbox" value="Remember Me">
									<spring:message code="login.heading.form.rememberme" />
									</label>
								</div>
								<input name="submit" class="btn btn-lg btn-success btn-block" type="submit"
									value="<spring:message code="login.heading.form.login"/>" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
		</tiles:putAttribute>
</tiles:insertDefinition>