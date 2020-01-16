package com.app.mamuvi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dto.MovieInfoDTO;
import com.app.mamuvi.service.MovieExtractingService;

@RestController
@RequestMapping("/weekly-top-movies")
public class WeeklyTopMoviesController {
  
  @Autowired
  MovieExtractingService movieExtractingService;

  @GetMapping("/phim-moi")
  public ResponseEntity<List<MovieInfoDTO>> getWeeklyTopMoviesOfPhimmoi(){
    List<MovieInfoDTO> movieInfoDTOs = movieExtractingService.getWeeklyTopMoviesOfPhimmoi();
    return ResponseEntity.ok(movieInfoDTOs);
  }
  
  @PutMapping("/phim-moi")
  public ResponseEntity<Void> updateWeeklyTopMoviesOfPhimmoi(){
    movieExtractingService.updateMovies();
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
}
