package com.example.demoM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoM.service.TestService;

@RestController
public class TestCotroller {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("getNameById")
	public String getNameById(int id){
		return testService.getNameById(id);
	}
}
