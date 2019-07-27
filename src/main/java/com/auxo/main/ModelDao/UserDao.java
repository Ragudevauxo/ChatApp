package com.auxo.main.ModelDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auxo.main.model.Model;
import com.auxo.main.repository.Repository;
public class UserDao {

	@Autowired
	Repository userrepo;
	
	
	public Model findByName(String username) {
		
		//userrepo.findByName(username);
		Model model=(Model) userrepo.findByName(username);
		return model;
	}

}
