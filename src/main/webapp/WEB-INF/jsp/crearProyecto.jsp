<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<c:if test="${action == 'update'}">
	<h1>Proyecto: ${project.name }</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Nuevo proyecto</h1>
</c:if>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-body">
		<form:form method="POST" action="/guardarProyecto"
			modelAttribute="project">

			<table>
				<c:if test="${action == 'update'}">
					<tr>
						<td><form:label path="id" class="mb-3">ID</form:label></td>
						<td><form:input path="id" class="mb-3" /></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="name" class="mb-3">Nombre</form:label></td>
					<td><form:input path="name" class="mb-3" required="true" />
						<form:errors path="name" class="error text-danger" /></td>
				</tr>
				<tr>
					<td><form:label path="description" class="mb-3">Descripción</form:label></td>
					<td><form:input path="description" class="mb-3" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-primary mb-4" type="submit"
						value="Guardar"></td>
					<c:if
						test="${action == 'update' && fn:length(project.projectMachine) > 0 }">
						<td><a href="/proyecto/${project.id}/crearPieza"
							class=" btn mb-4 ms-4 btn-outline-primary" role="button"
							aria-pressed="true">Crear pieza</a>
							<a href="/inicio"
						class=" btn btn-primary mb-4 ms-4" role="button" aria-pressed="true">Volver</a></td>
					</c:if>
					
				</tr>
			</table>
		</form:form>
	</div>
	<div class="card-header mb-3 bg-info">Piezas del proyecto</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Soldador</th>
					<th>Máquina</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pieces}" var="piece">
					<tr>
						<td>${piece.id}</td>
						<td>${piece.name}</td>
						<td>${piece.welder.name}</td>
						<td>${piece.projectMachine.machine.id}</td>
						<td><a href="/verPieza/${piece.id}"
							class=" btn btn-primary btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-pencil"></i></a> <a
							href="/verProyecto/${project.id}/quitarPieza/${piece.id}"
							class=" btn btn-danger btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-trash"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="card-header mb-3 bg-info">Máquinas asignadas al
		proyecto</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Número de serie</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${project.projectMachine}" var="projectMachine">
					<tr>
						<td>${projectMachine.machine.id}</td>
						<td>${projectMachine.machine.serialNumber}</td>
						<td><a
							href="/verProyecto/${project.id}/quitar/${projectMachine.machine.id}"
							class=" btn btn-danger btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-trash"></i></a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="card-header mb-3 bg-info">Máquinas sin asignar al
		proyecto</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Número de serie</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${machines}" var="machine">
					<tr>
						<td>${machine.id}</td>
						<td>${machine.serialNumber}</td>
						<td><a href="/verProyecto/${project.id}/anadir/${machine.id}"
							class=" btn btn-primary btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-plus-lg"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="col-md-6">
	<canvas id="myChart"></canvas>
</div>