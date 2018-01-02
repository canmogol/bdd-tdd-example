package com.fererlab.controller;

import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.controller.mapper.LoginMapper;
import com.fererlab.service.UserService;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginControllerNoMockTest {

    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserModel RESPONSE_USER_MODEL = new UserModel(NAME, null, null);
    public static final LoginRequestDTO REQUEST_DTO = new LoginRequestDTO(USERNAME, PASSWORD);
    public static final LoginResponseDTO SUCCESS_RESPONSE_DTO = new LoginResponseDTO(Boolean.TRUE, NAME);
    public static final LoginResponseDTO FAIL_RESPONSE_DTO = new LoginResponseDTO(Boolean.FALSE, null);

    private LoginController loginController;

    private UserService userServiceSuccess;
    private UserService userServiceFail;

    private MapperFacade mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new LoginMapper();
        userServiceSuccess = new UserService(){
            @Override
            public Optional<UserModel> findUser(UserModel model) {
                return Optional.of(RESPONSE_USER_MODEL);
            }
        };
        userServiceFail = new UserService(){
            @Override
            public Optional<UserModel> findUser(UserModel model) {
                return Optional.empty();
            }
        };
    }

    @Test
    public void testLoginSuccess() throws Exception {
        loginController = new LoginController();
        loginController.setMapper(mapper);
        loginController.setUserService(userServiceSuccess);

        LoginResponseDTO responseDTO = loginController.login(REQUEST_DTO);
        assertThat("responseDTO should be successful", responseDTO.isLoginSuccessful(), is(Boolean.TRUE));
        assertThat("responseDTO should have same name", responseDTO.getName(), is(equalTo(NAME)));
        assertThat("responseDTO should be same with successful response", responseDTO, is(equalTo(SUCCESS_RESPONSE_DTO)));
    }

    @Test
    public void testLoginFail() throws Exception {
        loginController = new LoginController();
        loginController.setMapper(mapper);
        loginController.setUserService(userServiceFail);

        LoginResponseDTO responseDTO = loginController.login(REQUEST_DTO);
        assertThat("responseDTO should be successful", responseDTO.isLoginSuccessful(), is(Boolean.FALSE));
        assertThat("responseDTO should have same name", responseDTO.getName(), nullValue());
        assertThat("responseDTO should be same with fail", responseDTO, is(equalTo(FAIL_RESPONSE_DTO)));
    }

}