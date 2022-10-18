/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lamvnt.utils.DBHelper;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();        
        try {
            DBHelper.getSiteMaps(context);
        }catch (IOException ex) {
            context.log("ServletListener IO "+ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
