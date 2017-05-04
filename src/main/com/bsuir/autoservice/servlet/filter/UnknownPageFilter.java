package main.com.bsuir.autoservice.servlet.filter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.binding.annotation.ErrorJspPage;

import javax.servlet.*;
import java.io.IOException;

@Singleton
public class UnknownPageFilter implements Filter{
    private String jspPage;

    @Inject
    public void NoFilter(@ErrorJspPage String errorPageJsp){
        this.jspPage = errorPageJsp;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getRequestDispatcher(jspPage).forward(servletRequest, servletResponse);
    }
}
