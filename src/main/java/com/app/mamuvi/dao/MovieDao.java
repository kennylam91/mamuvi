package com.app.mamuvi.dao;

import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.model.Movie;

public interface MovieDao {

  Long saveMovie(MovieDTO movie);
  
  MovieDetailDTO getMovieById(Long id);
}
