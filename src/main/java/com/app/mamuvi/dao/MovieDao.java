package com.app.mamuvi.dao;

import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieSearchDTO;
import com.app.mamuvi.dto.ResultSetDTO;
import com.app.mamuvi.model.Movie;

public interface MovieDao {

  Long saveMovie(Movie movie);
  
  void updateMovie(Movie movie) throws Exception;
  
  MovieDetailDTO getMovieById(Long id);
  
  ResultSetDTO<Movie> searchMovie(MovieSearchDTO movieSearchDTO); 
}
