package com.app.mamuvi.dao.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;
import com.app.mamuvi.util.HibernateUtil;

@Repository("appParamDao")
public class AppParamDaoImpl implements AppParamDao{
  
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
  private Logger log = LogManager.getLogger(AppParamDao.class);
  
  private static final String SQL_SELECT = "select ap.name as name,"
            + " ap.id as id,"
            + " ap.type as type,"
            + " ap.description as description"
            + " from app_params ap";

  private void addScalarForSqlSelect(SQLQuery query) {
    query.addScalar("name", StringType.INSTANCE)
      .addScalar("id", LongType.INSTANCE)
      .addScalar("type", StringType.INSTANCE)
      .addScalar("description", StringType.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<AppParamDTO> findAppParamByAPDTO(AppParamSearchDTO apDto) {
    Session session = null;
    String name = apDto.getName();
    String type = apDto.getType();
    List<Long> ids = null;
    if(apDto.getIds() != null) {
      ids = apDto.getIds();
    }
      
        
    try {
      session = sessionFactory.openSession();
      String sql = SQL_SELECT
          + " where 1 = 1 ";
      StringBuilder sqlBuilder = new StringBuilder(sql);
      if(!StringUtils.isAllBlank(name)) {
        sqlBuilder.append(" and name ~ :name");
      }
      if(!StringUtils.isAllBlank(type)) {
        sqlBuilder.append(" and type ~ :type");
      }
      if (ids !=null && !ids.isEmpty()) {
        sqlBuilder.append(" and ap.id in :ids");
      }
      SQLQuery query = session.createSQLQuery(sqlBuilder.toString());
      addScalarForSqlSelect(query);
      if(!StringUtils.isAllBlank(name)) {
        query.setParameter("name", name);
      }
      if(!StringUtils.isAllBlank(type)) {
        query.setParameter("type", type);
      }
      if(ids != null && !ids.isEmpty()) {
        query.setParameterList("ids", ids);
      }
      return query.setResultTransformer(Transformers.aliasToBean(AppParamDTO.class)).list();
    } catch(Exception e) {
      log.error(e);
      return Collections.emptyList();
    } finally {
      if(session != null)
        session.close();
    }
  }

  
}
