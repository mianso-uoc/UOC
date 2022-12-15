<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<security:authentication var="usuario" property="principal" />


<h1>Proyectos</h1>
<a href="/crearProyecto" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear proyecto</a>
<%-- ${usuario.projects} --%>

<div class="card">
	<div class="card-header bg-info">Lista de proyectos</div>
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
				<c:forEach items="${usuario.projects}" var="projects">
					<tr>
						<td>${projects.id}</td>
						<td>${projects.name}</td>
						<td>${projects.description}</td>
						<td><a href="/verProyecto/${projects.id}"
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
<script>
	const labels = [ 'January', 'February', 'March', 'April', 'May', 'June', ];

	const data = {
		labels : labels,
		datasets : [ {
			label : 'My First dataset',
			backgroundColor : 'rgb(255, 99, 132)',
			borderColor : 'rgb(255, 99, 132)',
			data : [ 0, 10, 5, 2, 20, 30, 45 ],
		} ]
	};

	const config = {
		type : 'line',
		data : data,
		options : {}
	};
</script>
<script>
	$(document).ready(function() {
		const myChart = new Chart(document.getElementById('myChart'), config);
	});
</script>
