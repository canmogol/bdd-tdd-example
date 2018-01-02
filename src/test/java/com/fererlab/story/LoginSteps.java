package com.fererlab.story;

import com.fererlab.controller.LoginController;
import com.fererlab.controller.dto.LoginRequestDTO;
import com.fererlab.controller.dto.LoginResponseDTO;
import com.fererlab.controller.mapper.LoginMapper;
import com.fererlab.repository.UserRepository;
import com.fererlab.repository.entity.UserEntity;
import com.fererlab.service.UserService;
import com.fererlab.service.mapper.UserMapper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    public static final String ID = "1ID";
    public static final String NAME = "name";

    private String username;
    private String password;

    private LoginResponseDTO loginResponseDTO;

    private UserRepository repository;
    private UserService userService;
    private LoginController loginController;

    @Given("a login operation")
    public void givenALoginOperation() {
        repository = new UserRepository();

        userService = new UserService();
        userService.setMapper(new UserMapper());
        userService.setRepository(repository);

        loginController = new LoginController();
        loginController.setMapper(new LoginMapper());
        loginController.setUserService(userService);
    }

    @When("I type username $username")
    public void iTypeUsername(String username) {
        this.username = username;
    }

    @When("I type password $password")
    public void iTypePassword(String password) {
        this.password = password;
    }

    @When("I call login")
    public void iCallLogin() {
        HashMap<String, UserEntity> users = new HashMap<String, UserEntity>() {{
            put(username, new UserEntity(ID, NAME, username, password));
        }};
        repository.setUsers(users);

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(this.username, this.password);
        loginResponseDTO = loginController.login(loginRequestDTO);
    }

    @Then("I should get a successful login response")
    public void iShouldGetASuccessfulLoginResponse() {
        String errorMessage = String.format("username %1s and password %2s should be able to login!", username, password);
        assertThat(errorMessage, loginResponseDTO.isLoginSuccessful(), is(equalTo(Boolean.TRUE)));
    }

}
