<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<c:if test="${action == 'update'}">
	<h1>Modificar soldadura nº ${weld.id}</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Crear soldadura</h1>
</c:if>

<span class="badge bg-danger">${weld.alarm.info}</span> 
<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-bdbody">
		<form:form method="POST" action="/guardarSoldadura"
			modelAttribute="weld">
			<c:if test="${action == 'update'}">
				<td><a href="/crearAlarma" class=" btn btn-primary active mb-4"
					role="button" aria-pressed="true"> Crear alarma</a>
			</c:if>

			<table>
				<c:if test="${action == 'update'}">
					<tr>
						<td><form:label path="id" class="mb-3">ID</form:label></td>
						<td><form:input path="id" class="mb-3" /></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="amp" class="mb-3">Intensidad</form:label></td>
					<td><form:input path="amp" class="mb-3" required="required" /></td>
				</tr>
				<tr>
					<td><form:label path="volt" class="mb-3">Voltaje</form:label></td>
					<td><form:input path="volt" class="mb-3" required="required" /></td>
				</tr>
				<tr>
					<td><form:label path="tolerance" class="mb-3">Tolerancia</form:label></td>
					<td><form:input path="tolerance" class="mb-3"
							required="required" /></td>
				</tr>
				<tr>
					<td><form:label path="note" class="mb-3">Observaciones</form:label></td>
					<td><form:input path="note" class="mb-3" /></td>
				</tr>
				<tr>
					<td><form:hidden path="piece.id" class="mb-3"/></td>
				</tr>
				<tr>
<%-- 					<td><form:label path="state" class="form-label mb-3">Estado</form:label></td> --%>
<%-- 					<td><form:select path="state" class="form-select mb-3"> --%>
<%-- 							<c:forEach items="${weldStatus}" var="status"> --%>
<%-- 								<form:option value="${status}"></form:option> --%>
<%-- 							</c:forEach> --%>

<%-- 						</form:select></td> --%>
				</tr>
				<tr>
					<td><input class="btn btn-primary mb-3" type="submit"
						value="Aceptar"></td>
				</tr>

			</table>
		</form:form>
	</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>