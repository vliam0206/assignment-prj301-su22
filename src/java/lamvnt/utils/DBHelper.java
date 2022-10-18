/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBHelper implements Serializable/*Phai co*/{
    public static Connection makeConnection() 
        throws /*ClassNotFoundException*/ NamingException, SQLException {
//            //1.load driver (DK: phai co driver) 
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Neu go ma ko bung code thi remove jdbk4 -> add lai
//            //2. make connection String [protocaol://ip:port;]
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=MVC2SE;instanceName=SQLEXPRESS";
//            //3. open connection
//            Connection con = DriverManager.getConnection(url, "sa", "lamvnt160857");
//        
//            return con;
            Context currentContext= new InitialContext();
            Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)tomcatContext.lookup("LA0602");
            Connection connection = datasource.getConnection(); // ko truyen tham so nao ca
            return connection;
    }
    public static void getSiteMaps(ServletContext contextScope) 
            throws IOException {
        
        String siteMapsFilePath=contextScope.getInitParameter("SITEMAPS_FILE_PATH");
        InputStream is = null;
        Properties siteMaps=null;//
        try {
            is=contextScope.getResourceAsStream(siteMapsFilePath);
            
            siteMaps=new Properties();
            siteMaps.load(is);
            
            contextScope.setAttribute("SITEMAPS", siteMaps);
        }finally {
            if (is !=null) {
                is.close();
            }
        }
    }
}
