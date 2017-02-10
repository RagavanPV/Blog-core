package com.ragavan.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomTest {
public static void main(String[] args) {

	
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	String pwd = RandomStringUtils.random( 20, characters );
	System.out.println( pwd );
	
}
}
