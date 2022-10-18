/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.registration;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RegistrationLoginError implements Serializable{
    private String  userameEmptyErr;
    private String passwordEmptyErr;
    private String accountNotFound;

    public RegistrationLoginError() {
    }

    /**
     * @return the userameEmptyErr
     */
    public String getUserameEmptyErr() {
        return userameEmptyErr;
    }

    /**
     * @param userameEmptyErr the userameEmptyErr to set
     */
    public void setUserameEmptyErr(String userameEmptyErr) {
        this.userameEmptyErr = userameEmptyErr;
    }

    /**
     * @return the passwordEmptyErr
     */
    public String getPasswordEmptyErr() {
        return passwordEmptyErr;
    }

    /**
     * @param passwordEmptyErr the passwordEmptyErr to set
     */
    public void setPasswordEmptyErr(String passwordEmptyErr) {
        this.passwordEmptyErr = passwordEmptyErr;
    }

    /**
     * @return the accountNotFound
     */
    public String getAccountNotFound() {
        return accountNotFound;
    }

    /**
     * @param accountNotFound the accountNotFound to set
     */
    public void setAccountNotFound(String accountNotFound) {
        this.accountNotFound = accountNotFound;
    }

    
    
}
