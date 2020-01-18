package com.app.mamuvi.service;

import java.util.List;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;

public interface AppParamService {

  
  List<AppParamDTO> findAppParamsByAPDTO(AppParamSearchDTO apDto);
}
