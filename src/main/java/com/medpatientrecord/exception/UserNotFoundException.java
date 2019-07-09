/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medpatientrecord.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
