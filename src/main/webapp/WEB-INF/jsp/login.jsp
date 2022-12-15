<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container">
	<form method="POST" action="${contextPath}/login" class="form-signin">
		<h1 class="form-heading mb-3">Log in</h1>

		<div class="form-group ${error != null ? 'has-error' : ''}">
			<span>${message}</span>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email address</label> 
				<input type="email" name="email" class="form-control">
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input type="password" name="password" class="form-control">
			</div>
			<span>${error}</span> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Acceder</button>
		</div>
	</form>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/inicio"></script>
