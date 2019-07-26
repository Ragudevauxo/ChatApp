package com.auxo.main.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.auxo.main.Dao.UserDao;
import com.auxo.main.model.Model;

public class Controllers {

	@Autowired
	UserDao userdao;
	
	@RequestMapping("/")
	public String index() {
	    return "index";
	}
	
}
