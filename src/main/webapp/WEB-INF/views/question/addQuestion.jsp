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
					<spring:message code="addQuestion.form.title" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="addQuestion.form.header" />
						</h3>
					</div>
					<div class="panel-body" id="testForm">
						<form:form modelAttribute="question">
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
									<spring:message var="questionLabel" code="addQuestion.form.item.question" />
									<form:textarea class="form-control" placeholder="${questionLabel}" path="content" rows="4" style="width: 100%" />
								</div>
								<div class="form-group">
									<spring:message var="typeLabel" code="addQuestion.form.item.questionType" />
									<form:select id="select2Form" placeholder="{typeLabel}" items="${questionTypes}" path="type" />
								</div>

								<c:forEach items="${question.answers}" varStatus="i">
									<div class="form-group">
										<div class="input-group">
											<c:choose>
												<c:when test="${question.type=='radio'}">
													<span class="input-group-addon"> <input type="radio" name="rightAnswer" value="${i.index}"
														<c:if test="${question.answers[i.index].correct==true}">checked</c:if>></span>
													<form:input path="answers[${i.index}].content" class="form-control" type="text" />
													<span class="input-group-addon"  style="display: none;" >
														<input type="text" name="answerIndex" value="${i.index}" style="display: none;" />
													</span>
												</c:when>
												<c:when test="${question.type=='checkbox'}">
													<span class="input-group-addon"> <form:checkbox path="answers[${i.index}].correct" /></span>
													<form:input path="answers[${i.index}].content" class="form-control" type="text" />
												</c:when>
											</c:choose>
										</div>
									</div>
								</c:forEach>
								<div class="form-group">
									<c:if test="${question.type!='descriptive'}">
										<input class="btn btn-success btn-sm" type="submit" name="action" value="addAnswer" />
									</c:if>
								</div>
								<div class="form-group pull-right">
								
								<c:url value="/test/show" var="show">
										<c:param name="id" value="${question.test.testId}" />
									</c:url> <a href="<c:out value='${show}'/>">
									
									<span class="btn btn-success btn-sm"><spring:message code="addQuestion.form.item.cancel" /></span></a>
								
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