package com.amaropticals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String home() {

		System.out.println("print");
		return "home";

	}

	@RequestMapping("/renderlogin")
	public String login() {

		System.out.println("print");
		return "login";

	}

	@RequestMapping("/loadDash")
	public String logdashin(HttpServletRequest request) {

		System.out.println("print");
		if ("ankit".equalsIgnoreCase(request.getParameter("username"))
				&& "opticals123".equalsIgnoreCase(request.getParameter("password"))) {
			return "dashboard";
		} else {
			return "login";
		}

	}

	/*
	 * @RequestMapping(value = "/loginApp", method = RequestMethod.POST) public void
	 * loginDo(HttpServletRequest request, HttpServletResponse response) throws
	 * ServletException, IOException {
	 * 
	 * 
	 * 
	 * 
	 * if ("ankit".equalsIgnoreCase(request.getParameter("username")) &&
	 * "opticals123".equalsIgnoreCase(request.getParameter("password"))) {
	 * RequestDispatcher dispatcher = request .getRequestDispatcher("/loadDash");
	 * 
	 * dispatcher.forward(request, response); } }
	 */

}
