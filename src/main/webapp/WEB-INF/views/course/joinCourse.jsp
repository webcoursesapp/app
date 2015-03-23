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
					<spring:message code="joinCourse.form.title" /> ${member.course.title}
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="joinCourse.form.header" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="member">
							<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div" />
							<fieldset>
								<div class="form-group">
									<input class="form-control" id="disabledInput" type="text" placeholder="${member.course.title}" disabled="">
								</div>
								<c:if test="${member.course.password!=''}">
									<div class="form-group">
										<spring:message var="passwordLabel" code="joinCourse.form.item.password" />
										<form:input class="form-control" placeholder="${passwordLabel}" path="passwordToCourse" type="text" />
									</div>
								</c:if>
								<div class="form-group">
									<spring:message var="groupLabel" code="joinCourse.form.item.group" />
									<form:select id="select2Form" items="${groups}" data-placeholder="${groupLabel}" path="groupId" itemValue="groupId"
										itemLabel="name" />
								</div>
								<spring:message var="submitLabel" code="coursesList.table.join" />
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