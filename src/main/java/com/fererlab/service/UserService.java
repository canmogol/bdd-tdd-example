package com.fererlab.service;

import com.fererlab.repository.UserRepository;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;

import java.util.Optional;

public class UserService {

    private UserRepository repository;

    private MapperFacade mapper;

    public Optional<UserModel> findUser(UserModel model) {
        return repository.findByUsernameAndPassword(model.getUsername(), model.getPassword())
            .map(userEntity -> mapper.map(userEntity, UserModel.class));
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void setMapper(MapperFacade mapper) {
        this.mapper = mapper;
    }
}
