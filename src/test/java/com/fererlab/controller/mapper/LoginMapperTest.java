package com.fererlab.controller.mapper;

import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginMapperTest {

    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserModel USER_MODEL_REQ = new UserModel(null, USERNAME, PASSWORD);
    public static final UserModel USER_MODEL_RES = new UserModel(NAME, null, null);
    public static final LoginRequestDTO REQUEST_DTO = new LoginRequestDTO(USERNAME, PASSWORD);
    public static final LoginResponseDTO RESPONSE_DTO = new LoginResponseDTO(Boolean.FALSE, NAME);

    @Test
    public void testConfigure() throws Exception {
        MapperFacade mapper = new LoginMapper();

        UserModel userModel = mapper.map(REQUEST_DTO, UserModel.class);
        assertThat("userModel should be identical", userModel, is(equalTo(USER_MODEL_REQ)));

        LoginResponseDTO responseDTO = mapper.map(USER_MODEL_RES, LoginResponseDTO.class);
        assertThat("userEntity should be identical", responseDTO, is(equalTo(RESPONSE_DTO)));
    }

}