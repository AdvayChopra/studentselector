package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.AppUser;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser user);

    List<AppUser> getAllUsers();

    AppUser getUserByID(int userID);

    AppUser getUserBySubscriptionID(int subscriptionID);

    AppUser getUserByUsername(String username);

    String deleteUserByID(int userID);

    int verifyCredentials(AppUser input);

    boolean isOrganiser(AppUser user);

    boolean isAdmin(AppUser user);
}
