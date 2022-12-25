<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<h1>Valores de la soldadura ${weld.id}</h1>

<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Información de la soldadura</div>
	<div class="card-body">
		<h2>Intensidad: ${weld.amp}</h2>
		<h2>Voltaje: ${weld.volt}</h2>
		<h2>Tolerancia: ${weld.tolerance}</h2>
		<h2>Estado: ${weld.state}</h2>
	</div>
	<div class="d-inline-flex justify-content-center">
<span>
	<a href="/verSoldadura/${weld.id}" class=" btn btn-success active mt-5"
		role="button" aria-pressed="true"><i class="bi bi-play-circle me-2"></i>Iniciar Soldadura</a>
			<a href="/verSoldadura/${weld.id}" class=" btn btn-danger active ms-5 mt-5"
		role="button" aria-pressed="true"><i class="bi bi-stop-circle me-2"></i>Parar Soldadura</a></span>
		</div>
	<div class="col-md-6">
		<canvas id="myChart"></canvas>
	</div>

</div>