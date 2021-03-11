package com.paynet.project.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paynet.project.exception.WalletNotFoundException;
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
	
	@GetMapping("/getall")
	public ResponseEntity<List<Wallet>> fetchAllWallets() {
		List<Wallet> list = serviceImpl.getAllWallets(); 
		return new ResponseEntity<List<Wallet>>(list, new HttpHeaders(), HttpStatus.OK);
    }                        
		
	@GetMapping("/ss/{phoneNumber}")
	 public ResponseEntity<Wallet> showStatement(@PathVariable String phoneNumber)  throws WalletNotFoundException {
       Wallet wallet = serviceImpl.getWalletByNumber(phoneNumber);
       return new ResponseEntity<Wallet>(wallet, new HttpHeaders(), HttpStatus.OK);
   }
	
	@PostMapping("/openAcc")
	 public ResponseEntity<Wallet> openAccount(@RequestBody Wallet wallet) throws WalletNotFoundException{
		Wallet w = serviceImpl.openAccount(wallet) ;
		return new ResponseEntity<Wallet>(w, new HttpHeaders(), HttpStatus.OK);
	}
		
	@PutMapping("/deposit")
	public ResponseEntity<Wallet> depositMoney(@Valid @RequestBody Wallet wallet) throws WalletNotFoundException{
		Wallet w = serviceImpl.depositMoney(wallet);
		return new ResponseEntity<Wallet>(w, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<Wallet> fundTransfer(@PathVariable String sender, @PathVariable String receiver, @PathVariable BigDecimal amount)throws WalletNotFoundException{
		Wallet w = serviceImpl.transferMoney(sender, receiver, amount);
		return new ResponseEntity<Wallet>(w, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/ss/{phoneNumber}/{password}")
	 public String authenticate(@PathVariable String phoneNumber, @PathVariable String password)  throws WalletNotFoundException {
      if(serviceImpl.login(phoneNumber, password)) {
    	return "success";  
      }
      //return new ResponseEntity<Wallet>(wallet, new HttpHeaders(), HttpStatus.OK);
	return password;
  }
}
