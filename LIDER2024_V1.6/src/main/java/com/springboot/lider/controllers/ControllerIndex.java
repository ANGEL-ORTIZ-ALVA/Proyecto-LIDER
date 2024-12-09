package com.springboot.lider.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIndex {
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
