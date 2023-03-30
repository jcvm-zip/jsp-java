package com.example.jspJava.filter;

import com.example.jspJava.connection.SingleConnectionBanco;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {

    private static Connection connection;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        connection = SingleConnectionBanco.getConnection();

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();

            String usuarioLogado = (String) session.getAttribute("usuario");

            String urlAutenticacao = request.getServletPath();

            if (usuarioLogado == null && !urlAutenticacao.equalsIgnoreCase("/principal/ServletLogin")) {

                RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticacao);
                request.setAttribute("msg", "Por Favor, Realize o Login!");
                redireciona.forward(request,servletResponse);
                return;
            } else {
                filterChain.doFilter(request, servletResponse);
            }

            connection.commit();

        } catch ( Exception e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
