/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import lamvnt.product.ProductDTO;
import lamvnt.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO implements Serializable {

    // method checkout: insert cart into database
    public boolean checkout(int identCurrent,Map<String, ProductDTO> productList, Map<String, Integer> items)
            throws NamingException, SQLException {

        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                //2. create sql string
                String sql = "insert OrderDetail(ordId,SKU, quantity, price, totalprice)\n"                        
                            + "values(?,?, ?, ?, ?)";                                    
                //3. create statement obj
                stm = connection.prepareStatement(sql);
                for (String key : items.keySet()) { //key: sku
                    ProductDTO product = productList.get(key);
                    int quantity = items.get(key); 
                    stm.setInt(1, identCurrent);
                    stm.setString(2, product.getSKU());
                    stm.setInt(3, quantity);
                    stm.setDouble(4, product.getPrice());
                    stm.setDouble(5, product.getPrice() * quantity);
                    stm.addBatch();
                }
                //4. execute query
                int[] affectedRows = null;
                if (stm != null) {
                    affectedRows = stm.executeBatch();
                }
                //5 process result
                if (affectedRows != null) {
                    result = true;
                    for (int affectedRow : affectedRows) {
                        if (affectedRow <= 0) {
                            result = false;
                            break;
                        }// end affectedRow <= 0
                    }// end traverser affectedRows
                }// end affectedRows not null
                if (result) {
                    connection.commit();
                }
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
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
}
