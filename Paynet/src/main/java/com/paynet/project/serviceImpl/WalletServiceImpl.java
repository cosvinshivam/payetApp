package com.paynet.project.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paynet.project.dao.WalletDao;
import com.paynet.project.exception.WalletNotFoundException;
import com.paynet.project.serviceAPI.WalletServiceAPI;
import com.paynet.project.wallet.Wallet;

@Service
public class WalletServiceImpl implements WalletServiceAPI{

	@Autowired
	private WalletDao dao;
	
	@Override
	public Wallet openAccount(Wallet wallet) throws WalletNotFoundException {
		
		Optional<Wallet> wall = dao.getWalletByNumber(wallet.getPhoneNumber());
        
        if(wall.isPresent())
        {             
           throw new WalletNotFoundException("Wallet already exist with "+ wallet.getPhoneNumber() +" this number");
        } else {
        	wallet.setAmount(new BigDecimal("1000"));
        	wallet.setCreatedAt(new Date());
        	wallet = dao.openAccount(wallet);
            return wallet;
        }		
	}

	@Override
	public Wallet depositMoney(Wallet wallet) throws WalletNotFoundException {
		Optional<Wallet> wall = dao.getWalletByNumber(wallet.getPhoneNumber());

		if(!wall.isPresent())
		{             
			throw new WalletNotFoundException(" No Wallet exist with "+ wallet.getPhoneNumber() +" this number");
		} else {
			
			wallet.setUpdatedAt(new Date());
			wallet = dao.openAccount(wallet);
			return wallet;
		}		
	}


	@Override
	public Wallet transferMoney(String sender, String receiver, BigDecimal amount) throws WalletNotFoundException {
		dao.transferMoney(sender, receiver, amount);
		
		Optional<Wallet> senderWallet = dao.getWalletByNumber(sender);
		Optional<Wallet> receiverWallet = dao.getWalletByNumber(sender);

		if(!senderWallet.isPresent())
		{             
			throw new WalletNotFoundException(" No Wallet exist with "+ senderWallet +" this number");
		}else if(!receiverWallet.isPresent())
		{             
			throw new WalletNotFoundException(" No Wallet exist with "+ receiverWallet +" this number");
		}  
		else{	
			if(senderWallet.get().getAmount().compareTo(amount) == 1) {
				senderWallet.get().setAmount(senderWallet.get().getAmount().subtract(amount));
				senderWallet.get().setUpdatedAt(new Date());
			}	
			else
				throw new WalletNotFoundException("Insufficient Amount");
			
			receiverWallet.get().setAmount(receiverWallet.get().getAmount().add(amount));
			receiverWallet.get().setUpdatedAt(new Date());
			dao.openAccount(senderWallet.get());
			dao.openAccount(receiverWallet.get());
		}
		return senderWallet.get();	
	}
	
	@Override
	public List<Wallet> getAllWallets() {
		List<Wallet> wallets = (List<Wallet>) dao.getAllWallets();
        
        if(wallets.size() > 0) {
            return wallets;
        } else {
            return new ArrayList<Wallet>();
        }
	}

	@Override
	public Wallet getWalletByNumber(String phoneNumber) throws WalletNotFoundException {
		
		 Optional<Wallet> wallet = dao.getWalletByNumber(phoneNumber);
         
	        if(wallet.isPresent()) {
	            return wallet.get();
	        } else {
	          throw new WalletNotFoundException("No employee record exist for given id");
	        }
	}

}
