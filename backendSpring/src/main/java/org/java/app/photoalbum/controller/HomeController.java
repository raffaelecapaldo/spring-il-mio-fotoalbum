package org.java.app.photoalbum.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
		
		@GetMapping public String getHome(@AuthenticationPrincipal UserDetails user) {
			System.out.println("ID   : " + user);
		//	user.getAuthorities().stream().forEach(auth -> System.out.println(auth.getAuthority()));
			return "home";
			
		
}}
