package com.auxo.main.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auxo.main.Dao.UserDao;
import com.auxo.main.model.Model;

@Controller

public class Controllers {

	@Autowired
	UserDao userdao;
	
	@RequestMapping("/")
	public String index() {
	    return "index.html";
	}

	@RequestMapping(value = "/validation", method = RequestMethod.GET)
	public String validate(@RequestParam("namelogin") String namelogin) {
//		Model emp = ((UserDao) userdao).findByName(namelogin);
//		
//		System.out.println("employee"+emp);
//		
//		if(emp==null) {
//			return "notfound.html";
//		}
//		System.out.println("the  "+namelogin);
		return "namelogin";
	}
	
}
