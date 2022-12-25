<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Usuarios</h1>
<a href="/crearUser" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear usuario</a>
<a href="/crearSoldador" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear soldador</a>
<a href="/crearResponsable" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear responsable</a>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info"> Listado de usuarios</div>
	<div class="card-body">
		<table class="table table-striped table-sm table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Tipo</th>
					<th>Nombre</th>
					<th>Email</th>
					<th>Dirección</th>
					<th>Teléfono</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
				<tr>
					
					<td>${user.id}</td>
					<td>${user.tipo}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.address}</td>
					<td>${user.phone}</td>             
					<td><a href="/verUser/${user.id}" class=" btn btn-primary active" role="button" aria-pressed="true"><i class="bi bi-eye"></i></a>
					
					</td>
					
				</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<div class="col-md-6">
	  <canvas id="myChart"></canvas>
	</div>
	
</div>