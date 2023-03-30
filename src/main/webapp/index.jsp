<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Página de Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 21px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type=text], input[type=password] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>


    <form action="ServletLogin" method="post">
    <input type="hidden" value="<%= request.getParameter("url") %>" name="url">

    <table>
        <tr>
            <td>
                <h1>Bem vindo ao login</h1>
            </td>
        </tr>
        <tr>
            <td>
                <label for="login">Login:</label>
                <input type="text" id="login" name="login" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Entrar">
            </td>
        </tr>
    </table>
</form>

    <h4>${msg}</h4>
</body>
</html>
