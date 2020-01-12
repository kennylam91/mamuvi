package com.app.mamuvi.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailDTO extends MovieDTO implements Serializable{

  private static final long serialVersionUID = 6830203717468711169L;
  
  private String countryName;
  private String resolutionName;
  private String languageName;
  private String movieTypeName;
  private String prodCompanyName;
  
  

}
