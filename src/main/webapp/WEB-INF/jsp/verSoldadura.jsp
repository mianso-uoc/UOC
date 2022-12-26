<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<h1>Valores de la soldadura ${weld.id}</h1>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Información de la soldadura</div>
	<div class="card-body">
		<h3>Intensidad: ${weld.amp}</h3>
		<h3>Voltaje: ${weld.volt}</h3>
		<h3>Tolerancia: ${weld.tolerance}</h3>
		<h3>Estado: ${weld.state}</h3>
	</div>
	<div class="d-inline-flex justify-content-center">
		<span> <c:if test="${weld.state == 'CREADA'}">
				<a href="/iniciarSoldadura/${weld.id}"
					class=" btn btn-success active mt-5" role="button"
					aria-pressed="true"><i class="bi bi-play-circle me-2"></i>Iniciar
					Soldadura</a>
			</c:if> <c:if test="${weld.state == 'INICIADA'}">
				<a href="/pararSoldadura/${weld.id}"
					class=" btn btn-danger active ms-5 mt-5" role="button"
					aria-pressed="true"><i class="bi bi-stop-circle me-2"></i>Parar
					Soldadura</a>
			</c:if></span>
	</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>