package com.app.mamuvi.service;

import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieSearchDTO;
import com.app.mamuvi.dto.ResultSetDTO;
import com.app.mamuvi.model.Movie;

public interface MovieService {

  MovieDetailDTO getMovieDetailById(Long id);
  
  Long saveMovie(MovieDTO movieDTO);
  
  void updateMovie(MovieDTO movieDTO) throws Exception;
  
  ResultSetDTO<Movie> searchMovie(MovieSearchDTO movieSearchDTO);
}
