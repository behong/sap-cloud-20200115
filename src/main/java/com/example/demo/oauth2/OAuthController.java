package com.example.demo.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {
	
	@Autowired
	private OAuthService oAuthservice;
	
	@RequestMapping("/oauth2")
	public String getToken(){
		return oAuthservice.getToken(); 
	}	

}
