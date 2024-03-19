<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login to continue</h1>
        <form action="main" method="POST">
            <p>${ERROR_MESSAGE}</p>
            UserID <input type="text" name="userid" required=""/>
            Password <input type="password" name="password" required/>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
