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
							Wynik testu:
							<c:out value="${testSummary.test.title}" />
							(
							<c:out value="${testSummary.test.course.title}" />
							)
						</h3>
					</div>

					<div class="panel-body">
					Poprawnych pytań: <c:out value="${testSummary.rightQuestions.size()}" /><br />
					Niepoprawnych pytań: <c:out value="${testSummary.wrongQuestions.size()}" /><br />
					Końcowa ocena: <c:out value="${testSummary.rate}" /><br />
					</div>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>