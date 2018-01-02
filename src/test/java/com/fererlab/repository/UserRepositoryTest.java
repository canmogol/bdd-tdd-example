package com.fererlab.repository;

import com.fererlab.repository.entity.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest {

    public static final String ID = "1ID";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final UserEntity USER_ENTITY_DEFAULT = new UserEntity(ID, NAME, USERNAME, PASSWORD);
    public static final String ONE = "ONE";
    public static final UserEntity USER_ENTITY_ONE = new UserEntity(ID, ONE, ONE, ONE);

    @Mock
    private UserRepository repository;

    private HashMap<String, UserEntity> users;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        users = new HashMap<String, UserEntity>() {{
            put(USERNAME, USER_ENTITY_DEFAULT);
        }};
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInternalStorage() throws Exception {
        UserRepository localUserRepository = new UserRepository();
        Whitebox.setInternalState(localUserRepository, "users", users);

        Optional<UserEntity> optional = localUserRepository.findByUsernameAndPassword(USERNAME, PASSWORD);
        assertThat("optional should contain an object", optional.isPresent(), is(Boolean.TRUE));

        UserEntity userEntity = optional.get();
        assertThat("should return the user entity", userEntity, is(equalTo(USER_ENTITY_DEFAULT)));
    }

    @Test
    public void testFindByUsernameAndPassword() throws Exception {
        Mockito.when(repository.findByUsernameAndPassword(USERNAME, PASSWORD))
            .thenReturn(Optional.of(USER_ENTITY_ONE));
        Optional<UserEntity> optional = repository.findByUsernameAndPassword(USERNAME, PASSWORD);
        assertThat("optional should contain an object", optional.isPresent(), is(Boolean.TRUE));

        UserEntity userEntity = optional.get();
        assertThat("should return the user entity", userEntity, is(equalTo(USER_ENTITY_ONE)));
    }

}