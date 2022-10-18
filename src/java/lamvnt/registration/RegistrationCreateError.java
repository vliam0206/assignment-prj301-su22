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
public class RegistrationCreateError implements Serializable{
    private String  userameLengthErr;
    private String passwordLengthErr;
    private String fullnamLengthErr;
    private String confirmNotMathched;
    private String usernameExisted;
    public RegistrationCreateError() {
    }
    
    public String getUserameLengthErr() {
        return userameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public String getFullnamLengthErr() {
        return fullnamLengthErr;
    }

    public String getConfirmNotMathched() {
        return confirmNotMathched;
    }

    public String getUsernameExisted() {
        return usernameExisted;
    }

    public void setUserameLengthErr(String userameLengthErr) {
        this.userameLengthErr = userameLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public void setFullnamLengthErr(String fullnamLengthErr) {
        this.fullnamLengthErr = fullnamLengthErr;
    }

    public void setConfirmNotMathched(String confirmNotMathched) {
        this.confirmNotMathched = confirmNotMathched;
    }

    public void setUsernameExisted(String usernameExisted) {
        this.usernameExisted = usernameExisted;
    }
    
}
