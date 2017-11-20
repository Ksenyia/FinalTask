<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<body>
<hr/>
<form action="Controller" method = "post">
	<input type = "hidden" name = "command" value = "naming" /><br/>Введите имя:
	<input type = "text" name = "name" value = "" /><br/>Введите фамилию:
	<input type = "text" name = "surname" value = "" /><br/>Введите e-mail:
	<input type = "text" name = "e-mail" value = "" /><br/>Введите пароль:	
	<input type = "text" name = "passward" value = "" /><br/>
	<input type="submit" value="register"/><br/>
</form>
</body>
</html>
