<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<c:if test="${action == 'update'}">
	<h1>Modificar pieza nº ${piece.id}</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Crear pieza</h1>
</c:if>


<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-bdbody">
		<form:form method="POST" action="/guardarPieza" modelAttribute="piece">
			<c:if test="${action == 'update'}">
				<td><a href="/verPieza/${piece.id}/crearSoldadura"
					class=" btn btn-primary active mb-4" role="button"
					aria-pressed="true"> Crear soldadura</a>
			</c:if>

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
					<form:errors path="name" class="error text-danger"/></td>
				</tr>
				<tr>
					<td><form:label path="observaciones" class="mb-3">Observaciones</form:label></td>
					<td><form:input path="observaciones" class="mb-3" /></td>
				</tr>
				<tr>
					<!-- 				path hace referencia a que en la clase piece hay un objeto welder. equivale a piece.welder -->
					<td><form:label path="welder" class="mb-3">Soldador</form:label></td>
					<td><form:select path="welder" class="form-select mb-3">
							<c:forEach items="${welders}" var="welder">
								<form:option value="${welder}">${welder.name}</form:option>
							</c:forEach>

						</form:select></td>
				</tr>
				<tr>
					<form:hidden path="projectMachine.project" />
					<!-- 				path hace referencia a modelAttribute, en este caso piece -->
					<td><form:label path="projectMachine.machine.id" class="mb-3">Máquina</form:label></td>
					<td><form:select path="projectMachine.machine.id"
							class="form-select mb-3">
							<!-- 					project es el nombre que se le da al objeto para model.addAtribute -->
							<!-- la variable projectMachine es como se llama cada objeto en la iteracion del forEach -->
							<!-- en value se mete el objeto entero y se especifica que muestre el id solamente en el desplegable -->
							<c:forEach items="${project.projectMachine}" var="projectMachine">
								<form:option value="${projectMachine.machine.id}">${projectMachine.machine.id}</form:option>
							</c:forEach>

						</form:select></td>
				</tr>
				<tr>
					<td><input class="btn btn-primary mb-3" type="submit"
						value="Aceptar"></td>
				</tr>
			</table>
		</form:form>
	</div>


	<div class="card-header mb-3 bg-info">Soldaduras de la pieza</div>
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
					<td><a href="/verSoldadura/${weld.id}"
						class=" btn btn-primary btn-sm active" role="button"
						aria-pressed="true"><i class="bi bi-pencil"></i></a> <a
						href="/verPieza/${piece.id}/quitarSoldadura/${weld.id}"
						class=" btn btn-danger btn-sm active" role="button"
						aria-pressed="true"><i class="bi bi-trash"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>