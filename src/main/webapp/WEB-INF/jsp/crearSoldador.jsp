<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${action == 'update'}">
	<h1>Modificar soldador</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Crear soldador</h1>
</c:if>
<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-body">
		<form:form method="POST" action="/guardarSoldador"
			modelAttribute="welder">

			<table>
				<c:if test="${action == 'update'}">
					<tr class="">
						<td><form:label path="id" class="form-label mb-3">ID</form:label></td>
						<td><form:input path="id" class="form-control mb-3" readOnly="true"/></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="name" class="form-label mb-3">Nombre</form:label></td>
					<td><form:input path="name" class="form-control mb-3"
							required="true" />
						<form:errors path="password" class="error text-danger" /></td>
				</tr>
				<c:if test="${action == 'new'}">
					<tr>
						<td><form:label path="password" class="form-label mb-3">Contraseña</form:label></td>
						<td><form:input path="password" class="form-control mb-3"
								required="true" />
							<form:errors path="password" class="error text-danger" /></td>
					</tr>
				</c:if>
				<c:if test="${action == 'update'}">
					<form:hidden path="password"></form:hidden>
				</c:if>
				<tr>
					<td><form:label path="email" class="form-label mb-3">Email</form:label></td>
					<td><form:input path="email" type="email"
							class="form-control mb-3" required="true" />
						<form:errors path="password" class="error text-danger" /></td>
				</tr>
				<tr>
					<td><form:label path="address" class="form-label mb-3">Dirección</form:label></td>
					<td><form:input path="address" class="form-control mb-3" /></td>

				</tr>
				<tr>
					<td><form:label path="phone" class="form-label mb-3">Teléfono</form:label></td>
					<td><form:input path="phone" class="form-control mb-3" /></td>
				</tr>
				<tr>
					<td><form:label path="experienceYears" class="form-label mb-3">Experiencia</form:label></td>
					<td><form:input path="experienceYears"
							class="form-control mb-3" /></td>
				</tr>
				<tr>
					<td><form:label path="weldMode" class="form-label mb-3">Especialidad</form:label></td>
					<td><form:select path="weldMode" class="form-select mb-3">
							<c:forEach items="${weldModes}" var="weldMode">
								<form:option value="${weldMode}"></form:option>
							</c:forEach>

						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="company" class="mb-3">Empresa</form:label></td>
					<td><form:select path="company" class="form-select mb-3">
							<c:forEach items="${companies}" var="company">
								<form:option value="${company}">${company.name}</form:option>
							</c:forEach>

						</form:select></td>
				</tr>
				<tr>
					<td><input class="btn btn-primary" type="submit"
						value="Guardar"></td>
					<td><a href="/user" class=" btn btn-primary ms-4"
						role="button" aria-pressed="true">Volver</a></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>