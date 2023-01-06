<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<h1>Soldaduras</h1>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Listado de soldaduras</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Estado</th>
					<th>Intensidad</th>
					<th>Voltaje</th>
					<th>Proyecto</th>
					<th>Pieza</th>
					<th>Alarma</th>
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
						<td>${weld.piece.projectMachine.project.name}</td>
						<td>${weld.piece.name}</td>
						<td>${weld.alarm.name}</td>
						<td><a href="/verSoldadura/${weld.id}"
							class=" btn btn-primary btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-eye"></i></a> <a
							href="/verPieza/${weld.piece.id}/quitarSoldadura/${weld.id}"
							class=" btn btn-danger btn-sm active" role="button"
							aria-pressed="true"><i class="bi bi-trash"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>