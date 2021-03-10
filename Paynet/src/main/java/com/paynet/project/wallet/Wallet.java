package com.paynet.project.wallet;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    String phoneNumber;
	String name;
	BigDecimal amount =	new BigDecimal("1000");
	
	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Wallet(String phoneNumber, String name, BigDecimal amount) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.amount = amount;
	}
	
	
}
