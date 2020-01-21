package com.app.mamuvi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.app.mamuvi.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
  
  @Id
  @Column(name = "id")
  @GeneratedValue
  private Long id;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "watched_list")
  private String watchedList;
  
  @Column(name = "favorite_list")
  private String favoriteList;
  
  @Column(name = "favorite_type")
  private String favoriteType;

  public User(String email) {
    this.email = email;
  }
  
  public static User mapFromUserDTO(UserDTO userDTO) {
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setFavoriteList(userDTO.getFavoriteList());
    user.setWatchedList(userDTO.getWatchedList());
    user.setId(userDTO.getId());
    user.setFavoriteType(userDTO.getFavoriteType());
    return user;
  }
  
  
}
