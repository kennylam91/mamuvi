package com.app.mamuvi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchDTO implements Serializable{

  private static final long serialVersionUID = 8739009174484954479L;
  
  private String titleS;
  
  private Double imdbS;
  
  private LocalDate releasedDateS;
  
  private Long countryS;
  
  private Long lengthS;
  
  private Long resolutionS;
  
  private Long typeS;
  
  private Long page;
  
  private Long recordsPP; //Records Per Page

}
