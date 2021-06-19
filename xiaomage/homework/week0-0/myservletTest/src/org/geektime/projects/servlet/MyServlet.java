package org.geektime.projects.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", urlPatterns = ("/MyServlet"))

public class MyServlet implements Servlet {
    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String servletName = servletConfig.getServletName();

        String newStr = servletRequest.getAttribute("new").toString();
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("<html><head></head>" + "<body> Hello from" + servletName + newStr+ "</body></html>");
    }

    @Override
    public String getServletInfo() {
        return "my servlet";
    }

    @Override
    public void destroy() {

    }
}
