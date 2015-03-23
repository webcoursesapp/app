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
					<c:out value="${testResult.test.title}" /> - <c:out value="${testResult.test.course.title}" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
<%-- 							<spring:message code="addTest.form.header" />  --%>
							FORMULARZE Z PYTANIAMI
						</h3>
					</div>
					<div class="panel-body" id="testForm">
						<form:form modelAttribute="testResult" action="saveResult">
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
								<c:forEach items="${testResult.questionResults}" var="questionResult" varStatus="qi">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												<c:out value="${questionResult.question.content}" />
											</h3>
										</div>
										<div class="panel-body">
											<c:forEach items="${questionResult.question.answers}" varStatus="ai">
												<div class="form-group">
													<div class="input-group">
														<c:choose>
															<c:when test="${questionResult.question.type=='radio'}">
																<input type="radio" name="questionResults[${qi.index}].question.rightAnswerIndex" value="${ai.index}"
																	<c:if test="${questionResult.question.rightAnswerIndex==ai.index}">checked</c:if>>
																<c:out value="${questionResult.question.answers[ai.index].content}" />
															</c:when>
															<c:when test="${questionResult.question.type=='checkbox'}">
																<form:checkbox path="questionResults[${qi.index}].question.answers[${ai.index}].correct" />
																<c:out value="${questionResult.question.answers[ai.index].content}" />
															</c:when>
														</c:choose>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:forEach>

								<div class="form-group pull-right">
									<c:url value="/course/show" var="show">
										<c:param name="id" value="${testResult.test.course.courseId}" />
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