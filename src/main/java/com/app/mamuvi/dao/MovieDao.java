package com.app.mamuvi.dao;

import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.model.Movie;

public interface MovieDao {

  public Long saveMovie(Movie movie);
  
  public MovieDTO getMovieById(Long id);
}
