package com.app.mamuvi.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppParamSearchDTO implements Serializable {
  
  private static final long serialVersionUID = -6948794643873817816L;

  private Long id;
  
  private String type;
  
  private String name;
  
  private String description;
  
  private List<Long> ids;
}
