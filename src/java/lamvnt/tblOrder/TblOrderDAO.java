/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.tblOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.naming.NamingException;
import lamvnt.product.ProductDTO;
import lamvnt.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class TblOrderDAO implements Serializable {

    // method checkout: insert cart into database
    public boolean checkout(Map<String, ProductDTO> productList, Map<String, Integer> items)
            throws NamingException, SQLException {

        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            double total = 0;
            if (connection != null) {
                for (String key : items.keySet()) { //key: sku
                    ProductDTO product = productList.get(key);
                    int quantity = items.get(key);
                    total += product.getPrice() * quantity;
                }// end traverser items
                //2. create sql string
                String sql = "insert TblOrder(date, total)\n"
                        + "values(?, ?)";
                //3. create statement obj
                stm = connection.prepareStatement(sql);
                stm.setDate(1, new Date(System.currentTimeMillis()));
                stm.setDouble(2, total);
                //4. execute query
                int affectedRows = stm.executeUpdate();
                //5 process result
                if (affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    // method to get the curent identity of tblOrder table (ordId)
    public int getIdentCurent()
            throws NamingException, SQLException {

        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
                String sql = "select ident_current('TblOrder')\n"
                        + "as identCurrent";
                //3. create statement obj
                stm = connection.createStatement();
                //4. execute query
                rs = stm.executeQuery(sql);
                //5 process result
                if (rs.next()) {
                    result = rs.getInt("identCurrent");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
}
