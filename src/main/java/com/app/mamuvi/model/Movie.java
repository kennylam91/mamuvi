package com.app.mamuvi.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Long id;
  
  @Column(name = "title")
  private String title;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "released_date")
  private LocalDate releasedDate;
  
  @Column(name = "country")
  private String country;
  
  @Column(name = "imdb_rating")
  private Double imdb;
  
  @Column(name = "length")
  private Long length;
  
  @Column(name = "resolution")
  private Long resolution;
  
  @Column(name = "language")
  private Long language;
  
  //The loai phim: luu id, chon nhieu, cach nhau boi dau ','
  @Column(name = "movie_type")
  private String type;
  
  //Cong ty san xuat: luu id, chon nhieu, cach nhau dau ','
  @Column(name = "production_companies")
  private String prodCompanies;
  
  @Column(name = "movie_url")
  private String movieUrl;
  
  @Column(name = "img_url")
  private String imgUrl;
  
  @Column(name = "director")
  private String director;
  
  @Column(name = "en_convert_title")
  private String enConvertTitle;
  
  @Column(name = "trailer")
  private String trailer;
  
  @Column(name = "casts")
  private String casts;
  
  public static Movie mapFromMovieDTO(MovieDTO movieDTO) {
    Movie movie = new Movie();
    movie.setId(movieDTO.getId());
    movie.setTitle(movieDTO.getTitle());
    movie.setDescription(movieDTO.getDescription());
    movie.setImdb(movieDTO.getImdb());
    movie.setReleasedDate(movieDTO.getReleasedDate());
    movie.setCountry(movieDTO.getCountry());
    movie.setLength(movieDTO.getLength());
    movie.setResolution(movieDTO.getResolution());
    movie.setLanguage(movieDTO.getLanguage());
    movie.setType(movieDTO.getType());
    movie.setProdCompanies(movieDTO.getProdCompanies());
    movie.setDirector(movieDTO.getDirector());
    movie.setImgUrl(movieDTO.getImgUrl());
    movie.setMovieUrl(movieDTO.getMovieUrl());
    movie.setEnConvertTitle(CommonUtils.convertVnToEn(movieDTO.getTitle()));
    movie.setTrailer(movieDTO.getTrailer());
    movie.setCasts(movieDTO.getCasts());
    return movie;
  }
}
