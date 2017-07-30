package com.kandasm.web.demo.controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class ItemsController {
	
	@RequestMapping("/demo")
    public String test() {
        return "demo success";
    }
}
    
