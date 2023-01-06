<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action == 'update'}">
	<h1>Modificar empresa</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Crear empresa</h1>
</c:if>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-body">
		<form:form method="POST" action="/guardarEmpresa"
			modelAttribute="company">
			<%-- 			<form:hidden path="machines"/> --%>
			<table>
				<c:if test="${action == 'update'}">
					<tr>
						<td><form:label path="id" class="mb-3">ID</form:label></td>
						<td><form:input path="id" class="form-control mb-3" readOnly="true"/></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="nif" class="mb-3">NIF</form:label></td>
					<td><form:input path="nif" class="form-control mb-3" required="true"/> <form:errors
							path="nif" class="error text-danger" /></td>
				</tr>
				<tr>

					<td><form:label path="name" class="mb-3">Nombre</form:label></td>
					<td><form:input path="name" class="form-control mb-3" required="true"/> <form:errors
							path="name" class="error text-danger" /></td>
				</tr>
				<tr>

					<td><form:label path="address" class="mb-3">Dirección</form:label></td>
					<td><form:input path="address" class="form-control mb-3" /></td>

				</tr>
				<tr>
					<td><form:label path="phone" class="mb-3">Teléfono</form:label></td>
					<td><form:input path="phone" class="form-control mb-3" /></td>
				</tr>
				<tr>
					<td><form:label path="description" class="mb-3">Descripción</form:label></td>
					<td><form:input path="description" class="form-control mb-3" /></td>
				</tr>

				<tr>
					<td><input class="btn btn-primary mb-3" type="submit"
						value="Guardar"></td>
					<td><a href="/verEmpresa" class=" btn btn-primary mb-3 ms-3"
						role="button" aria-pressed="true">Volver</a></td>
				</tr>
			</table>
		</form:form>
	</div>
	<c:if test="${action == 'update'}">
		<div class="card mt-1">
			<div class="card-header mb-3 bg-info">Listado de máquinas</div>
			<div class="card-body">
				<table class="table table-striped table-sm table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Número de serie</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${company.machines}" var="machine">
							<tr>
								<td>${machine.id}</td>
								<td>${machine.serialNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</div>
