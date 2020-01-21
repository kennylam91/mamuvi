package com.app.mamuvi.dao;

import com.app.mamuvi.dto.UserDTO;
import com.app.mamuvi.model.User;

public interface UserDao {

  Long saveUser(User user);
  
  Long updateUser(User user);
  
  UserDTO findUserByEmail(String email);
}
