<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<h1>Empresa </h1>

<div class="card mt-3">
	<div class="card-header  bg-info"> Detalle de empresa</div>
	<div class="card-body ">


		id: ${company.id} ${company.name}

		<form:form method="POST" action="/guardarEmpresa"
			modelAttribute="company">
			<table>
				<tr>
					<td><form:label path="nif" class= "mb-3">NIF</form:label></td>
					<td><form:input path="nif" class= "mb-3" /></td>
				</tr>
				<tr>
			</table>
		</form:form>
	</div>
	<div class="col-md-6">
	  <canvas id="myChart"></canvas>
	</div>
	
</div>