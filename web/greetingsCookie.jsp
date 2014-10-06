<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Greetings Application</title>
    </head>    
    <body>
        <b> Hello, ${cookie.friendOrStranger.value} </b>
        <br>
 		<form action="/hello-user-jsp/cookie" method="POST">
            Enter your name:<br />
            <input type="text" name="user" /><br />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
