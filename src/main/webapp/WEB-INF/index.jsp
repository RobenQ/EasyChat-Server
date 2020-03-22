<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>SPOC-FORUM</title>
		<meta charset="utf-8"/>
		<link rel="stylesheet" href="css/index.css" />
	</head>
	<body>
		<div id="div1">
			<span id="span1">用户登录</span>
		</div>
		<div id="div2">
			<div class="div2_post">
				<form action="/forum/user/login" name="form" method="post">
					昵称：<input class="post" type="text" name="name" id="name" /><br />
					密码：<input class="post" type="password" name="pwd" id="pwd" /><br>
					<input class="post" type="submit" name="submit" id="submit" value="登录" />
				</form>
			</div>
		</div>
	</body>
</html>
