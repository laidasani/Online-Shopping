package net.lka.onlineshoppingFrontEnd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.lka.onlineshoppingBackEnd.dao.UserDAO;
import net.lka.onlineshoppingBackEnd.dto.Address;
import net.lka.onlineshoppingBackEnd.dto.Cart;
import net.lka.onlineshoppingBackEnd.dto.User;
import net.lka.onlineshoppingFrontEnd.model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		
		registerModel.setUser(user);
	}
	public void addBilling(RegisterModel registerModel, Address billing) {
		
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel) {
		String transitionValue="success";
		
		
		User user=registerModel.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//save the user
		userDAO.add(user);
		
		//get the address
		Address billing=registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
	 public String validateUser(User user, MessageContext error) {
		  String transitionValue = "success";
		   if(!user.getPassword().equals(user.getConfirmPassword())) {
		    error.addMessage(new MessageBuilder()
		    		.error()
		    		.source("confirmPassword")
		    		.defaultText("Password does not match confirm password!")
		    		.build());
		    		 transitionValue = "failure";    
		   }  
		   if(userDAO.getByEmail(user.getEmail())!=null) {
		    error.addMessage(new MessageBuilder().error().source(
		      "email").defaultText("Email address is already taken!").build());
		    transitionValue = "failure";
		   }
		  return transitionValue;
		 }
}
