<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<c:if test="${aviso != null}">
	<div class="alert alert-${tipo} alert-dismissible fade show mt-2"
		role="alert">
		<strong><c:out value="${aviso}"></c:out></strong>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
</c:if>

<security:authentication var="usuario" property="principal" />


<h1>Proyectos</h1>
<a href="/crearProyecto" class="btn btn-primary mb-4 mt-2" role="button" aria-pressed="true">Crear proyecto</a>
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
				<c:forEach items="${projects}" var="project">
					<tr>
						<td>${project.id}</td>
						<td>${project.name}</td>
						<td>${project.description}</td>
						<td><a href="/verProyecto/${project.id}"
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
