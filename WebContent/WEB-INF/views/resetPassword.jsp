<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form:form action="resetPassword" method="POST" commandName="user">
            <table border = "0">
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>

                <tr>
                    <td>Confirm Password:</td>
                    <td><form:password path="confirmPassword" /></td>
                    <td><form:errors path="confirmPassword" cssClass="error"/></td>
                </tr>

                <tr>
                    <td colspan="3" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
            <form:hidden path ="email" value="${user.email}" />
            <form:hidden path ="tokenValue" value="${user.tokenValue}" />
        </form:form>
    </body>
</html>
