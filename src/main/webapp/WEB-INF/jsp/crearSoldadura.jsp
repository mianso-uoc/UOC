<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${action == 'update'}">
	<h1>Modificar soldadura nº ${weld.id}</h1>
</c:if>
<c:if test="${action == 'new'}">
	<h1>Crear soldadura</h1>
</c:if>

<c:choose>
	<c:when test="${weld.state != 'CREADA'}">
		<c:set var="disable" value="true"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="disable" value="false"></c:set>
	</c:otherwise>
</c:choose>

<span class="badge bg-danger">${weld.alarm.info}</span>
<div class="card mt-1">
	<div class="card-header mb-3 bg-info">Rellena los datos
		requeridos</div>
	<div class="card-body">
		<form:form method="POST" action="/guardarSoldadura"
			modelAttribute="weld">
			<c:if test="${action == 'update'}">
				<td><h4>
						<span class="badge bg-success mb-3">${weld.state}</span>
					</h4>
			</c:if>

			<table>
				<c:if test="${action == 'update'}">
					<tr>
						<td><form:label path="id" class="mb-3">ID</form:label></td>
						<td><form:input path="id" class="mb-3" disabled="${disable}" /></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="amp" class="mb-3">Intensidad</form:label></td>

					<td><form:input path="amp" class="mb-3" required="required"
							disabled="${disable}" /> <form:errors path="amp"
							class="error text-danger" /></td>

				</tr>
				<tr>
					<td><form:label path="volt" class="mb-3">Voltaje</form:label></td>
					<td><form:input path="volt" class="mb-3" required="required"
							disabled="${disable}" /> <form:errors path="volt"
							class="error text-danger" /></td>
				</tr>
				<tr>
					<td><form:label path="tolerance" class="mb-3">Tolerancia</form:label></td>
					<td><form:input path="tolerance" class="mb-3"
							required="required" disabled="${disable}" /> <form:errors
							path="tolerance" class="error text-danger" /></td>
				</tr>
				<tr>
					<td><form:label path="note" class="mb-3">Observaciones</form:label></td>
					<td><form:input path="note" class="mb-3" disabled="${disable}" /></td>
				</tr>
				<tr>
					<td><form:hidden path="piece.id" class="mb-3" /></td>
				</tr>
				<tr>
					<td><form:hidden path="state" class="mb-3" /></td>
				</tr>
				<tr>
					<%-- 					<td><form:label path="state" class="form-label mb-3">Estado</form:label></td> --%>
					<%-- 					<td><form:select path="state" class="form-select mb-3"> --%>
					<%-- 							<c:forEach items="${weldStatus}" var="status"> --%>
					<%-- 								<form:option value="${status}"></form:option> --%>
					<%-- 							</c:forEach> --%>

					<%-- 						</form:select></td> --%>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${weld.state != 'CREADA' }">
							<td><a href="/verPieza/${weld.piece.id}"
								class=" btn btn-primary" role="button" aria-pressed="true">Volver</a></td>
						</c:when>
						<c:otherwise>
							<td><input class="btn btn-primary mb-3" type="submit"
								value="Guardar"></td>
							<td><a href="/verPieza/${weld.piece.id}"
								class=" btn btn-primary mb-3" role="button" aria-pressed="true">Volver</a></td>
						</c:otherwise>
					</c:choose>
				</tr>

			</table>
		</form:form>
	</div>

	<c:if test="${fn:length(readings) > 0 }">

		<div class="row">
			<div class="col-10">
				<canvas id="myChart" style="margin-bottom: 50px;" width="700"
					height="220"></canvas>
				<canvas id="myChartAmp" width="700" height="220"></canvas>

			</div>
		</div>


		<script>
			$(document).ready(function() {

				const ctx = document.getElementById('myChart');
				const myChart = new Chart(ctx, {
					type : 'line',
					data : {
						datasets : [ {
							label : 'Voltaje',
							data : [ ${datosVolt} ],
							backgroundColor : [ '#90cd8a' ],
							borderColor : [ '#90cd8a' ],
							borderWidth : 4,
							pointStyle : 'circle'
						},{
							label : 'Límite superior',
							data : [ ${datosVoltMax}],
							backgroundColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderDash : [ 10, 5 ],
							borderWidth : 1
						}, {
							label : 'Límite inferior',
							data : [${datosVoltMin}],
							backgroundColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderDash : [ 10, 5 ],
							borderWidth : 1
						} ]
					},
					options : {
						scales : {
							x : {
								type : 'time',
								time : {
									unit : 'second',
									tooltipFormat : 'yyyy/MM/dd hh:mm:ss',
									displayFormats : {second: 'HH:mm:ss'}
								}
							},
					y : {
		                suggestedMin: ${voltMin},
		                suggestedMax: ${voltMax}
			            }
						},
						plugins:{
	                title: {
	                    display: true,
	                    text: 'Voltaje'
	                }
						}
					}
				});
				
				const ctxAmp = document.getElementById('myChartAmp');
				const myChartAmp = new Chart(ctxAmp, {
					type : 'line',
					data : {
						datasets : [ {
							label : 'Intensidad',
							data : [ ${datosAmp} ],
							backgroundColor : [ '#03396c' ],
							borderColor : [ '#03396c' ],
							borderWidth : 4,
							pointStyle : 'circle'
						},{
							label : 'Límite superior',
							data : [ ${datosAmpMax}],
							backgroundColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderDash : [ 10, 5 ],
							borderWidth : 1
						}, {
							label : 'Límite inferior',
							data : [${datosAmpMin}],
							backgroundColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderColor : [ 'rgba(0, 0, 0, 0.3)' ],
							borderDash : [ 10, 5 ],
							borderWidth : 1
						} ]
					},
					options : {
						scales : {
							x : {
								type : 'time',
								time : {
									unit : 'second',
									tooltipFormat : 'yyyy/MM/dd hh:mm:ss',
									displayFormats : {second: 'HH:mm:ss'}
									
								}
							},
							y : {
				                suggestedMin: ${ampMin},
				                suggestedMax: ${ampMax}
					            }
						},
						plugins:{
		                title: {
		                    display: true,
		                    text: 'Intensidad'
		                }
						}
					}
				});
			});
		</script>
	</c:if>
</div>