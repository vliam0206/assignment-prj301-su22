/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamvnt.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    // method: return this list of products
    public List<ProductDTO> getProducts() {
        return this.productList;
    }
    // method: load all products in database
    public void loadAllProducts() throws SQLException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            //2. write sql string
            String sql = "select SKU, name, description, price\n"
                    + "from Product";
            //3. create statement obj           
            stm=con.createStatement();
            //4. execute query            
            rs = stm.executeQuery(sql);
            //5. process rs
            this.productList = null; // co can thiet ko? -> moi lan chay req co lam moi productList ko?
            while (rs.next()) {
                String SKU=rs.getString("SKU");
                String name=rs.getString("name");
                String description=rs.getString("description");
                double price=rs.getDouble("price");
                ProductDTO product = new ProductDTO(SKU, name, description, price);
                if (this.productList==null) {
                    productList=new ArrayList<>();
                }// end: if pd list is null
                productList.add(product); // pd list co the = null -> khi database ko co san pham nao
            }// end process rs
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }   
    // method: return product name when SKU is given
    public ProductDTO getProductBySKU(String SKU) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result=null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            //2. write sql string
            String sql = "select name, price\n"
                    + "from Product\n"
                    + "where SKU=?";
            //3. create statement obj           
            stm=con.prepareStatement(sql);
            stm.setString(1, SKU);
            //4. execute query            
            rs = stm.executeQuery();
            //5. process rs            
            if (rs.next()) {
                String name=rs.getString("name");
                double price = rs.getDouble("price");
                result = new ProductDTO(SKU, name, price);                
            }// end process rs
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
