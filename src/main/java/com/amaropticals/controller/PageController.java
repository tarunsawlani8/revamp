package com.amaropticals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String home() {

		System.out.println("print");
		return "home";

	}

	@RequestMapping("/internalApps/backoffice")
	public String login() {

		return "dashboard";

	}


}
