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
<fmt:setBundle basename="localization.locale" var="loc" />
<jsp:useBean id = "movie" class="by.tr.web.entity.Movie" type = "java.lang.Object" scope = "session" ></jsp:useBean>
<jsp:useBean id = "user" class="by.tr.web.entity.User" type = "java.lang.Object" scope = "session" ></jsp:useBean>
<title><jsp:getProperty property="title" name="movie"/></title>
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
			<a class="navig" href="Controller?command=login"><fmt:message bundle="${loc}" key="local.login"/></a>|
			<a class="navig" href="Controller?command=users"><fmt:message bundle="${loc}" key="local.user"/></a>|
	<div class="dropdown">
		<fmt:message bundle="${loc}" key="local"/> <br />
		<div class="dropdown-content">
			<a href="<c:url value="Controller?command=local"> 
			<c:param name="local" value="ru"/>
			<c:param name="page" value="path.page.movie"/></c:url>">
			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
			<a href="<c:url value="Controller?command=local"> 
			<c:param name="local" value="en"/>
			<c:param name="page" value="path.page.movie"/>
			</c:url>"><fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
		</div>
	</div>
	</nav> 
	</header>
	<article>
	<h1 class="head"><jsp:getProperty property="title" name="movie"/></h1>
	<c:set var="movieId" scope="request" value="${movie.getId()}" />
	<p><jsp:getProperty property="title" name="movie"/></p>
	<p><jsp:getProperty property="type" name="movie"/></p>
	<p><jsp:getProperty property="year" name="movie"/></p>
	<p><jsp:getProperty property="rating" name="movie"/></p>
	<p><jsp:getProperty property="director" name="movie"/></p>
	<p><jsp:getProperty property="discription" name="movie"/></p>
	<p><jsp:getProperty property="genres" name="movie"/></p>
	<p><jsp:getProperty property="countries" name="movie"/></p>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="editMovie" />
		<input type="hidden" name="button" value="login" />
		<fmt:message bundle="${loc}" key="local.edit" var= "edit"/>
		<input type="submit" value="${edit}" /> 
	</form>
		<mytag:review reviews="${map}"/>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="reviw" />
		<input type="hidden" name="button" value="Register" />
		<input type="text" name="review" value =""/><br/>
		<fmt:message bundle="${loc}" key="local.review" var= "review"/>
		<input type="submit" name="button" value="${review}"  /><br />
	</form>
	</article>
	<footer>
	<p><fmt:message bundle="${loc}" key="local.footer.name"/></p>
	<p>
		<fmt:message bundle="${loc}" key="local.footer.contact"/> <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
	</p>
	</footer> 
</body>
</html>
