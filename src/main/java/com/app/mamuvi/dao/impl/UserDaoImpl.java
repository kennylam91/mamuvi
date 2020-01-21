package com.app.mamuvi.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.app.mamuvi.dao.UserDao;
import com.app.mamuvi.dto.UserDTO;
import com.app.mamuvi.model.User;
import com.app.mamuvi.util.HibernateUtil;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  Logger log = LogManager.getLogger(UserDaoImpl.class);
  
  
  @Override
  public Long saveUser(User user) {
    Session session = null;
    Transaction tran = null;
    try {
      session = sessionFactory.openSession();
      tran = session.beginTransaction();
      Long userId = (Long) session.save(user);
      tran.commit();
      return userId;
    } catch (Exception e) {
      log.error(e);
      if(tran != null)
        tran.rollback();
      return null;
    } finally{
      if(session != null)
        session.close();
    }
  }

  @Override
  public Long updateUser(User user) {
    Session session = null;
    Transaction tran = null;
    try {
      session = sessionFactory.openSession();
      tran = session.beginTransaction();
      session.update(user);
      tran.commit();
      return user.getId();
    } catch (Exception e) {
      log.error(e);
      if(tran != null)
        tran.rollback();
      return null;
    } finally{
      if(session != null)
        session.close();
    }
  }

  @Override
  public UserDTO findUserByEmail(String email) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      StringBuilder sqlBd = new StringBuilder()
          .append("select id as id,")
          .append("email as email,")
          .append("watched_list as watchedList,")
          .append("favorite_list as favoriteList,")
          .append("favorite_type as favoriteType")
          .append(" from users")
          .append(" where email = :email");
      SQLQuery query = session.createSQLQuery(sqlBd.toString());
      query.addScalar("id",LongType.INSTANCE);
      query.addScalar("email",StringType.INSTANCE);
      query.addScalar("watchedList",StringType.INSTANCE);
      query.addScalar("favoriteList",StringType.INSTANCE);
      query.addScalar("favoriteType",StringType.INSTANCE);
      query.setParameter("email", email);
      query.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
      return (UserDTO) query.uniqueResult();
    } catch (Exception e) {
      log.error(e);
      return null;
    } finally{
      if(session != null)
        session.close();
    }
  }

}
