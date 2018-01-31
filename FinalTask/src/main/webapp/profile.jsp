<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<title><fmt:message bundle="${loc}" key="local.page.register.title"/></title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="formstyle.css">
</head>
<body>
<body>
	<header class="fon1">
		<h1 class="head">
			<fmt:message bundle="${loc}" key="local.site.name"/>
		</h1>
		<img id="animation" src="img/dog.png" alt="Picture not found"> <img
			id="animation2" src="img/cat2.png" alt="Picture not found">
		<nav>
			<a class="navig" href="Controller?command=movies">
			<fmt:message bundle="${loc}" key="local.home"/></a>|
	 		<a class="navig" href="login.jsp">
	 		<fmt:message bundle="${loc}" key="local.login"/></a>|
			<div class="dropdown">
				<fmt:message bundle="${loc}" key="local"/> <br />
				<div class="dropdown-content">
					<a href="<c:url value="prfile.jsp">
					<c:param name="local" value="ru"/></c:url>">
					<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
					<a href="<c:url value="prfile.jsp"> 
					<c:param name="local" value="en"/>
					<c:param name="page" value="path.page.register"/></c:url>">
					<fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
				</div>
			</div>
		</nav>
	</header>
	<article>
		<h1 class="head"><fmt:message bundle="${loc}" key="local.site.name"/></h1>
		<form id="form" onsubmit="return validateForm()">
			<p>First name</p>
			<input type="text" name="fname" value = "${user.getName() }" >
			<p>Last name</p>
			<input type="text" name="lname" value = "${user.getSurname() }" >
			<p><fmt:message bundle="${loc}" key="local.login"/></p>
			<input type="text" name="usrname" value = "${user.getLogin() }" title="Fill field obligatory">
			<span class="err" id="err-uname"></span>
			<p>e-mail</p>
			<div id="emails">
				<input type="email" name= "email"  id="email" value ="${user.getEmail()}" autocomplete="off"><span
					class="err" id='emailf'></span><br>
				<button onclick="Add()">Add</button>
			</div>
			<input type="hidden" name="command" value="changeProfile" />
			<input type="submit" name="changeProfile" value="Change">
			</form>
			<form onsubmit="return validateForm()">
			<p><fmt:message bundle="${loc}" key="local.password"/></p>
			<input type="password" name="pwd1"><span class="err"
				id="err-pwd1"></span><br>
			<p><fmt:message bundle="${loc}" key="local.repeat.password"/></p>
			<input type="password" name="pwd2"><span class="err"
				id="err-pwd2"></span><br>
				<input type="hidden" name="command" value="changePassword" />
				<input type="submit" name="changePassword" value="Change Password">
		</form>
	</article>
	<footer>
	<p><fmt:message bundle="${loc}" key="local.footer.name"/></p>
	<p>
		<fmt:message bundle="${loc}" key="local.footer.contact"/> <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
	</p>
	</footer>
	<script src="formjs.js"></script>
</body>
</html>
