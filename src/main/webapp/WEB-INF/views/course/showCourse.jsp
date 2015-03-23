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
					<spring:message code="showCourse.header.label" />
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value="${course.title}" />
						</h3>
					</div>

					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th><spring:message code="coursesList.table.header.title" /></th>
										<th><spring:message code="coursesList.table.header.author" /></th>
										<th><spring:message code="coursesList.table.header.createdDate" /></th>
										<c:choose>
											<c:when test="${isMember}">
												<th><spring:message code="coursesList.table.header.showMembers" /></th>
											</c:when>
										</c:choose>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><c:out value="${course.title}" /></td>
										<td><c:url value="/user/show" var="showUser">
												<c:param name="id" value="${course.user.userId}" />
											</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${course.user.name}" /> <c:out value="${course.user.surname}" /></a></td>
										<td><fmt:formatDate value="${course.createdAt}" pattern="yyyy/MM/dd" /></td>
										<c:choose>
											<c:when test="${isMember}">
												<td><c:url value="showCourseUsers" var="showCourseUsers">
														<c:param name="id" value="${course.courseId}" />
													</c:url> <a href="<c:out value='${showCourseUsers}'/>"><spring:message code="coursesList.table.showUsers" /></a></td>
											</c:when>
										</c:choose>
									</tr>


								</tbody>

							</table>

						</div>
						<p>${course.description}</p>
						<c:choose>
							<c:when test="${!isMember and !isCourseOwner}">
								<c:url value="join" var="join">
									<c:param name="id" value="${course.courseId}" />
								</c:url>
								<a href="<c:out value='${join}'/>"> <input name="submit" class="btn btn-success" type="submit"
									value="<spring:message code="coursesList.table.join" />" />
								</a>
							</c:when>
						</c:choose>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<c:choose>
			<c:when test="${isMember}">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<spring:message code="showCourse.header.materials" />
								</h3>
							</div>
							<!-- .panel-heading -->
							<div class="panel-body">
								<div class="panel-group" id="accordion">
									<c:forEach items="${course.materials}" var="material">
										<div class="panel-heading">
											<h3 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<c:out value="${material.materialId}" />"><c:out
														value="${material.title}" /> <div class="pull-right">
														<c:url value="/user/show" var="showUser">
													<c:param name="id" value="${material.createdUser.userId}" />
												</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${material.createdUser.name}" /> <c:out value="${material.createdUser.surname}" /></a>
												</div></a>
											</h3>
										</div>
										<div id="collapseOne<c:out value="${material.materialId}" />" class="panel-collapse collapse">
											<div class="panel-body">
												<c:out value="${material.description}" />
												<br /><br />
												<c:url var="materialShow" value="/material/show">
													<c:param name="id" value="${material.materialId}"></c:param>
												</c:url>
												<form:form modelAttribute="material" method="post" action="${materialShow}">
													<spring:message var="submitLabel" code="coursesList.table.showMaterial" />
													<input name="submit" class="btn btn-success" type="submit" value="${submitLabel}" />
												</form:form>
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

				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Testy dla tego kursu</h3>
							</div>
							<div class="panel-body">
								<h4>Testy rozwiązane</h4>
								<ul>
								<c:forEach items="${solvedTests}" var="test">
									<li><c:out value="${test.title}" /></li>
								</c:forEach>
								</ul>
								<h4>Testy nierozwiązane</h4>
								<ul>
								<c:forEach items="${notSolvedTests}" var="test">
									<c:url var="runTest" value="/test/run">
										<c:param name="id" value="${test.testId}"></c:param>
									</c:url>
									<li><c:out value="${test.title}" /> <a href="<c:out value='${runTest}'/>">Przystap do testu</a></li>
								</c:forEach>
								</ul>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Zadania domowe</h3>
							</div>
							<div class="panel-body">
								<h4>Zadania wysłane</h4>
								<ul>
									<li>Lab 1</li>
									<li>Lab 2</li>
									<li>Lab 3</li>
								</ul>
								<h4>Zadania do zrobienia</h4>
								<ul>
									<li>Lab 4</li>
								</ul>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
				</div>
			</c:when>
			<c:when test="${isCourseOwner}">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<spring:message code="showCourse.header.materials" />
									<div class="pull-right">
										<div class="btn-group">
											<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">
												<spring:message code="showCourse.buttons.actions" /> <span class="caret"></span>
											</button>
											<ul class="dropdown-menu pull-right" role="menu">
												<li><c:url value="/material/add" var="addMaterial">
														<c:param name="courseId" value="${course.courseId}" />
													</c:url> <a href="<c:out value='${addMaterial}'/>"><spring:message code="showCourse.buttons.actions.addMaterial" /></a></li>
												<li><c:url value="/material/list" var="showMaterials">
														<c:param name="id" value="${course.courseId}" />
													</c:url> <a href="<c:out value='${showMaterials}'/>"><spring:message code="showCourse.buttons.actions.manage" /></a></li>
											</ul>
										</div>
									</div>
								</h3>
							</div>
							<!-- .panel-heading -->
							<div class="panel-body">
								<div class="panel-group" id="accordion">
									<c:forEach items="${course.materials}" var="material">
										<div class="panel-heading">
											<h3 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<c:out value="${material.materialId}" />"><c:out
														value="${material.title}" /> <div class="pull-right">
														<c:url value="/user/show" var="showUser">
													<c:param name="id" value="${material.createdUser.userId}" />
												</c:url> <a href="<c:out value='${showUser}'/>"><c:out value="${material.createdUser.name}" /> <c:out value="${material.createdUser.surname}" /></a>
												</div></a>
											</h3>
										</div>
										<div id="collapseOne<c:out value="${material.materialId}" />" class="panel-collapse collapse">
											<div class="panel-body">
												<c:out value="${material.description}" />
												<br /><br />
												<c:url var="materialShow" value="/material/show">
													<c:param name="id" value="${material.materialId}"></c:param>
												</c:url>
												<form:form modelAttribute="material" method="post" action="${materialShow}">
													<spring:message var="submitLabel" code="coursesList.table.showMaterial" />
													<input name="submit" class="btn btn-success" type="submit" value="${submitLabel}" />
												</form:form>
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

				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Testy dla tego kursu
									<div class="pull-right">
										<div class="btn-group">
											<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">
												<spring:message code="showCourse.buttons.actions" /> <span class="caret"></span>
											</button>
											<ul class="dropdown-menu pull-right" role="menu">
												<li><c:url value="/test/add" var="addTest">
														<c:param name="courseId" value="${course.courseId}" />
													</c:url> <a href="<c:out value='${addTest}'/>"><spring:message code="showCourse.buttons.actions.addTest" /></a></li>
												<li><c:url value="/test/list" var="showTests">
														<c:param name="id" value="${course.courseId}" />
													</c:url> <a href="<c:out value='${showTests}'/>"><spring:message code="showCourse.buttons.actions.manage" /></a></li>
											</ul>
										</div>
									</div>
								</h3>
							</div>
							<div class="panel-body">
								<h4>Testy dodane</h4>
								<ul>
									<c:forEach items="${course.tests}" var="test">
										<li><c:url value="/test/show" var="showTests">
												<c:param name="id" value="${test.testId}" />
											</c:url> <a href="<c:out value='${showTests}'/>"><c:out value="${test.title}" /></a></li>
									</c:forEach>
								</ul>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Zadania domowe</h3>
							</div>
							<div class="panel-body">
								<h4>Zadania dodane</h4>
								<ul>
									<li>Zad 1 - program</li>
									<li>Zad 2 - sprawozdanie</li>
								</ul>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
				</div>
			</c:when>
		</c:choose>

	</tiles:putAttribute>
</tiles:insertDefinition>