package com.app.mamuvi.dto;

import java.time.LocalDate;
import com.app.mamuvi.jackson.LocalDateDeserializer;
import com.app.mamuvi.jackson.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

  private Long id;

  private String title;

  private String description;
  
  private Double imdb;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate releasedDate;

  private String country;
  
  private Long length;
  
  private Long resolution;
  
  private String language;
  
  private String type;
  
  private String prodCompanies;

}
