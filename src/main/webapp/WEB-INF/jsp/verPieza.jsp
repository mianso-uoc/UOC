<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<h1>Ver pieza ${piece.id}</h1>



<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Información de la pieza</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Observaciones</th>
					<th>Máquina</th>
					<th>Proyecto</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${piece.id}</td>
					<td>${piece.name}</td>
					<td>${piece.observaciones}</td>
					<td>${piece.projectMachine.machine.id}</td>
					<td>${piece.projectMachine.project.name}</td>
				</tr>
			</tbody>
		</table>
	</div>


	<div class="card-header mb-3 bg-info">Soldaduras de la pieza</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Estado</th>
					<th>Intensidad</th>
					<th>Voltaje</th>
					<th>Tolerancia</th>
					<th>Observaciones</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${welds}" var="weld">
					<tr>
						<td>${weld.id}</td>
						<td>${weld.state}</td>
						<td>${weld.amp}</td>
						<td>${weld.volt}</td>
						<td>${weld.tolerance}</td>
						<td>${weld.note}</td>
						<td><a href="/soldador/verSoldadura/${weld.id}"
							class=" btn btn-primary btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-eye"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/inicio" class=" btn btn-primary mt-3"
			role="button" aria-pressed="true">Volver</a>
	</div>
</div>
<div class="col-md-6">
	<canvas id="myChart"></canvas>
</div>

