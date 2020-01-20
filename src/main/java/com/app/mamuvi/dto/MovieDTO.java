package com.app.mamuvi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import com.app.mamuvi.jackson.LocalDateDeserializer;
import com.app.mamuvi.jackson.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class MovieDTO implements Serializable {

  private static final long serialVersionUID = -3381363375514537169L;

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
  
  private Long language;
  
  private String type;
  
  private String prodCompanies;

  private String movieUrl;
  
  private String imgUrl;
  
  private String director;
  
  private String trailer;
  
  private String casts;
}
