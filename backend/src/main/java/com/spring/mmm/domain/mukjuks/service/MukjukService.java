package com.spring.mmm.domain.mukjuks.service;

import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.service.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MukjukService {

    MukjukResponse findAllMukjuks(Long groupId, UserDetailsImpl users);


}
