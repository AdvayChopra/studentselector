package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * RestController is Spring Stereotype which wires up the class as Spring bean
 * The common pattern is to have Web/HTTP related logic in the controllers
 * and business logic in the service implementation.
 */

@RestController

/**
 * All User related HTTP Requests handled by this class
 */

public class UserController {
    @Autowired
    private UserService userService;

    /**
     *This method is called when the client requests
     * HTTP POST method with URL context /event
     * @param user
     * The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/user")
    public AppUser addUser(@RequestBody AppUser user){
        return userService.saveUser(user);
    }

    /**
     *This method is called when the client requests
     * HTTP POST method with URL context /event
     * @param input
     * The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/login")
    public int verifyCredentials(@RequestBody AppUser input){
        return userService.verifyCredentials(input);
    }

    /**
     *This method is called when the client requests
     * HTTP POST method with URL context /event
     * @param user
     * The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/getOrganiser")
    public boolean isOrganiser (@RequestBody AppUser user){
        return userService.isOrganiser(user);
    }

    /**
     This method is called when the client requests
     * HTTP POST method with URL context /getAdmin
     * @param user
     * The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/getAdmin")
    public boolean isAdmin(@RequestBody AppUser user){
        return userService.isAdmin(user);
    }

    /**
     *This method is called when the client requests
     * HTTP GET method with URL context /subscription
     * @return
     */
    @GetMapping("/user")
    public List<AppUser> findAllUser(){
        return userService.getAllUsers();
    }

    /**
     *This method is called when the client requests
     *HTTP GET method with URL context /userByID/{id}
     * @param id is specified in request URL path
     * @return
     */
    @GetMapping("/userByID/{id}")
    public AppUser findUserById(@PathVariable int id){
        return userService.getUserByID(id);
    }

    /**
     *This method is called when the client requests
     * HTTP DELETE method with URL context /event/{id}
     * @param id is specified in request URL path
     * @return
     */
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
         userService.deleteUserByID(id);
         return "";
    }


}
