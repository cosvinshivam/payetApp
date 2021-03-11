package com.paynet.project.serviceAPI;

import java.math.BigDecimal;
import java.util.Optional;

import com.paynet.project.wallet.Wallet;

public interface WalletServiceAPI {
	
	public Iterable<Wallet> getAllWallets();
	Optional<Wallet> getWalletByNumber(String phoneNumber);
	void openAccount(Wallet wallet);
	void transferMoney(String sender, String receiver, BigDecimal amount);
	void depositMoney(Wallet wallet); 

}
