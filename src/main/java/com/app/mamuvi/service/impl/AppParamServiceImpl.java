package com.app.mamuvi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;
import com.app.mamuvi.service.AppParamService;

@Service
public class AppParamServiceImpl implements AppParamService{

  @Autowired
  AppParamDao appParamDao;

  @Override
  public List<AppParamDTO> findAppParamsByAPDTO(AppParamSearchDTO apDto) {
    return appParamDao.findAppParamByAPDTO(apDto); 
  }



}
