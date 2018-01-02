package com.fererlab.controller;

import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.service.UserService;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginControllerTest {

    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserModel REQUEST_USER_MODEL = new UserModel(null, USERNAME, PASSWORD);
    public static final UserModel RESPONSE_USER_MODEL = new UserModel(NAME, null, null);
    public static final LoginRequestDTO REQUEST_DTO = new LoginRequestDTO(USERNAME, PASSWORD);
    public static final LoginResponseDTO SUCCESS_RESPONSE_DTO = new LoginResponseDTO(Boolean.TRUE, NAME);
    public static final LoginResponseDTO FAIL_RESPONSE_DTO = new LoginResponseDTO(Boolean.FALSE, null);

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;

    @Mock
    private MapperFacade mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() throws Exception {

        Mockito.when(mapper.map(REQUEST_DTO, UserModel.class))
            .thenReturn(REQUEST_USER_MODEL);
        Mockito.when(mapper.map(RESPONSE_USER_MODEL, LoginResponseDTO.class))
            .thenReturn(SUCCESS_RESPONSE_DTO);

        Mockito.when(userService.findUser(REQUEST_USER_MODEL))
            .thenReturn(Optional.of(RESPONSE_USER_MODEL));

        LoginResponseDTO responseDTO = loginController.login(REQUEST_DTO);
        assertThat("responseDTO should be successful", responseDTO.isLoginSuccessful(), is(Boolean.TRUE));
        assertThat("responseDTO should have same name", responseDTO.getName(), is(equalTo(NAME)));
        assertThat("responseDTO should be same with successful response", responseDTO, is(equalTo(SUCCESS_RESPONSE_DTO)));
    }

    @Test
    public void testLoginFail() throws Exception {

        Mockito.when(mapper.map(REQUEST_DTO, UserModel.class))
            .thenReturn(REQUEST_USER_MODEL);
        Mockito.when(mapper.map(RESPONSE_USER_MODEL, LoginResponseDTO.class))
            .thenReturn(SUCCESS_RESPONSE_DTO);

        Mockito.when(userService.findUser(REQUEST_USER_MODEL))
            .thenReturn(Optional.empty());

        LoginResponseDTO responseDTO = loginController.login(REQUEST_DTO);
        assertThat("responseDTO should be successful", responseDTO.isLoginSuccessful(), is(Boolean.FALSE));
        assertThat("responseDTO should have same name", responseDTO.getName(), nullValue());
        assertThat("responseDTO should be same with fail", responseDTO, is(equalTo(FAIL_RESPONSE_DTO)));
    }

}