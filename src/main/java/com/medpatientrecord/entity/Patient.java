/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medpatientrecord.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
@Entity
@Table(name="patient")
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "height")
	private Double height;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "dblood_pressure")
	private Double sBloodPressre;

	@Column(name = "sblood_pressure")
	private Double d_BloodPressue;

	@Column(name = "bmi")
	private Double bmi;

	@Column(name = "captured_by")
	private Integer capturedBy;

	@Column(name = "captured_date")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date capturedDate;

	public Patient() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getsBloodPressre() {
		return sBloodPressre;
	}

	public void setsBloodPressre(Double sBloodPressre) {
		this.sBloodPressre = sBloodPressre;
	}

	public Double getD_BloodPressue() {
		return d_BloodPressue;
	}

	public void setD_BloodPressue(Double d_BloodPressue) {
		this.d_BloodPressue = d_BloodPressue;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Integer getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Integer capturedBy) {
		this.capturedBy = capturedBy;
	}

	public Date getCapturedDate() {
		return capturedDate;
	}

	public void setCapturedDate(Date capturedDate) {
		this.capturedDate = capturedDate;
	}

}
