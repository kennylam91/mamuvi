package com.app.mamuvi.dao;

import java.util.List;
import com.app.mamuvi.dto.AppParamDTO;

public interface AppParamDao {

  List<AppParamDTO> findAppParamsByIds(List<Long> Ids);
  
  AppParamDTO findAppParamByNameAndType(String name, String type);
  
}
