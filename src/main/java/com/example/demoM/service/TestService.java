package com.example.demoM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoM.mapper.TestMapper;

@Service
public class TestService {
	@Autowired
	TestMapper testMapper;
	
	public String getNameById(int id){
		return testMapper.getNameById(id);
	}
}
