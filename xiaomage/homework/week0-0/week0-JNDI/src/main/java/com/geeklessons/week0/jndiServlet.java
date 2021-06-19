package com.geeklessons.week0;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *  jndi test servlet
 */
@WebServlet(urlPatterns = "/jndi", asyncSupported = false)
public class jndiServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context encVtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)encVtx.lookup("jdbc/mysqlDataSource");
            Connection conn1 = ds.getConnection();
            if (conn1 != null) {
                resp.getWriter().println("get JDBC connection suc");
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().println("Demo Servlet");
    }
}
