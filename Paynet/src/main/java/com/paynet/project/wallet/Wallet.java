package com.paynet.project.wallet;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
public class Wallet {

	@Id
	@Column(length=10)
    private String phoneNumber;
	
	@NotBlank(message = "Name is mandatory")
	@Column(nullable=false )
	private String name;
	
	@CreationTimestamp 
	private Date createdAt;
	  
	@UpdateTimestamp
	private Date updatedAt;
	 
	private BigDecimal amount =	new BigDecimal("1000");
	
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
	public Wallet(String phoneNumber, @NotBlank(message = "Name is mandatory") String name, Date createdAt,
			Date updatedAt, BigDecimal amount) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.amount = amount;
	}
	public Wallet(String phoneNumber, String name, BigDecimal amount) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.amount = amount;
	}
	
	 public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	    public String toString() {
	        return "Wallet [Contact=" + this.phoneNumber + ", Name=" + this.name + 
	                ", amount=" + this.amount   + ", createdAt =" + this.amount +", updatedAt = " + this.updatedAt;
	    }
	
}
