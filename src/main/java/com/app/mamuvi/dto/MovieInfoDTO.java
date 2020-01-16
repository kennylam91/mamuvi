package com.app.mamuvi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieInfoDTO {

  private String enTitle;
  
  private String viTitle;
  
  private String movieUrl;
  
  private String imgUrl;

}
