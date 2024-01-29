package com.usersDetails.users.service;

import com.usersDetails.users.model.Address;
import com.usersDetails.users.model.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {

    public User createUser(User user) throws Exception;

    void deleteUser(UUID id);

    List<User> allUsers();

    Address addAddress(UUID id, Address address);
}
