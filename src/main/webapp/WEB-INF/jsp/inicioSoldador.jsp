<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<security:authentication var="usuario" property="principal" />


<h1>Piezas</h1>

<div class="card">
	<div class="card-header bg-info">Lista de piezas</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Definición</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pieces}" var="pieces">
					<tr>
						<td>${pieces.id}</td>
						<td>${pieces.name}</td>
						<td>${pieces.observaciones}</td>
						<td><a href="/soldador/verPieza/${pieces.id}"
							class=" btn btn-primary active" role="button" aria-pressed="true"><i
								class="bi bi-eye"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>
</div>