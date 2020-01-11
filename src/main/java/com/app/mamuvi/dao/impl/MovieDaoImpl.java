package com.app.mamuvi.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.util.HibernateUtil;

@Repository
public class MovieDaoImpl implements MovieDao {

  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public Long saveMovie(Movie movie) {

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
  public MovieDTO getMovieById(Long id) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      String sql = "select "
          + " m.id as id, "
          + " m.title as title, "
          + " m.description as description"
          + " from movies m where m.id = :id";
      return (MovieDTO) session.createSQLQuery(sql)
          .addScalar("id", LongType.INSTANCE)
          .addScalar("title",StringType.INSTANCE)
          .addScalar("description",StringType.INSTANCE)
          .setParameter("id", id)
          .setResultTransformer(Transformers.aliasToBean(MovieDTO.class))
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
