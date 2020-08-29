package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Login;
import com.alevel.compsci.advay.studentselector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired

    private UserRepository userRepository;
    private EventService eventService;
    private SubscriptionService subscriptionService;

    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }


    public List<AppUser> getAllUsers() {
        return userRepository.findAll();

    }

    public AppUser getUserByID(int userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public AppUser getUserBySubscriptionID(int subscriptionID) {
        return userRepository.findById(subscriptionID).orElse(null);
    }
    public AppUser getUserByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    public String deleteUserByID(int userID) {
        userRepository.deleteById(userID);

        return "User Deleted " + userID;
    }

    public int verifyCredentials(Login input) {


        String inputUsername = input.getUsername();
        String inputpassword = input.getPassword();

        String passwordDB = userRepository.findAllByUsername(inputUsername).getPassword();
        if (passwordDB.equals(inputpassword) && passwordDB != null) {

            return userRepository.findAllByUsername(inputUsername).getUserID();
        } else {
            return 0;
        }
    }

    public boolean verifyUser(AppUser user) {

        String inputUsername = user.getUsername();
        boolean organiser = userRepository.findAllByUsername(inputUsername).getOrganiser();
        return organiser;
    }


}