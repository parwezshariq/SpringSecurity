package com.shariqparwez.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TOTPController {
	
	@GetMapping("/totp-login")
	public String totpLogin() {		
		return "totp-login";
	}
	
	@GetMapping("/totp-login-error")
	public String totpLoginError(Model model) {
		model.addAttribute("loginError",true);
		return "totp-login";
	}
	
}
