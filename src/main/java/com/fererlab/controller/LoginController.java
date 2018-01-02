package com.fererlab.controller;

import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.service.UserService;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;

public class LoginController {

    private UserService userService;

    private MapperFacade mapper;

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        UserModel userModel = mapper.map(requestDTO, UserModel.class);
        return userService.findUser(userModel)
            .map(um -> {
                LoginResponseDTO loginResponseDTO = mapper.map(um, LoginResponseDTO.class);
                loginResponseDTO.setLoginSuccessful(true);
                return loginResponseDTO;
            })
            .orElse(new LoginResponseDTO());
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMapper(MapperFacade mapper) {
        this.mapper = mapper;
    }
}
