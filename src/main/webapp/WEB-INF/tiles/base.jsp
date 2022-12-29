<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!doctype html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Font Icon CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<!-- Datatable CSS -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>WeldTIC</title>

</head>
<body>

	<tiles:insertAttribute name="header" />
	<main class="flex-shrink-0">
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
	</main>

	<tiles:insertAttribute name="footer" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		<script src="/js/weldtic.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>

</body>
</html>