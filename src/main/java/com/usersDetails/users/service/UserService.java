package com.usersDetails.users.service;

import com.usersDetails.users.exception.EmailExceptions;
import com.usersDetails.users.exception.UserExceptions;
import com.usersDetails.users.model.Address;
import com.usersDetails.users.model.User;
import com.usersDetails.users.repositories.AddressRepository;
import com.usersDetails.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    @Override
    public User createUser(User user) throws EmailExceptions.EmailAlreadyExistsExceptions {


        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist != null){
            throw new EmailExceptions.EmailAlreadyExistsExceptions("Email Already Exist");
        }


        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(UUID id) {

       Optional<User>  isExist = userRepository.findById(id);

        System.out.println(isExist);

        if (isExist.isEmpty()){
            throw new UserExceptions.UserNotFound("User not found!");
        }

        userRepository.deleteById(id);
    }

    @Override
    public List<User> allUsers() {

        return userRepository.findAll();
    }

    @Override
    public Address addAddress(UUID id, Address address) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserExceptions.UserNotFound("User not found!");

        } else {
            User user = optionalUser.get();

            address.setUser(user);

            Address savedAddress = addressRepository.save(address);

            user.getAddresses().add(savedAddress);

            userRepository.save(user);
             return savedAddress;

        }
    }

}
