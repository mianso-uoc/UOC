<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<header class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="/inicio"><i class="bi bi-wrench"></i>
			WeldTIC</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<security:authorize access="isAuthenticated()">
				<security:authentication var="tipo" property="principal.tipo" />
				
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<!-- 					<li class="nav-item"><a class="nav-link active"	aria-current="page" href="#">Inicio</a></li> -->
					<li class="nav-item"><a class="nav-link" href="/company">Empresas</a></li>
					<security:authorize access="hasAnyRole('User')">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Usuario </a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="/user">Ver usuarios</a></li>
									<li><a class="dropdown-item" href="/crearUser">Crear
											administrador</a></li>
									<li><a class="dropdown-item" href="/crearSoldador">Crear
											soldador</a></li>
									<li><a class="dropdown-item" href="/crearResponsable">Crear
											responsable</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="/logout">Eliminar
											usuarios</a></li>
								</ul>
						</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Empresa </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/company">Ver
									empresas</a></li>
							<li><a class="dropdown-item" href="/crearEmpresa">Crear
									empresa</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/logout">Eliminar
									empresas</a></li>
						</ul></li>
					</security:authorize>
				</ul>
				<a role="button" class="btn btn-primary position-relative me-3" href="/alarma">
  <i class="bi bi-bell text-warning"></i>
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    99+
    <span class="visually-hidden">unread messages</span>
  </span>
  </a>
				<div class="form-inline my-2 my-lg-0">
				
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"><c:out value="${tipo}"></c:out><i
								class="bi bi-person-circle ms-2"></i> <security:authentication
									property="principal.username" /></a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/logout">Salir</a></li>
							</ul></li>
					</ul>
				</div>
			</security:authorize>
		</div>
	</div>
</header>