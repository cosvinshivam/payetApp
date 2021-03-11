package com.paynet.project.serviceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paynet.project.dao.WalletDao;
import com.paynet.project.serviceAPI.WalletServiceAPI;
import com.paynet.project.wallet.Wallet;

@Service
public class WalletServiceImpl implements WalletServiceAPI{

	@Autowired
	private WalletDao dao;
	
	@Override
	public void openAccount(Wallet wallet) {
		wallet.setAmount(new BigDecimal("1000"));
		dao.openAccount(wallet);
	}

	@Override
	public void depositMoney(Wallet wallet) {
		dao.depositMoney(wallet);
	}


	@Override
	public void transferMoney(String sender, String receiver, BigDecimal amount) {
		dao.transferMoney(sender, receiver, amount);
	}
	
	@Override
	public Iterable<Wallet> getAllWallets() {
		return dao.getAllWallets();
	}

	@Override
	public Optional<Wallet> getWalletByNumber(String phoneNumber) {
		return dao.getWalletByNumber(phoneNumber);
	}

}
