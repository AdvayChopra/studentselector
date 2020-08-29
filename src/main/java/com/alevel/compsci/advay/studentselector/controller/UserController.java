package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Login;
import com.alevel.compsci.advay.studentselector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public AppUser addUser(@RequestBody AppUser user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public int verifyCredentials(@RequestBody Login input){
        return userService.verifyCredentials(input);
    }
    @PostMapping("/getUser")
    public boolean verifyUser(@RequestBody AppUser user){
        return userService.verifyUser(user);
    }

    @GetMapping("/user")
    public List<AppUser> findAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/userByID/{id}")
    public AppUser findUserById(@PathVariable int id){
        return userService.getUserByID(id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
         userService.deleteUserByID(id);
         return "";
    }


}
