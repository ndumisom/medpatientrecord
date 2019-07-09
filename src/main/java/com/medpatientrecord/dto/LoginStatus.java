/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medpatientrecord.dto;

/**
 *
 * @author Josch
 */
public class LoginStatus {
    String loginStatusMessage;
    String userType;
    Integer userId;

    public String getLoginStatusMessage() {
        return loginStatusMessage;
    }

    public void setLoginStatusMessage(String loginStatusMessage) {
        this.loginStatusMessage = loginStatusMessage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }   

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
