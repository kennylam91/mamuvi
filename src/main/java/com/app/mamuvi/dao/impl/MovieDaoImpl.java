package com.app.mamuvi.dao.impl;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
  
  static final String SQL_SELECT_MOVIE_DETAIL_DTO = ""
      + " select "
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
      + " m.director as director,"
      + " m.movie_url as movieUrl,"
      + " m.img_url as imgUrl,"
      + " ap.name as resolutionName,"
      + " ap2.name as languageName"
      ;
  static final String SQL_SELECT_COUNTRYNAME_AND_MOVIETYPENAME = ""
      + " select *,"
      + " ( select string_agg(ap3.name,', ')"
      + " from app_params ap3"
      + " where ap3.id = any("
      + "   select cast(unnest(string_to_array(tb1.country,',')) as int)"
      + "   from table1 tb1 )) as countryName,"
      + " ( select string_agg(ap4.name,', ')"
      + " from app_params ap4"
      + " where ap4.id = any("
      + "   select cast(unnest(string_to_array(tb1.type,',')) as int)"
      + "   from table1 tb1 )) as movieTypeName";
  
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
      .addScalar("languageName",StringType.INSTANCE)
      .addScalar("movieUrl",StringType.INSTANCE)
      .addScalar("imgUrl",StringType.INSTANCE)
      .addScalar("countryName",StringType.INSTANCE)
      .addScalar("movieTypeName",StringType.INSTANCE);
  }
  
  @Override
  public Long saveMovie(Movie movie) {
    ;
    Transaction transaction = null;
    Session session = null;
    try {
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();
      Long movieId = (Long) session.save(movie);
      transaction.commit();
      return movieId;
    } catch (Exception e) {
      log.error(e);
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
      StringBuilder sqlBd = new StringBuilder("with table1 as (");
      sqlBd.append(SQL_SELECT_MOVIE_DETAIL_DTO)
           .append(" "
                + " from movies m "
                + " left join app_params ap on m.resolution = ap.id"
                + " left join app_params ap2 on m.language = ap2.id"
                + " where m.id = :id )")
           .append(SQL_SELECT_COUNTRYNAME_AND_MOVIETYPENAME)
           .append(" from table1");
      
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

  @SuppressWarnings("unchecked")
  @Override
  public ResultSetDTO<Movie> searchMovie(MovieSearchDTO movieS) {
    Session session = null;
    String titleS = movieS.getTitleS();
    Long[] countryS = movieS.getCountryS();
    Long[] typeS = movieS.getTypeS();
    Long recordsPP = movieS.getRecordsPP();
    Long page = movieS.getPage();
    recordsPP = recordsPP!= null? recordsPP : 20L;
    page = page != null? page: 1L;
    ResultSetDTO<Movie> resultSet = new ResultSetDTO<>();
    try {
      session = sessionFactory.openSession();
      StringBuilder sqlBd = new StringBuilder("with table1 as (");
      StringBuilder sqlCountBd = new StringBuilder("select count(*) as totalRecords from movies m");
      sqlCountBd.append(" where 1 = 1");
      sqlBd.append(SQL_SELECT_MOVIE_DETAIL_DTO)
           .append(" "
                + " from movies m "
                + " left join app_params ap on m.resolution = ap.id"
                + " left join app_params ap2 on m.language = ap2.id"
                + " where 1 = 1 ");
      if(!StringUtils.isEmpty(titleS)) {
        sqlBd.append(" and lower(m.en_convert_title) ~ :titleS");
        sqlCountBd.append(" and lower(m.en_convert_title) ~ :titleS");
      }
      if(countryS != null) {
        if(countryS.length > 0) {
          StringBuilder countrySqlBd = new StringBuilder();
          for(int i = 0; i<countryS.length;i++) {
            String countryi = (new StringBuilder("country").append(i)).toString();
            countrySqlBd.append(" and :"+ countryi + " = any("
                + " select cast(unnest(string_to_array(country,',')) as int)"
                + " from movies m1 "
                + " where m.id = m1.id )");
          }
          sqlBd.append(countrySqlBd);
          sqlCountBd.append(countrySqlBd);
        }
      }
      if(typeS != null) {
        if(typeS.length > 0) {
          StringBuilder typeSqlBd = new StringBuilder();
          for(int i = 0; i<typeS.length;i++) {
            String typei = (new StringBuilder("type").append(i)).toString();
            typeSqlBd.append(" and :"+ typei + " = any("
                + " select cast(unnest(string_to_array(movie_type,',')) as int)"
                + " from movies m1 "
                + " where m.id = m1.id )");
          }
          sqlBd.append(typeSqlBd);
          sqlCountBd.append(typeSqlBd);
        }
      }
      
      sqlBd.append(") ")
           .append(SQL_SELECT_COUNTRYNAME_AND_MOVIETYPENAME)
           .append(" from table1")
           .append(" order by title")
           .append(" limit :recordsPP")
           .append(" offset :offset");
      
      SQLQuery sqlQuery = session.createSQLQuery(sqlBd.toString());
      addScalarMovieDetailDTO(sqlQuery);
      
      SQLQuery sqlCountQuery = session.createSQLQuery(sqlCountBd.toString());
      sqlCountQuery.addScalar("totalRecords", LongType.INSTANCE);
      
      if(!StringUtils.isEmpty(titleS)) {
        sqlQuery.setParameter("titleS", StringUtils.lowerCase(titleS));
        sqlCountQuery.setParameter("titleS", StringUtils.lowerCase(titleS));
      }
      if(countryS != null) {
        if(countryS.length > 0) {
          for(int i = 0; i< countryS.length ; i++) {
            String countryi = (new StringBuilder("country").append(i)).toString();
            sqlQuery.setParameter(countryi,countryS[i]);
            sqlCountQuery.setParameter(countryi,countryS[i]);
          }
        }
      }
      if(typeS != null) {
        if(typeS.length > 0) {
          for(int i = 0; i< typeS.length ; i++) {
            String typei = (new StringBuilder("type").append(i)).toString();
            sqlQuery.setParameter(typei,typeS[i]);
            sqlCountQuery.setParameter(typei,typeS[i]);
          }
        }
      }
      sqlQuery.setParameter("recordsPP", recordsPP)
        .setParameter("offset", recordsPP*(page-1));
      resultSet.setList(sqlQuery.setResultTransformer(Transformers.aliasToBean(MovieDetailDTO.class)).list());
      resultSet.setTotalRecords((Long) sqlCountQuery.uniqueResult());
      return resultSet;
    } catch(Exception e) {
      log.error(e);
      return null;
    } finally {
      if(session != null)
        session.close();
    }
   
  }

  @Override
  public void updateMovie(Movie movie) throws Exception {
    Session session = null;
    Transaction tran = null;
    try {
      session = sessionFactory.openSession();
      tran = session.beginTransaction();
      session.update(movie);
      tran.commit();
    } catch (Exception e) {
      log.error(e);
      if(tran != null)
        tran.rollback();
      throw new Exception("Update Fail");
    } finally {
      if(session != null)
        session.close();
    }
  }
  
  


}
