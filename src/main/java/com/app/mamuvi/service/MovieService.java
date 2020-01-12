package com.app.mamuvi.service;

import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;

public interface MovieService {

  MovieDetailDTO getMovieDetailById(Long id);
  
  Long saveMovie(MovieDTO movieDTO);
}
