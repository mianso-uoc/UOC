<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Proyectos</h1>

<a href="/crearProyecto" class="btn btn-primary active mb-2" role="button" aria-pressed="true">Crear proyecto</a>


<c:forEach items="${projects}" var="project">
	${project.name}
</c:forEach>
<div class="card">
	<div class="card-header bg-info">Lista de proyectos</div>
	<div class="card-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>ID proyecto</th>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>ID manager</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects}" var="project">
				<tr>
					
					<td>${project.id}</td>
					<td>${project.name}</td>
					<td>${project.description}</td>
					<td>${project.manager.name}</td>   
					<td><a href="/verProyecto/${project.id}" class=" btn btn-primary active" role="button" aria-pressed="true"><i class="bi bi-eye"></i></a>
					
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

