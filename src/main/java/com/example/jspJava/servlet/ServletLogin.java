package com.example.jspJava.servlet;

import com.example.jspJava.model.ModelLogin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/ServletLogin", "/principal/ServletLogin"}) /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 8051347591106763233L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty())  {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            RequestDispatcher redirecionar;
            if(modelLogin.getLogin().equalsIgnoreCase("admin")
                    && modelLogin.getSenha().equalsIgnoreCase("admin")) {

                request.getSession().setAttribute("usuario", modelLogin);/*Coloca o user na sessao*/

                if (url == null || url.equals("null")) {
                    url = "principal/principal.jsp";
                }
                redirecionar = request.getRequestDispatcher(url);
                redirecionar.forward(request, response);


            } else {
                redirecionar = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg","Usuário ou senha incorretos!");
            }
            redirecionar.forward(request, response);

        } else {
            RequestDispatcher nao_redirecionar = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg","Informe o login e senha corretamente!");
            nao_redirecionar.forward(request, response);
        }

    }
}
