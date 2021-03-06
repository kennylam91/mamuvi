package com.app.mamuvi.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppParamDTO implements Serializable{

  private static final long serialVersionUID = -5408355088010540203L;

  private Long id;
  
  private String type;
  
  private String name;
  
  private String description;
}
