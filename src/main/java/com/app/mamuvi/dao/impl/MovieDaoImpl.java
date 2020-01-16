package com.app.mamuvi.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LocalDateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieSearchDTO;
import com.app.mamuvi.dto.ResultSetDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.util.HibernateUtil;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  Logger log = LogManager.getLogger(MovieDaoImpl.class);
  
  final String MovieDetailDTOSelect = "select "
      + " m.id as id, "
      + " m.title as title, "
      + " m.description as description, "
      + " m.released_date as releasedDate,"
      + " m.country as country,"
      + " m.length as length,"
      + " m.resolution as resolution,"
      + " m.language as language,"
      + " m.movie_type as type,"
      + " m.production_companies as prodCompanies,"
      + " m.imdb_rating as imdb,"
      + " m.director as director";
  
  private void addScalarMovieDetailDTO(SQLQuery sql){
    sql.addScalar("id", LongType.INSTANCE)
      .addScalar("title",StringType.INSTANCE)
      .addScalar("description",StringType.INSTANCE)
      .addScalar("releasedDate",LocalDateType.INSTANCE)
      .addScalar("country",StringType.INSTANCE)
      .addScalar("length", LongType.INSTANCE)
      .addScalar("resolution",LongType.INSTANCE)
      .addScalar("language",LongType.INSTANCE)
      .addScalar("type",StringType.INSTANCE)
      .addScalar("director",StringType.INSTANCE)
      .addScalar("prodCompanies",StringType.INSTANCE)
      .addScalar("imdb",DoubleType.INSTANCE)
      .addScalar("resolutionName",StringType.INSTANCE)
      .addScalar("languageName",StringType.INSTANCE);
  }
  
  @Override
  public Long saveMovie(MovieDTO movieDTO) {
    Movie movie = Movie.mapFromMovieDTO(movieDTO);
    Transaction transaction = null;
    Session session = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();
      Long movieId = (Long) session.save(movie);
      transaction.commit();
      return movieId;
    } catch (Exception e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
      return null;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  @Override
  public MovieDetailDTO getMovieById(Long id) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      StringBuilder sqlBd = new StringBuilder(MovieDetailDTOSelect);
      sqlBd.append(""
          + " ap.name as resolutionName,"
          + " ap2.name as languageName,"
          + " from movies m "
          + " left join app_params ap on m.resolution = ap.id"
          + " left join app_params ap2 on m.language = ap2.id"
          + " where m.id = :id");
      
      SQLQuery sqlQuery = session.createSQLQuery(sqlBd.toString());
      addScalarMovieDetailDTO(sqlQuery);
      return  (MovieDetailDTO) sqlQuery
          .setParameter("id", id)
          .setResultTransformer(Transformers.aliasToBean(MovieDetailDTO.class))
          .uniqueResult();
    } catch (Exception e) {
      log.error(e);
      return null;
    } finally {
      if(session != null)
        session.close();
    }
  }

  @Override
  public ResultSetDTO<Movie> movieResultSetDTO(MovieSearchDTO movieSearchDTO) {

    
    return null;
  }
  
  


}
