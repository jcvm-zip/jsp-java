package com.example.jspJava.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

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
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
