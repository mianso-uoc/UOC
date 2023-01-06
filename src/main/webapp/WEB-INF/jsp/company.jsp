<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Empresas</h1>

<a href="/crearEmpresa" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear empresa</a>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info"> Listado de empresas creadas</div>
	<div class="card-bdbody">
		<table class="table table-striped table-sm table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Direcci�n</th>
					<th>Tel�fono</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${companies}" var="company">
				<tr>
					
					<td>${company.id}</td>
					<td>${company.name}</td>
					<td>${company.address}</td>
					<td>${company.phone}</td>             
					<td><a href="/verEmpresa/${company.id}" class=" btn btn-primary active" role="button" aria-pressed="true"><i class="bi bi-eye"></i></a>
					<a href="/quitarEmpresa/${company.id}" class=" btn btn-danger active" role="button" aria-pressed="true"><i class="bi bi-trash"></i></a>
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