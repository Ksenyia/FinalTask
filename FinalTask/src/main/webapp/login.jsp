<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<title><fmt:message bundle="${loc}" key="local.page.login.title"/></title>
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
	<div class="dropdown">
		<fmt:message bundle="${loc}" key="local"/> <br />
		<div class="dropdown-content">
			<a href="<c:url value="login.jsp"> 
			<c:param name="local" value="ru"/>
			<c:param name="page" value="path.page.login"/></c:url>">
			<fmt:message bundle="${loc}" key="local.locbutton.name.ru"/></a>
			<a href="<c:url value="login.jsp"> 
			<c:param name="local" value="en"/>
			<c:param name="page" value="path.page.login"/>
			</c:url>"><fmt:message bundle="${loc}" key="local.locbutton.name.en"/></a>
		</div>
	</div>
	</nav> 
	</header>
	<article>
	<h1 class="head"><fmt:message bundle="${loc}" key="local.page.login.title"/></h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="login" />
		<p><fmt:message bundle="${loc}" key="local.login"/></p>
		<input type="text" name="login" title="Fill field obligatory">
		<span class="err" id="err-uname"></span>
		<p><fmt:message bundle="${loc}" key="local.password"/></p>
		<input type="password" name="password">
		<span class="err" id="err-pwd1"></span><br>
		<fmt:message bundle="${loc}" key="local.login.button.name" var= "login"/>
		<input type="hidden" name="button" value="login" />
		<input type="submit" value="${login}" /> 
	</form>
	<form action="Controller" method="post">
		<fmt:message bundle="${loc}" key="local.regiser.button.name" var= "register"/>
		<input type="hidden" name="command" value="login" />
		<input type="hidden" name="button" value="Register" />
		<input type="submit" name="button" value="${register}"  /><br />
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
