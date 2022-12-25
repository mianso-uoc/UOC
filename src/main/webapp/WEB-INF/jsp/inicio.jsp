<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<security:authentication var="usuario" property="principal" />


<h1>Bienvenido administrador</h1>


<div class="card">
	<div class="card-header bg-info">Opciones disponibles</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Email</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td><a href="/verUser/${user.id}"
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
