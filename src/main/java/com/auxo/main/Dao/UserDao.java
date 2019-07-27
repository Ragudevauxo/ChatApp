package com.auxo.main.Dao;

import com.auxo.main.model.Model;

public class UserDao {



	public Model findByName(String namelogin) {
		Model model=new Model();
		model.setName(namelogin);
		return model;
	}

}
