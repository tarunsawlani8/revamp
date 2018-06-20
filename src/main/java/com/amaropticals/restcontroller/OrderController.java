package com.amaropticals.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {

	@RequestMapping("/getAllOrders")
	public List<String> getAllOrders() {
		System.out.println("In");

		return Arrays.asList("abc", "cde");
	}
}
