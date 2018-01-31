<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.locale" var="loc"/>
<title><fmt:message bundle="${loc}" key="local.page.add.title"/></title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="formstyle.css">
</head>
<body>
	<hr/>
	<header class="fon1">
	<img id="animation" src="img/dog.png" alt="Picture not found">
	<img id="animation2" src="img/cat2.png" alt="Picture not found"> 
	<h1 class="head">
		<fmt:message bundle="${loc}" key="local.site.name"/>
	</h1>
	<nav>
			<a class="navig" href="Controller?command=movies"><fmt:message bundle="${loc}" key="local.home"/></a>|
			<a class="navig" href="login.jsp"><fmt:message bundle="${loc}" key="local.login"/></a>|
			<a class="navig" href="Controller?command=users"><fmt:message bundle="${loc}" key="local.user"/></a>|
	<div class="dropdown">
		<fmt:message bundle="${loc}" key="local"/> <br />
		<div class="dropdown-content">
			<a href="<c:url value="Controller?command=addMovie"> 
			<c:param name="local" value="ru"/>
			<c:param name="page" value="path.page.movie"/></c:url>">
			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
			<a href="<c:url value="Controller?command=addMovie"> 
			<c:param name="local" value="en"/>
			<c:param name="page" value="path.page.movie"/>
			</c:url>"><fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
		</div>
	</div>
	</nav> 
	</header>
	<article>
	<h1 class="head"><fmt:message bundle="${loc}" key="local.page.add.title"/></h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="sendAdd" />
		<p> RU tittle </p>
		<input type="text" name="titleRU" required/>
		<p> EN tittle </p>
		<input type="text" name="titleEN"/>
		<input type="text" name="year"/>
		<p> RU directore </p>
		<input type="text" name="directorRU"/>
		<p> EN directore </p>
		<input type="text" name="directorEN"/>
		<p> RU discription </p>
		<textarea rows="5" cols="15" name="discriptionRU"></textarea>
		<p> EN discription </p>
		<textarea rows="5" cols="15" name="discriptionEN"></textarea>
		<select id="types" name = "typeRU" onclick="newSelect('types','typeRU','newTypeRU')">
			<c:forEach var="type" items = "${typesRU}">
		  	  <option value = "${type}">${type}</option>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newTypeRU"></p>
		<select id="types" name = "typeEN" onclick="newSelect('types','typeEN','newTypeEN')">
			<c:forEach var="type" items = "${typesEN}">
		  	  <option value = "${type}">${type}</option>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newTypeEN"></p>
		<select id="genres" name = "genre" onchange="newSelects('genres','genre','newGenre')" multiple>
			<c:forEach var="genre" items = "${genres}">
		  	  <option value = "${genre.key}">${genre.value}</option>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newGenre"></p>
		<select id="countries" name= "country" onchange="newSelects('countries','country','newCountry')" multiple>
			<c:forEach var="country" items = "${countries}">
		  	  <option value = "${country.key}">${country.value}</option>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newCountry"></p>
		<fmt:message bundle="${loc}" key="local.reset" var= "reset"/>
		<input type="reset" value = "${reset}" /> 
		<fmt:message bundle="${loc}" key="local.add" var= "add"/>
		<input type="submit" value="${add}" /> 
	</form>
	</article>
	<footer>
	<p><fmt:message bundle="${loc}" key="local.footer.name"/></p>
	<p>
		<fmt:message bundle="${loc}" key="local.footer.contact"/> <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
	</p>
	</footer>
	<script src="text-${sessionScope.local}.js"></script>	
	<script src="editMovie.js"></script> 
</body>
</html>
