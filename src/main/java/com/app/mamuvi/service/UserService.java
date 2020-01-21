package com.app.mamuvi.service;

import com.app.mamuvi.dto.UserDTO;
import com.app.mamuvi.model.User;

public interface UserService {

  Long saveUser(UserDTO userDTO);
  
  Long updateUser(UserDTO userDTO);
  
  UserDTO findUserByEmail(String email);
}
