package com.app.mamuvi.service.impl;


import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieSearchDTO;
import com.app.mamuvi.dto.ResultSetDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.service.MovieService;
import com.app.mamuvi.util.CommonUtils;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieDao movieDao;
  
  @Autowired
  AppParamDao appParamDao;
  
  @Override
  public MovieDetailDTO getMovieDetailById(Long id) {
    MovieDetailDTO movieDetailDTO = movieDao.getMovieById(id);
    return movieDetailDTO;
  }

  @Override
  public Long saveMovie(MovieDTO movieDTO) {
    Movie movie = Movie.mapFromMovieDTO(movieDTO);
    return movieDao.saveMovie(movie);
  }

  @Override
  public void updateMovie(MovieDTO movieDTO) throws Exception {
    Movie movie = Movie.mapFromMovieDTO(movieDTO);
    movieDao.updateMovie(movie);
  }

  @Override
  public ResultSetDTO<Movie> searchMovie(MovieSearchDTO movieSearchDTO) {
    return movieDao.searchMovie(movieSearchDTO);
  }

  
}
