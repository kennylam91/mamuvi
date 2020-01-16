package com.app.mamuvi.service;

import java.util.List;
import com.app.mamuvi.dto.MovieInfoDTO;

public interface MovieExtractingService {

  List<MovieInfoDTO> getWeeklyTopMoviesOfPhimmoi();
  
  void updateMovies();
}
