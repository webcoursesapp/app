<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<spring:message code="showTest.header.label" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value="${test.title}" />
							(
							<c:out value="${test.course.title}" />
							)
						</h3>
					</div>

					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th><spring:message code="coursesList.table.header.title" /></th>
										<th><spring:message code="coursesList.table.header.author" /></th>
										<th><spring:message code="addTest.form.item.password" /></th>
										<th><spring:message code="addTest.form.item.courseVisibility" /></th>
										<th><spring:message code="addTest.form.item.date" /></th>
										<th><spring:message code="addTest.form.item.time" /></th>
										<th><spring:message code="addTest.form.item.random" /></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><c:out value="${test.title}" /></td>
										<td><c:url value="/user/show" var="showUser">
												<c:param name="id" value="${test.user.userId}" />
											</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${test.user.name}" /> <c:out value="${test.user.surname}" /></a></td>
										<td><c:out value="${test.password}" /></td>
										<td><c:choose>
												<c:when test="${course.visibility}">
													<spring:message code="addTest.form.item.active" />
												</c:when>
												<c:otherwise>
													<spring:message code="addTest.form.item.unactive" />
												</c:otherwise>
											</c:choose></td>
										<td><fmt:formatDate value="${test.startDate}" pattern="yyyy/MM/dd" /> - <fmt:formatDate value="${test.endDate}"
												pattern="yyyy/MM/dd" /></td>
										<td><c:out value="${test.time}" /></td>
										<td><c:choose>
												<c:when test="${course.random}">
													<c:out value="${test.questionsCount}" />
												</c:when>
												<c:otherwise>
													--
												</c:otherwise>
											</c:choose></td>
									</tr>
								</tbody>

							</table>

						</div>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="showTest.header.addedQuestions" />
							<div class="pull-right">
								<c:url value="/question/add" var="addQuestion">
									<c:param name="id" value="${test.testId}" />
								</c:url>
								<a href="<c:out value='${addQuestion}'/>"><span class="glyphicon glyphicon-plus"></span></a>
							</div>
						</h3>
					</div>
					<!-- .panel-heading -->
					<div class="panel-body">
						<div class="panel-group" id="accordion">
							<c:forEach items="${test.questions}" var="question" varStatus="status">
								<div class="panel-heading">
									<h3 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<c:out value="${question.questionId}" />"><spring:message code="showTest.questions.label.question" /> <c:out
												value="${status.index+1}" /></a>
										<c:url value="/question/edit" var="editQuestion">
											<c:param name="id" value="${question.questionId}" />
											<c:param name="init" value="false" />
										</c:url>
										<a href="<c:out value='${editQuestion}'/>"> <span class="glyphicon glyphicon-pencil"></span> <c:url value="/question/remove"
												var="removeQuestion">
												<c:param name="id" value="${question.questionId}" />
											</c:url> <a href="<c:out value='${removeQuestion}'/>"><span class="glyphicon glyphicon-trash"></span> </a>
										</a>
									</h3>
								</div>
								<div id="collapseOne<c:out value="${question.questionId}" />" class="panel-collapse collapse">
									<div class="panel-body">
										<c:out value="${question.content}" />
										<br /> <br />
										<ul>
											<c:forEach items="${question.answers}" var="answer">
												<li><c:if test="${answer.correct}"><u></c:if><c:out value="${answer.content}" /><c:if test="${answer.correct}"></u></c:if></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- .panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>