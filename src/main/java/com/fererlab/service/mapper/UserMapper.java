package com.fererlab.service.mapper;

import com.fererlab.repository.entity.UserEntity;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(UserModel.class, UserEntity.class)
            .byDefault()
            .register();

        factory.classMap(UserEntity.class, UserModel.class)
            .byDefault()
            .register();
    }

}
