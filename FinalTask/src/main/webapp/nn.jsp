<!DOCTYPE html>

<head>
<meta charset="utf-8">
<title>Список фильмов</title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<div class="header">
		<fmt:message key="faculty.list_jsp.label.faculties_list" />
	</div>

	<table id="facultiesTable" class="display">
		<thead>
			<tr>
				<td><fmt:message key="faculty.list_jsp.label.name" /></td>
				<td><fmt:message key="faculty.list_jsp.label.total_seats" /></td>
				<td><fmt:message key="faculty.list_jsp.label.budget_seats" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="faculty" items="${faculties}">
				<tr>
					<td><a
						href="<c:url value="controller?command=viewFaculty"> <c:param name="name_eng" value="${faculty.nameEng}"/></c:url>">
							<c:out
								value="${language eq 'ru' ? faculty.nameRu : faculty.nameEng}"></c:out>
					</a></td>
					<td><c:out value="${faculty.totalSeats}"></c:out></td>
					<td><c:out value="${faculty.budgetSeats}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		var language = "${language}";
		$(document).ready(function() {
			$('#facultiesTable').dataTable({
				"language" : {
					"url" : (language == 'ru') ? "script/russian.lang" : "",
				}
			});
		}); 
	</script>
</body>
</html>