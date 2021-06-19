package com.geeklessons.week0;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  jdbc manager
 */
public class MyDbConnectionManager {
    private Logger logger = Logger.getLogger(MyDbConnectionManager.class.getName());

    @Resource(name = "jdbc/myDataSource")
    private DataSource dataSource;

    @Resource(name = "jdbc/mysqlDataSource")
    private DataSource mysqlDataSource;

    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            //conn = mysqlDataSource.getConnection();
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }

        if (conn != null) {
            logger.log(Level.INFO, "get JNDI database connection suc!");
        }

        return conn;
    }

    public static void main(String[] args) throws Exception{
        //String url = "jdbc:derby:db/my-derby-db;create=true";
        String url = "jdbc:mysql://localhost:3306/db1?characterEncoding=GBK";
        Context initCtx = new InitialContext();
        Context encVtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)encVtx.lookup("jdbc/mysqlDataSource");
        Connection conn1 = ds.getConnection();
        Connection conn = DriverManager.getConnection(url,"root","123456");
    }

}
