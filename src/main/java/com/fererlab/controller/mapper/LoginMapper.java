package com.fererlab.controller.mapper;

import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class LoginMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(LoginRequestDTO.class, UserModel.class)
            .byDefault()
            .register();

        factory.classMap(UserModel.class, LoginResponseDTO.class)
            .byDefault()
            .register();
    }

}
