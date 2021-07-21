package org.geektimes.configuration.microprofile.config.source.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  servlet realization
 */
@WebServlet(urlPatterns = "/my", asyncSupported = true)
public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newStr = "new str";
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head></head>" + "<body> Hello from" + newStr+ "</body></html>");
    }
}
