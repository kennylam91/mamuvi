package com.app.mamuvi.dao;

import java.util.List;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;

public interface AppParamDao {

  List<AppParamDTO> findAppParamByAPDTO(AppParamSearchDTO apDto);
  
}
