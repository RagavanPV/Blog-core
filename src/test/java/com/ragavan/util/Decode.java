package com.ragavan.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 

public class Decode {
	     
	    public static void main(String[] args) {
	           
	          PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	           
	          String plainTextPassword = "raj";
	           
	          //Encode password before saving plainTextPassword in Database       
	          String hashedPassword = passwordEncoder.encode(plainTextPassword);          
	          System.out.println(hashedPassword);
	           
	           
	          //Validate DB Password with user password
	          String dbPassword = "$2a$10$WuZITXFuiieyU5Pds.r1xOgZDdwv7NJ5/dCIWAsODn/Ipwdo6SuZS";
	          boolean isValid = passwordEncoder.matches(plainTextPassword, dbPassword);
	           
	          if (isValid){
	              System.out.println("Password Matched");
	          }
	          else
	          {
	              System.out.println("Password Not Matched");
	          }
	           
	    }
	}

