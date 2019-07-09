/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medpatientrecord.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="userinfo")
public class UserInfo implements Serializable{
    
    	@Id
	@GeneratedValue
        @Column(name = "id")
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String type;
        
        	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
    
}
