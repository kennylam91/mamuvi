package com.app.mamuvi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.mamuvi.dao.UserDao;
import com.app.mamuvi.dto.UserDTO;
import com.app.mamuvi.model.User;
import com.app.mamuvi.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
  
  @Autowired
  UserDao userDao;

  @Override
  public Long saveUser(UserDTO userDTO) {
    User user = User.mapFromUserDTO(userDTO);
    return userDao.saveUser(user);
    
  }

  @Override
  public Long updateUser(UserDTO userDTO) {
    User user = User.mapFromUserDTO(userDTO);
    return userDao.updateUser(user);
  }

  @Override
  public UserDTO findUserByEmail(String email) {
    return userDao.findUserByEmail(email);
  }

  
}
