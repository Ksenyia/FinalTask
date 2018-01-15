<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Список фильмов</title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<jsp:useBean id = "movies" scope="session" class="by.tr.web.entity.Movie" type="java.lang.Object"></jsp:useBean>
	<header class="fon1">
		<h1 class="head">Кино-рейтинг</h1>
		<div id="anim">
			<img id="animation" src="img/cat.png" alt="Picture not found">
		</div>
		<nav>
			<a class="navig" href="#">Главная</a> <a class="navig" href="#">Профиль</a>|
			<a class="navig" href="#">Выйти</a>|
			<div class="dropdown">
				<button class="dropbtn">Русский</button>
				<div class="dropdown-content">
					<a href="#">Русский</a> <a href="#">English</a>
				</div>
			</div>
		</nav>
	</header>
	<aside class="fon1">
		<div>
			<form action="Controller" method="post">
			<input type="hidden" name="command" value ="search"> 
				Найти:.<input type="text" name="search" placeholder="Search.."><br>
				Сортироать по: <select size="1" name="sel1">
					<option value="rating">Рейтинг</option>
					<option value="year">Год выпуска</option>
					<option value="title">Название</option>
				</select><br> <input type="radio"> Полнометражный<br> <input
					type="radio"> Краткометражный<br> <input type="radio">
				Сериал<br> <input type="radio"> Мини-сериал<br>
				<br> Выбрать жанр: <select size="4" multiple>
					<option>Приключение</option>
					<option>Фентези</option>
					<option>Фантастика</option>
					<option>Комедия</option>
				</select><br> <input class="button" type="reset" value="Сброс"
					name="just_reset_1"> <input class="button" type="submit"
					value="Поиск">
			</form>
		</div>
	</aside>

	<article>
	<input type="hidden" name="command" value ="users">
	<form class="users-form" action="Controller" method="post">
		<table>
			<caption class="head">Users</caption>
			<thead>
				<tr>
					<th>Login</th>
					<th>E-mail</th>
					<th>Admin_Flag</th>
					<th>Status</th>
					<th>Acsess</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			  <c:set var="begin" scope="session" value="${1}"/>
  			  <c:set var="end" scope="session" value="${11}"/>
			  <c:forEach var="user" items = "${users}">
				<tr>
					<td><a href="#"><c:out value = "${user.getLogin()}"></c:out> </a></td>
					<td>${user.getEmail()}</td>
					<td>
						<div class="flags">
							<input type="number" name="status#${user.getId()}" value = "${user.getStatus()}">
						</div>
					</td>
					<td>
						<div class="flags">
							<c:choose>
							<c:when test="${user.isAccessFlag()}">
								<input type="checkbox" id = "сheck${user.getId()}" name="access#${user.getId()}" value = "${true}" checked="checked">
								<input type="hidden" id = "сheck${user.getId()}" name="access#${user.getId()}" value = "${false}">
							</c:when>
							<c:otherwise>
								<input type="checkbox" id = "сheck${user.getId()}" name="access#${user.getId()}" value = "${true}">
								<input type="hidden" id = "сheck${user.getId()}" name="access#${user.getId()}" value = "${false}">
							</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td>
					</td>
				</tr>
				</c:forEach> 
			</tbody>
		</table>
			<input type="hidden" name="command"  value ="editUsers">
			<input type="submit" value="submit">
		</form>
		<div class="pagination">
			<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a>
			<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
			<a href="#">&raquo;</a>
		</div>
	</article>

	<footer>
		<p>Акула Ксения</p>
		<p>
			Контактная информация: <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
		</p>
	</footer>
</body>
<script>
function myFunction(id) {
	//onclick="myFunction(${user.getId()})"
    var x = document.getElementById("сheck"+id).checked;
    if(x){
    	document.getElementById("сheck"+id).value = true;
    }
    if(!x){
    	document.getElementById("сheck"+id).value = false;
    }
}
</script>
</html>


