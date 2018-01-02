package com.fererlab.service.mapper;

import com.fererlab.repository.entity.UserEntity;
import com.fererlab.service.model.UserModel;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserMapperTest {

    public static final String ID = "1ID";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserEntity USER_ENTITY = new UserEntity(ID, NAME, USERNAME, PASSWORD);
    public static final UserEntity USER_ENTITY_NULL_ID = new UserEntity(null, NAME, USERNAME, PASSWORD);
    public static final UserModel USER_MODEL = new UserModel(NAME, USERNAME, PASSWORD);

    @Test
    public void testConfigure() throws Exception {
        MapperFacade mapper = new UserMapper();

        UserModel userModel = mapper.map(USER_ENTITY, UserModel.class);
        assertThat("userModel should be identical", userModel, is(equalTo(USER_MODEL)));

        UserEntity userEntity = mapper.map(USER_MODEL, UserEntity.class);
        assertThat("userEntity should be identical", userEntity, is(equalTo(USER_ENTITY_NULL_ID)));
    }
}