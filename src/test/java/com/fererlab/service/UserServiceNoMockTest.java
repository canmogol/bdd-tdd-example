package com.fererlab.service;

import com.fererlab.repository.UserRepository;
import com.fererlab.repository.entity.UserEntity;
import com.fererlab.service.mapper.UserMapper;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserServiceNoMockTest {

    public static final String ID = "1ID";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserModel USER_MODEL = new UserModel(NAME, USERNAME, PASSWORD);
    public static final UserEntity USER_ENTITY = new UserEntity(ID, NAME, USERNAME, PASSWORD);

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        UserRepository localUserRepository = new UserRepository();
        localUserRepository.setUsers(new HashMap<String, UserEntity>() {{
            put(USERNAME, USER_ENTITY);
        }});
        MapperFacade mapperFacade = new UserMapper();
        userService = new UserService();
        userService.setMapper(mapperFacade);
        userService.setRepository(localUserRepository);
    }

    @Test
    public void testFindUser() throws Exception {
        Optional<UserModel> optional = userService.findUser(USER_MODEL);
        assertThat("optional should contain an object", optional.isPresent(), is(Boolean.TRUE));

        UserModel userModel = optional.get();
        assertThat("should return the user model", userModel, is(equalTo(USER_MODEL)));
    }

}