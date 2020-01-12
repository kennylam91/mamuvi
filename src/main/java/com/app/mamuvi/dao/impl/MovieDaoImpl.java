package com.app.mamuvi.dao.impl;

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
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.util.HibernateUtil;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

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

  @SuppressWarnings("deprecation")
  @Override
  public MovieDetailDTO getMovieById(Long id) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      String sql = "select "
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
          + " ap.name as resolutionName,"
          + " ap2.name as languageName"
          + " from movies m "
          + " left join app_params ap on m.resolution = ap.id"
          + " left join app_params ap2 on m.language = ap2.id"
          + " where m.id = :id";
      return  (MovieDetailDTO) session.createSQLQuery(sql)
          .addScalar("id", LongType.INSTANCE)
          .addScalar("title",StringType.INSTANCE)
          .addScalar("description",StringType.INSTANCE)
          .addScalar("releasedDate",LocalDateType.INSTANCE)
          .addScalar("country",StringType.INSTANCE)
          .addScalar("length", LongType.INSTANCE)
          .addScalar("resolution",LongType.INSTANCE)
          .addScalar("language",LongType.INSTANCE)
          .addScalar("type",StringType.INSTANCE)
          .addScalar("prodCompanies",StringType.INSTANCE)
          .addScalar("imdb",DoubleType.INSTANCE)
          .addScalar("resolutionName",StringType.INSTANCE)
          .addScalar("languageName",StringType.INSTANCE)
          .setParameter("id", id)
          .setResultTransformer(Transformers.aliasToBean(MovieDetailDTO.class))
          .uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if(session != null)
        session.close();
    }
  }
  
  


}
