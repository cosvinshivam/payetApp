package com.paynet.project.serviceAPI;

import java.math.BigDecimal;
import java.util.Optional;

import com.paynet.project.exception.WalletNotFoundException;
import com.paynet.project.modal.Wallet;

public interface WalletServiceAPI {
	
	public Iterable<Wallet> getAllWallets();
	Wallet getWalletByNumber(String phoneNumber) throws WalletNotFoundException;
	Wallet openAccount(Wallet wallet) throws WalletNotFoundException;
	Wallet transferMoney(String sender, String receiver, BigDecimal amount) throws WalletNotFoundException;
	Wallet depositMoney(Wallet wallet) throws WalletNotFoundException;
	boolean login(String phoneNumber, String password) throws WalletNotFoundException;

}
