package com.fererlab.repository;

import com.fererlab.repository.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private Map<String, UserEntity> users = new HashMap<>();

    public Optional<UserEntity> findByUsernameAndPassword(String username, String password) {
        return users.entrySet().stream()
            .filter(entry -> entry.getKey().equals(username) && entry.getValue().getPassword().equals(password))
            .map(Map.Entry::getValue)
            .findFirst();
    }

    public void setUsers(Map<String, UserEntity> users) {
        this.users = users;
    }

}
