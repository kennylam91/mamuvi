package com.app.mamuvi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  MovieService movieService;
  

  @PostMapping
  public ResponseEntity<Long> createMovie(@RequestBody MovieDTO movieDTO) {
    Long movieId = movieService.saveMovie(movieDTO);
    if(null != movieId) {
      return new ResponseEntity<>(movieId,HttpStatus.CREATED);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<MovieDetailDTO> getMovieById(@PathVariable("id") Long id){
    MovieDetailDTO movieDetailDTO = movieService.getMovieDetailById(id);
    return ResponseEntity.ok(movieDetailDTO);
  }
  
}
