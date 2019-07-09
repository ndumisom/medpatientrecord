/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medpatientrecord.config.variables;

/**
 *
 * @author Josch
 */
public enum UserTypeEnum {
    ADMIN("admin"),
    CAPTURE("capturer");
    private String userType;
    UserTypeEnum(String userType) {
        this.userType = userType;
    }
    public String getUserType() {
        return userType;
    }
}

