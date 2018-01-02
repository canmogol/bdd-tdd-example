package com.fererlab.service;

import com.fererlab.repository.UserRepository;
import com.fererlab.repository.entity.UserEntity;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserServiceTest {

    public static final String ID = "1ID";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserModel USER_MODEL = new UserModel(NAME, USERNAME, PASSWORD);
    public static final UserEntity USER_ENTITY = new UserEntity(ID, NAME, USERNAME, PASSWORD);

    @Mock
    private UserService userService;

    @Mock
    private UserRepository localUserRepository;

    @Mock
    private MapperFacade mapperFacade;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUser() throws Exception {
        Mockito.when(userService.findUser(USER_MODEL))
            .thenReturn(Optional.of(USER_MODEL));

        Optional<UserModel> optional = userService.findUser(USER_MODEL);
        assertThat("optional should contain an object", optional.isPresent(), is(Boolean.TRUE));

        UserModel userModel = optional.get();
        assertThat("should return the user model", userModel, is(equalTo(USER_MODEL)));
    }

    @Test
    public void testFindUserInternalRepository() throws Exception {
        // create local service
        UserService localUserService = new UserService();

        // mock repository
        Mockito.when(localUserRepository.findByUsernameAndPassword(USERNAME, PASSWORD))
            .thenReturn(Optional.of(USER_ENTITY));
        Whitebox.setInternalState(localUserService, "repository", localUserRepository);

        // mock mapper
        Mockito.when(mapperFacade.map(USER_ENTITY, UserModel.class))
            .thenReturn(USER_MODEL);
        Whitebox.setInternalState(localUserService, "mapper", mapperFacade);

        // assert
        Optional<UserModel> optional = localUserService.findUser(USER_MODEL);
        assertThat("optional should contain an object", optional.isPresent(), is(Boolean.TRUE));

        UserModel userModel = optional.get();
        assertThat("should return the user model", userModel, is(equalTo(USER_MODEL)));
    }
}