package com.app.mamuvi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dto.UserDTO;
import com.app.mamuvi.model.User;
import com.app.mamuvi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  UserService userService;
  
  @PostMapping
  public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO){
    UserDTO userDTOFound = userService.findUserByEmail(userDTO.getEmail());
    if(userDTOFound == null) {
      Long userDTOId = userService.saveUser(userDTO);
      userDTOFound = new UserDTO(userDTO.getEmail());
      userDTOFound.setId(userDTOId);
    }
    return ResponseEntity.ok(userDTOFound);
  }
  
  @PutMapping
  public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO){
    Long userId = userService.updateUser(userDTO);
    if(userId != null) {
      return new ResponseEntity<>(HttpStatus.OK);
    }else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
  }
}
