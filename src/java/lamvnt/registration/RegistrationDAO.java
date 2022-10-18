/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lamvnt.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class RegistrationDAO implements Serializable {

    public /*boolean*/RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
//                String sql = "Select username " //Phai nhan space roi moi xuong hang -> neu ko bi loi syntax
//                        + "From Registration "
//                        + "Where username = ? "
//                        + "And password = ?";
                String sql = "Select username, lastname, isAdmin " //Phai nhan space roi moi xuong hang -> neu ko bi loi syntax
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. create statement obj
                stm = connection.prepareStatement(sql); // tao ra obj rong
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery(); // do phia tren da nap roi nen ko can truyen them tham so de nap vao bo nho
                //5 process result
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result=new RegistrationDTO(username, null, fullname, role);
                    
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

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {  // do account phai khi xu li moi co, nen khi nao can thi lay
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
                String sql = "Select username, password, lastname, isAdmin " //Phai nhan space roi moi xuong hang -> neu ko bi loi syntax
                        + "From Registration "
                        + "Where lastname like ?";
                //3. create statement obj
                stm = connection.prepareStatement(sql); // tao ra obj rong
                stm.setString(1, "%" + searchValue + "%");
                //4. execute query
                rs = stm.executeQuery(); // do phia tren da nap roi nen ko can truyen them tham so de nap vao bo nho
                //5 process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }// end account is initialized
                    //account has existed
                    this.accounts.add(dto);
                    //this.accounts.add(dto);
                } // end traverse Result Set
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

    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. create statement obj
                stm = connection.prepareStatement(sql); // tao ra obj rong
                stm.setString(1, username);
                //4. execute query
                int affactedRows = stm.executeUpdate();
                //5 process result
                if (affactedRows > 0) {
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

    public boolean updateAccount(String username, String newPass, boolean newRole)
            throws SQLException, NamingException { // update password, role
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. write sql string
                String sql = "update Registration\n"
                        + "set password=?, isAdmin=?\n" //pass: varchar; isAdmin: bit(0,1)
                        + "where username LIKE ?"; //username: varchar
                //3. create stm 
                stm = connection.prepareStatement(sql);
                stm.setString(1, newPass);
                stm.setBoolean(2, newRole);
                stm.setString(3, username);
                //4. execute update
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
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

    public boolean isAccountDupplicate(String username)
            throws SQLException, NamingException {

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
                String sql = "Select username " //Phai nhan space roi moi xuong hang -> neu ko bi loi syntax
                        + "From Registration "
                        + "Where username = ?";
                //3. create statement obj
                stm = connection.prepareStatement(sql); // tao ra obj rong
                stm.setString(1, username);
                //4. execute query
                rs = stm.executeQuery(); // do phia tren da nap roi nen ko can truyen them tham so de nap vao bo nho
                //5 process result
                if (rs.next()) {
                    result = true;
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
    
    public boolean createAccount(RegistrationDTO dto)
        throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. create sql string
                String sql = "insert Registration("
                        + "username, password, lastname, isAdmin) "
                        + "Values(?,?,?,?)";
                //3. create statement obj                
                stm = connection.prepareStatement(sql); // tao ra obj rong
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. execute query
                int affactedRows = stm.executeUpdate();
                //5 process result
                if (affactedRows > 0) {
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
}
