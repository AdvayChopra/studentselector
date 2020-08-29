package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import com.alevel.compsci.advay.studentselector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubscriptionController {
    List<Subscription> subscriptions;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;

    @PostMapping("/subscription")
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    @GetMapping("/subscription")
    public List<Subscription> findAllSubscription() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/subscriptionByEventID/{id}")
    public List<Subscription> findSubscriptionByEventId(@PathVariable int id) {
        return subscriptionService.getSubscriptionByEventID(id);

    }

    @GetMapping("/selectedUsersByEventID/{id}")
    public List<AppUser> findSelectedUsersByEventID(@PathVariable int id) {
       subscriptions = subscriptionService.getSubscriptionByEventID(id);
       ArrayList<AppUser> selectedUsers = new ArrayList<>();
       for(int i =0; i <subscriptions.size(); i++){
           boolean selected = subscriptions.get(i).getSelected();

           if(selected == true){
               int userID = subscriptions.get(i).getUserID();
               AppUser user = userService.getUserByID(userID);
               selectedUsers.add(user);
           }
       }
    return selectedUsers;
    }
    @GetMapping("/subscribedUsersByEventID/{id}")
    public List<AppUser> findSubscribedUsers(@PathVariable int id) {
        subscriptions = subscriptionService.getSubscriptionByEventID(id);
        ArrayList<AppUser> subscribedUsers = new ArrayList<>();

        for(int i =0; i <subscriptions.size(); i++){


            int userID = subscriptions.get(i).getUserID();
            AppUser user = userService.getUserByID(userID);
            subscribedUsers.add(user);

        }
        return subscribedUsers;
    }


    @PostMapping("/updateWeight")
    public Subscription updateWeight(@RequestBody Subscription subscription) {
        return subscriptionService.updateWeight(subscription);
    }

    @DeleteMapping("/subscriptionByID/{id}")
    public String deleteSubscriptionById(@PathVariable int id) {
        return subscriptionService.deleteSubscriptionByID(id);

    }

}
