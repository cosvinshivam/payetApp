package com.paynet.project.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.paynet.project.repository.WalletRepository;
import com.paynet.project.wallet.Wallet;

@Repository
public class WalletDao {
	
	@Autowired
	private WalletRepository repo;
	
	public Iterable<Wallet> getAllWallets() {
		return repo.findAll();
		
	}
	
	public Optional<Wallet> getWalletByNumber(String phoneNumber) {
		return repo.findById(phoneNumber);
		
	}
	
	public Wallet openAccount(Wallet wallet) {
		return repo.save(wallet);
	}
	
	public void transferMoney(String sender, String receiver, BigDecimal amount) {
		//repo.save(wallet);
	}
	
}
