package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.SubscriberWeighting;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.EventService;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import com.alevel.compsci.advay.studentselector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * RestController is Spring Stereotype which wires up the class as Spring bean
 * The common pattern is to have Web/HTTP related logic in the controllers
 * and business logic in the service implementation.
 */

@RestController

/**
 * All Subscription related HTTP Requests handled by this class
 */
public class SubscriptionController {
    //List<Subscription> subscriptions;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    /**
     * This method is called when the client requests
     * HTTP POST method with URL context /event
     *
     * @param subscription The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/subscription")
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /subscription
     *
     * @return
     */
    @GetMapping("/subscription")
    public List<Subscription> findAllSubscription() {
        return subscriptionService.getAllSubscriptions();
    }

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /subscriptionByEventID/{id}
     *
     * @param id is specified in request URL path
     * @return
     */
    @GetMapping("/subscriptionByEventID/{id}")
    public List<Subscription> findSubscriptionByEventId(@PathVariable int id) {
        return subscriptionService.getSubscriptionByEventID(id);

    }

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /selectedUsersByEventID/{id}
     * It finds all the selected users for an Event
     * @param id is specified in request URL path
     * @return
     */
    @GetMapping("/selectedUsersByEventID/{id}")
    public List<AppUser> findSelectedUsersByEventID(@PathVariable int id) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionByEventID(id);
        ArrayList<AppUser> selectedUsers = new ArrayList<>();
        //Goes through all subscriptions
        for (int i = 0; i < subscriptions.size(); i++) {
            boolean selected = subscriptions.get(i).getSelected();
            //If subscription is selected then add it to selectedUsers list.
            if (selected == true) {
                int userID = subscriptions.get(i).getUserID();
                AppUser user = userService.getUserByID(userID);
                selectedUsers.add(user);
            }
        }
        return selectedUsers;
    }

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /subscribedUsersByEventID/{id}
     *
     * @param id is specified in request URL path
     * @return
     */
    @GetMapping("/subscribedUsersByEventID/{id}")
    public List<SubscriberWeighting> findSubscribedUsers(@PathVariable int id) {
        List<SubscriberWeighting> subscribedUsers = subscriptionService.findSubscriberWeightingsByEventID(id);

        return subscribedUsers;
    }

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /subscriptionWeightingsByEventID/{eventID}
     * It automatically finding the subscription weighting
     * based on whether a user has been previously selected or not
     *
     * @param eventID is specified in request URL path
     * @return
     */
    @GetMapping("/subscriptionWeightingsByEventID/{eventID}")
    public List<SubscriberWeighting> findSubscriptionWeightingsByEventID(@PathVariable int eventID) {

        List<Subscription> subscriptions = subscriptionService.getSubscriptionByEventID(eventID);
        ArrayList<AppUser> subscribedUsers = new ArrayList<>();
        ArrayList<SubscriberWeighting> subscriberWeightings = new ArrayList<>();
        //Adds all subscribers to the subscribedUsers list
        for (int i = 0; i < subscriptions.size(); i++) {
            int userID = subscriptions.get(i).getUserID();
            AppUser user = userService.getUserByID(userID);
            subscribedUsers.add(user);
        }
        //Gets all subscriptions for user
        for (int i = 0; i < subscriptions.size(); i++) {
            ArrayList<Event> notSelectedEvents = new ArrayList<>();
            ArrayList<Event> selectedEvents = new ArrayList<>();
            List<Subscription> userSubscriptions = subscriptionService.getSubscriptionByUserID(subscriptions.get(i).getUserID());
            for (int j = 0; j < userSubscriptions.size(); j++) {
                //Checks whether the subscription has selected as true or false
                if (userSubscriptions.get(j).getSelected() != true) {
                    //If event status is false add to notSelectedEvents list
                    if (!eventService.getEventByID(userSubscriptions.get(j).getEventID()).getEventStatus()) {
                        notSelectedEvents.add(eventService.getEventByID(userSubscriptions.get(j).getEventID()));
                    }
                    //Add to selectedEvents list
                } else {
                    selectedEvents.add(eventService.getEventByID(userSubscriptions.get(j).getEventID()));
                }
            }
            SubscriberWeighting weighting = new SubscriberWeighting();
            weighting.setUserID(subscribedUsers.get(i).getUserID());
            weighting.setUsername(subscribedUsers.get(i).getUsername());
            //Set weightings dependent on size of both lists
            if (selectedEvents.size() < notSelectedEvents.size()) {
                //Less than 50%
                weighting.setWeight(2);
            } else {
                //More than 50%
                weighting.setWeight(1);
            }
            subscriberWeightings.add(weighting);
        }
        return subscriberWeightings;
    }

    /**
     * This method is called when the client requests
     * HTTP POST method with URL context /updateWeight
     *
     * @param subscription The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PostMapping("/updateWeight")
    public Subscription updateWeight(@RequestBody Subscription subscription) {
        return subscriptionService.updateWeight(subscription);
    }

    /**
     * This method is called when the client requests
     * HTTP DELETE method with URL context /subscriptionByEventID/{id}
     *
     * @param id is specified in request URL path
     * @return
     */
    @DeleteMapping("/subscriptionByID/{id}")
    public String deleteSubscriptionById(@PathVariable int id) {
        return subscriptionService.deleteSubscriptionByID(id);

    }

}
