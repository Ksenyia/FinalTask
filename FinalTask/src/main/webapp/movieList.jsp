<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<title><fmt:message bundle="${loc}" key="local.page.movie.list.title"/></title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<jsp:useBean id = "movies" scope="session" class="by.tr.web.entity.Movie" type="java.lang.Object"></jsp:useBean>
	<header class="fon1">
		<h1 class="head">
		<fmt:message bundle="${loc}" key="local.site.name"/>
		</h1>
		<div id="anim">
			<img id="animation" src="img/cat.png" alt="Picture not found">
		</div>
		<nav>
			<a class="navig" href="Controller?command=movies"><fmt:message bundle="${loc}" key="local.home"/></a>|
			<a class="navig" href="Controller?command=login"><fmt:message bundle="${loc}" key="local.login"/></a>|
			<a class="navig" href="Controller?command=users"><fmt:message bundle="${loc}" key="local.user"/></a>|
			<div class="dropdown">
				<fmt:message bundle="${loc}" key="local"/> <br />
				<div class="dropdown-content">
					<a href="<c:url value="Controller?command=local"> 
					<c:param name="local" value="ru"/>
					<c:param name="page" value="path.page.main"/></c:url>">
					<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
					<a href="<c:url value="Controller?command=local"> 
					<c:param name="local" value="en"/>
					<c:param name="page" value="path.page.main"/>
					</c:url>"><fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
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
		<table>
			<caption class="head"><fmt:message bundle="${loc}" key="local.page.movie.list.title"/></caption>
			<thead>
				<tr>
					<th><fmt:message bundle="${loc}" key="local.movie.title"/></th>
					<th><fmt:message bundle="${loc}" key="local.movie.year"/></th>
					<th><fmt:message bundle="${loc}" key="local.movie.rating"/></th>
					<th><fmt:message bundle="${loc}" key="local.movie.your.rating"/></th>
				</tr>
			</thead>
			<tbody>
			  <c:set var="begin" scope="session" value="${1}"/>
  			  <c:set var="end" scope="session" value="${11}"/>
			  <c:forEach var="movie" items = "${movies}">
				<tr>
					<td><a href="<c:url value="Controller?command=movie"> <c:param name="movieId" value="${movie.getId()}"/></c:url>"><c:out value = "${movie.getTitle()}"></c:out></a> </td>
					<td>${movie.getYear()}</td>
					<td><fmt:formatNumber value = "${movie.getRating()}" type = "number"/></td>
					<td>
						<div class="ratings">
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${1}">
								<span data-descr="1">
								<input type="image" name="1" alt="1" src="img/Star.png" width="24" height="24">
								</span>
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${2}">							
								<span data-descr="2">
								<input type="image" name="2" alt="2" src="img/Star.png" width="24" height="24">
								</span>
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${3}">
								<span data-descr="3">
								<input type="image" name="3" alt="3" src="img/Star.png" width="24" height="24">
								</span>
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${4}">
								<span data-descr="4">
								<input type="image" name="4" alt="4" src="img/Star.png" width="24" height="24">
								</span>
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${5}">
								<span data-descr="5">
								<input type="image" name="5" alt="5" src="img/Star.png" width="24" height="24">
								</span> 
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${6}">
								<span data-descr="6">
								<input type="image" name="6" alt="6" src="img/Star.png" width="24" height="24">
								</span> 
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${7}">
								<span data-descr="7">
								<input type="image" name="7" alt="7" src="img/Star.png" width="24" height="24">
								</span>
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${8}"> 
								<span data-descr="8">
								<input type="image" name="8" alt="8" src="img/Star.png" width="24" height="24">
								</span> 
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${9}">
								<span data-descr="9">
								<input type="image" name="9" alt="9" src="img/Star.png" width="24" height="24">
								</span> 
							</form>
							<form class="ratings-form" action="Controller" method="post">
								<input type="hidden" name="command" value ="rating">
								<input type="hidden" name="movie" value ="${movie.getId()}">
								<input type="hidden" name="rating" value ="${10}">
								<span data-descr="10">
								<input type="image" name="10" alt="10" src="img/Star.png" width="24" height="24">
								</span>
							</form>
						</div>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="addMovie" />
			<fmt:message bundle="${loc}" key="local.review" var= "review"/>
			<input type="submit" name="button" value="${review}"  /><br />
		</form>
		<div class="pagination">
			<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a>
			<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
			<a href="#">&raquo;</a>
		</div>
	</article>

	<footer>
	<p><fmt:message bundle="${loc}" key="local.footer.name"/></p>
	<p>
		<fmt:message bundle="${loc}" key="local.footer.contact"/> <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
	</p>
	</footer>
</body>
</html>


