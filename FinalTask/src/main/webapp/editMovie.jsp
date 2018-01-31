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
			<a class="navig" href="login.jsp"><fmt:message bundle="${loc}" key="local.login"/></a>|
			<a class="navig" href="Controller?command=users"><fmt:message bundle="${loc}" key="local.user"/></a>|
	<div class="dropdown">
		<fmt:message bundle="${loc}" key="local"/> <br />
		<div class="dropdown-content">
			<a href="<c:url value="Controller?command=editMovie"> 
			<c:param name="local" value="ru"/>
			<c:param name="page" value="path.page.movie"/></c:url>">
			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
			<a href="<c:url value="Controller?command=editMovie"> 
			<c:param name="local" value="en"/>
			<c:param name="page" value="path.page.movie"/>
			</c:url>"><fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
		</div>
	</div>
	</nav> 
	</header>
	<article>
	<h1 class="head"><jsp:getProperty property="title" name="movie"/></h1>
	<c:set var="movieId" scope="session" value="${movie.getId()}" />
	<p><jsp:getProperty property="title" name="movie"/></p>
	<p><jsp:getProperty property="type" name="movie"/></p>
	<p><jsp:getProperty property="year" name="movie"/></p>
	<p><jsp:getProperty property="rating" name="movie"/></p>
	<p><jsp:getProperty property="director" name="movie"/></p>
	<p><jsp:getProperty property="discription" name="movie"/></p>
	<p><jsp:getProperty property="genres" name="movie"/></p>
	<p><jsp:getProperty property="countries" name="movie"/></p>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="sendChanges" />
		<p> RU tittle </p>
		<input type="text" name="titleRU" value="${movieRU.getTitle()}" required/>
		<p> EN tittle </p>
		<input type="text" name="titleEN" value="${movieEN.getTitle()}"/>
		<input type="text" name="year" value="${movieRU.getYear()}" />
		<p> RU directore </p>
		<input type="text" name="directorRU" value="${movieRU.getDirector()}" />
		<p> EN directore </p>
		<input type="text" name="directorEN" value="${movieEN.getDirector()}" />
		<p> RU discription </p>
		<textarea rows="5" cols="15" name="discriptionRU">${movieRU.getDiscription()}</textarea>
		<p> EN discription </p>
		<textarea rows="5" cols="15" name="discriptionEN">${movieEN.getDiscription()}</textarea>
		<select id="types" name = "typeRU" onclick="newSelect('types','typeRU','newTypeRU')">
			<c:forEach var="type" items = "${typesRU}">
			<c:choose>
			<c:when test="${movieRU.getType() eq type} ">
		  	  <option value = "${type}" selected="selected">${type}</option>
		  	 </c:when>
		  	 <c:otherwise>
		  	 <option value = "${type}">${type}</option>
		  	 </c:otherwise>
		  	  </c:choose>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newTypeRU"></p>
				<select id="types" name = "typeEN" onclick="newSelect('types','typeEN','newTypeEN')">
			<c:forEach var="type" items = "${typesEN}">
			<c:choose>
			<c:when test="${movieEN.getType() eq type} ">
		  	  <option value = "${type}" selected="selected">${type}</option>
		  	 </c:when>
		  	 <c:otherwise>
		  	 <option value = "${type}">${type}</option>
		  	 </c:otherwise>
		  	  </c:choose>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newTypeEN"></p>
		<c:set var = "size"  value = "${movieRU.getGenres().size()}"></c:set>
		<select id="genres" name = "genre" onchange="newSelects('genres','genre','newGenre')" multiple>
			<c:forEach var="genre" items = "${genres}">
			  <c:set var = "flag"  value = "${true}"></c:set>
			  <c:set var = "count"  value = "${0}"></c:set>
			  <c:choose>
			 <c:when test="${movieRU.getGenres().size()!=0}">
			  <c:forEach var="movieGenre" items = "${movieRU.getGenres()}">
			  <c:set var = "count"  value = "${count+1}"></c:set>
			    <c:choose>
			      <c:when test="${genre.value eq movieGenre}">
			        <option value = "${genre.key}" selected="selected">${genre.value}</option>
			        <c:set var = "flag"  value = "${false}"></c:set>
			      </c:when>
			      <c:otherwise>
			  	    <c:if test="${(count==size)&&flag}">
		  	          <option value = "${genre.key}">${genre.value}</option>
		  	          <c:set var = "flag"  value = "${false}"></c:set>
		  	        </c:if>
		  	      </c:otherwise>
		  	    </c:choose>
		  	  </c:forEach>
		  	  </c:when>
		  	  <c:otherwise>
		  	  <option value = "${genre.key}">${genre.value}</option>
		  	  </c:otherwise>
		  	 </c:choose>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newGenre"></p>
		<c:set var = "size"  value = "${movieRU.getCountries().size()}"></c:set>
		<select id="countries" name= "country" onchange="newSelects('countries','country','newCountry')"  multiple>
			<c:forEach var="country" items = "${countries}">
			  <c:set var = "flag"  value = "${true}"></c:set>
			  <c:set var = "count"  value = "${0}"></c:set>
			 <c:choose>
			 <c:when test="${movieRU.getCountries().size()!=0}">
			<c:forEach var="movieCountry" items = "${movieRU.getCountries()}">
			<c:set var = "count"  value = "${count+1}"></c:set>
			<c:choose>
			<c:when test="${country.value eq movieCountry}">
			<c:if test="${(count==size)&&flag}">
			<option value = "${country.key}" selected="selected">${country.value}</option>
			</c:if>
            <c:set var = "flag"  value = "${false}"></c:set>
			</c:when>
			<c:otherwise>
			<option value = "${country.key}">${country.value}</option>
			</c:otherwise>
		  	  </c:choose>
		  	  </c:forEach>
		  	  </c:when>
		  	  <c:otherwise>
		  	  <option value = "${country.key}">${country.value}</option>
		  	  </c:otherwise>
		  	  </c:choose>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other">${other}</option>
		</select>
		<p id = "newCountry"></p>
		<fmt:message bundle="${loc}" key="local.reset" var= "reset"/>
		<input type="reset" value = "${reset}" /> 
		<fmt:message bundle="${loc}" key="local.edit" var= "edit"/>
		<input type="submit" value="${edit}" /> 
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
