package com.paynet.project.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paynet.project.serviceImpl.WalletServiceImpl;
import com.paynet.project.wallet.Wallet;

@RestController
public class WalletController {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private WalletServiceImpl serviceImpl;
	
	@RequestMapping("/mail/{email}/{id}")
	void sendEmail(@PathVariable("email") String email,@PathVariable("id") String id) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Reset Password");
        msg.setText("http://localhost:4200/reset-password/"+id);
        javaMailSender.send(msg);
    }
	
	
	@PostMapping("/openAccount")
	void openAccount(@RequestBody Wallet wallet) {
		serviceImpl.openAccount(wallet);
	}
	
	@PutMapping("/deposit")
	void depositMoney(@RequestBody Wallet wallet) {
		serviceImpl.depositMoney(wallet);
	}
	
	@PostMapping("/fundTransfer")
	void fundTransfer(@PathVariable String sender,@PathVariable String receiver,@PathVariable BigDecimal amount) {
		serviceImpl.transferMoney(sender,receiver,amount);
	}
	
	@GetMapping("/getall")
    Iterable<Wallet> fetchAllWallets() {
    	return serviceImpl.getAllWallets(); 
    }                        
	
	@GetMapping("/showstatement/{number}")
    Optional<Wallet> showStatement(@PathVariable String phoneNumber) {
    	return serviceImpl.getWalletByNumber(phoneNumber);
    }
    
}
