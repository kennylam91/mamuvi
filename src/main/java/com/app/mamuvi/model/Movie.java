package com.app.mamuvi.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

  private Long id;
  
  private String title;
  
  private String description;
  
  private LocalDate releasedDate;
}
