<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<link rel="icon" href="img/movie_night.jpg" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="formstyle.css">
</head>
<body>
<body>
	<header class="fon1">
		<h1 class="head">Movie-rating</h1>
		<img id="animation" src="img/dog.png" alt="Picture not found"> <img
			id="animation2" src="img/cat2.png" alt="Picture not found">
		<nav>
			<a class="navig" href="#">Home</a>| <a class="navig" href="#">Login</a>|
			<div class="dropdown">
				<button class="dropbtn">English</button>
				<div class="dropdown-content">
					<a href="#">Русский</a> <a href="#">English</a>
				</div>
			</div>
		</nav>
	</header>
	<article>
		<h1 class="head">Registration</h1>
		<form id="form" onsubmit="return validateForm()">
			<p>First name</p>
			<input type="text" name="fname">
			<p>Last name</p>
			<input type="text" name="lname">
			<p>Username</p>
			<input type="text" name="usrname" title="Fill field obligatory">
			<span class="err" id="err-uname"></span>
			<p>e-mail</p>
			<div id="emails">
				<input type="email" name= "email"  id="email" autocomplete="off"><span
					class="err" id='emailf'></span><br>
				<button onclick="Add()">Add</button>
			</div>
			<p>Password</p>
			<input type="password" name="pwd1"><span class="err"
				id="err-pwd1"></span><br>
			<p>Repeat password</p>
			<input type="password" name="pwd2"><span class="err"
				id="err-pwd2"></span><br>
				<input type="hidden" name="command" value="register" />
				 <input type="submit" name="register" value="Register">
		</form>
	</article>
	<footer>
		<p>Kseniya Akula</p>
		<p>
			Contact information: <a href="mailto:ksenea100@gmail.com">ksenea100@gmail.com</a>.
		</p>
	</footer>
	<script src="formjs.js"></script>
</body>
</html>
