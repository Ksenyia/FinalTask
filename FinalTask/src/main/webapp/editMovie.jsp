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
		<input type="hidden" name="command" value="sendChanges" />
		<input type="hidden" name="button" value="login" />
		<input type="text" name="title" value="${movie.getTitle()}" required/>
		<input type="text" name="year" value="${movie.getYear()}" />
		<input type="text" name="director" value="${movie.getDirector()}" />
		<textarea rows="5" cols="15" name="discription">${movie.getDiscription()}</textarea>
		<select id="types" name = "type" onclick="newSelect('types','type','newType')">
			<c:forEach var="type" items = "${types}">
			<c:choose>
			<c:when test="${movie.getType() eq type} ">
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
		<p id = "newType"></p>
		<c:set var = "size"  value = "${movie.getGenres().size()}"></c:set>
		<select id="genres" name = "genre" multiple>
			<c:forEach var="genre" items = "${genres}">
			  <c:set var = "flag"  value = "${true}"></c:set>
			  <c:set var = "count"  value = "${0}"></c:set>
			  <c:forEach var="movieGenre" items = "${movie.getGenres()}">
			  <c:set var = "count"  value = "${count+1}"></c:set>
			    <c:choose>
			      <c:when test="${genre eq movieGenre}">
			        <option value = "${genre}" selected="selected">${genre}</option>
			        <c:set var = "flag"  value = "${false}"></c:set>
			      </c:when>
			      <c:otherwise>
			  	    <c:if test="${(count==size)&&flag}">
		  	          <option value = "${genre}">${genre}</option>
		  	          <c:set var = "flag"  value = "${false}"></c:set>
		  	        </c:if>
		  	      </c:otherwise>
		  	    </c:choose>
		  	  </c:forEach>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other" onclick="newSelects('genres','genre','newGenre')">${other}</option>
		</select>
		<p id = "newGenre"></p>
		<c:set var = "size"  value = "${movie.getCountries().size()}"></c:set>
		<select id="countries" name= "country"  multiple>
			<c:forEach var="country" items = "${countries}">
			  <c:set var = "flag"  value = "${true}"></c:set>
			  <c:set var = "count"  value = "${0}"></c:set>
			<c:forEach var="movieCountry" items = "${movie.getCountries()}">
			<c:set var = "count"  value = "${count+1}"></c:set>
			<c:choose>
			<c:when test="${country eq movieCountry}">
			<c:if test="${(count==size)&&flag}">
			<option value = "${country}" selected="selected">${country}</option>
			</c:if>
            <c:set var = "flag"  value = "${false}"></c:set>
			</c:when>
			<c:otherwise>
			<option value = "${country}">${country}</option>
			</c:otherwise>
		  	  </c:choose>
		  	  </c:forEach>
		  	</c:forEach>
		  	<fmt:message bundle="${loc}" key="local.option.other" var= "other"/>
		  	<option value = "other" onclick="newSelects('countries','country','newCountry')">${other}</option>
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
</body>
<script>
function newSelect(x,y,z) {
   i = document.getElementById(x).selectedIndex;
   l = document.getElementById(x).length;
   var newInput = document.getElementById(y);
   if(i==(l-1)&&!newInput){
         Add(y,z);
         
    }
    else{
    	Delete(y,z);
    }
}

function newSelects(x,y,z) {
	   l = document.getElementById(x).length;
	   var newInput = document.getElementById(y);
	   if(!newInput){
	         Add(y,z);   
	    }
	   else{
		   Delete(y,z);
	   }
	   
	}

function Add(y,z) {
	var demo = document.getElementById(z);
    var newInput = document.createElement("INPUT");
    newInput.setAttribute("type", "text");
    newInput.setAttribute("id", y);
	newInput.setAttribute("autocomplete", "off");
    demo.appendChild(newInput);
}
function Delete(x,z) {
    document.getElementById(z).innerHTML = "";
	var demo = document.getElementById(z);
    var newInput = document.getElementById(y);
	if (newInput.parentNode) {
		demo.removeChild(newInput);
	}
}
</script>
</html>