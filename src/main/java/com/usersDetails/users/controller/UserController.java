package com.usersDetails.users.controller;


import com.usersDetails.users.dto.SuccessResponseStructure;
import com.usersDetails.users.model.Address;
import com.usersDetails.users.model.User;

import com.usersDetails.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<SuccessResponseStructure> usersDetails() throws Exception {
        List<User> users = userService.allUsers();
        SuccessResponseStructure successResponseStructure = new SuccessResponseStructure(HttpStatus.OK,201,"User Created Successfully", Optional.ofNullable(users));
        return new ResponseEntity<>(successResponseStructure,HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<SuccessResponseStructure> saveDetails(@RequestBody User user) throws Exception {
        User createdUser = userService.createUser(user);
        SuccessResponseStructure successResponseStructure = new SuccessResponseStructure(HttpStatus.CREATED,201,"User Created Successfully", Optional.ofNullable(createdUser));
        return new ResponseEntity<>(successResponseStructure,HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseStructure> deleteDetailes(@PathVariable("id") UUID id) throws Exception{
        userService.deleteUser(id);
        SuccessResponseStructure successResponseStructure = new SuccessResponseStructure(HttpStatus.OK,202,"User Deleted Successfully",null);
        return new ResponseEntity<>(successResponseStructure,HttpStatus.OK);
    }

    @PostMapping("/address/{id}")
    public ResponseEntity<SuccessResponseStructure> addAddress(@PathVariable("id") UUID id, @RequestBody Address address) throws Exception{
        Address add = userService.addAddress(id,address);
        SuccessResponseStructure successResponseStructure = new SuccessResponseStructure(HttpStatus.OK,201,"Address Saved Successfully",Optional.ofNullable(add));
        return new ResponseEntity<>(successResponseStructure,HttpStatus.OK);
    }



}
