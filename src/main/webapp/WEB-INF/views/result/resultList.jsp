<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="content">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">Wyniki testow</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<c:forEach items="${resultMap}" var="courses">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${courses.key.title}</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Nazwa testu</th>
											<th>Wykonał</th>
											<th>Ocena</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${courses.value}" var="testSummary">
											<tr class="odd gradeX">
												<td><c:out value="${testSummary.test.title}" /></td>
												<td><c:out value="${testSummary.testResult.user}" /></td>
												<td><c:out value="${testSummary.rate}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /.panel-body -->
				</c:forEach>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>