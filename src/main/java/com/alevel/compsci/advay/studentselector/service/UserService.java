package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JPA implementation of the User Service Interface
 * The service is a wrapper around the User respository
 * to implement all the business logic.
 * The common pattern is not to have business logic in the
 * controllers.
 */
@Service
public class UserService implements IUserService {
    @Autowired

    private UserRepository userRepository;
    private EventService eventService;
    private SubscriptionService subscriptionService;

    /**
     * The method implement business logic before
     * saving the event into the repository
     * @param user
     * @return
     */
    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public AppUser getUserByID(int userID) {
        return userRepository.findById(userID).orElse(null);
    }

    @Override
    public AppUser getUserBySubscriptionID(int subscriptionID) {
        return userRepository.findById(subscriptionID).orElse(null);
    }
    @Override
    public AppUser getUserByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    @Override
    public String deleteUserByID(int userID) {
        userRepository.deleteById(userID);

        return "User Deleted " + userID;
    }

    /**
     * This method implements logic for
     * verifying credentials inputted by the user
     * @param input
     * @return
     */
    @Override
    public int verifyCredentials(AppUser input) {


        String inputUsername = input.getUsername();
        String inputpassword = input.getPassword();

        String passwordDB = userRepository.findAllByUsername(inputUsername).getPassword();
        if (passwordDB.equals(inputpassword) && passwordDB != null) {

            return userRepository.findAllByUsername(inputUsername).getUserID();
        } else {
            return 0;
        }
    }

    /**
     * This method implements logic for
     * checking whether the user is an organiser
     * @param user
     * @return
     */
    @Override
    public boolean isOrganiser(AppUser user) {

        String inputUsername = user.getUsername();
        boolean organiser = userRepository.findAllByUsername(inputUsername).getOrganiser();
        return organiser;
    }

    /**
     * This method implements logic for
     * checking whether the user is an admin
     * @param user
     * @return
     */
    @Override
    public boolean isAdmin(AppUser user) {

        String inputUsername = user.getUsername();
        boolean organiser = userRepository.findAllByUsername(inputUsername).getAdmin();
        return organiser;
    }
}