package com.app.mamuvi.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;
  
  private String email;
  
  private String watchedList;
  
  private String favoriteList;
  
  private String favoriteType;

  public UserDTO(String email) {
    this.email = email;
  }
  
  
}
