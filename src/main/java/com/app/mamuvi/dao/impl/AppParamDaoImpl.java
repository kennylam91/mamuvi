package com.app.mamuvi.dao.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.util.HibernateUtil;

@Repository("appParamDao")
public class AppParamDaoImpl implements AppParamDao{
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @SuppressWarnings("unchecked")
  @Override
  public List<AppParamDTO> findAppParamsByIds(List<Long> ids) {
    Session session = null;
    if(ids.isEmpty()) 
      return Collections.emptyList();
    try {
      session = sessionFactory.openSession();
      String sql = "select ap.name as name,"
          + " ap.id as id,"
          + " ap.type as type,"
          + " ap.description as description"
          + " from app_params ap"
          + " where ap.id in :ids";
      return session.createSQLQuery(sql)
      .addScalar("name",StringType.INSTANCE)
      .addScalar("id",LongType.INSTANCE)
      .addScalar("type",StringType.INSTANCE)
      .addScalar("description",StringType.INSTANCE)
      .setParameterList("ids", ids)
      .setResultTransformer(Transformers.aliasToBean(AppParamDTO.class))
      .list();
    } catch(Exception e){
      e.printStackTrace();
      return Collections.EMPTY_LIST;
    } finally {
      if(null != session)
        session.close();
    }
  }

  
}
