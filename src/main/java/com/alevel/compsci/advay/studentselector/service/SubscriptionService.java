package com.alevel.compsci.advay.studentselector.service;
import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;

import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.UserService;
import com.alevel.compsci.advay.studentselector.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired

    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;


    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();

    }

    public Subscription getSubscriptionByID(int subscriptionID) {
        return subscriptionRepository.findById(subscriptionID).orElse(null);
    }

    public List<Subscription> getSubscriptionByUserID(int userID) {
        return subscriptionRepository.findAllByUserID(userID);
    }

    public List<Event> getEventByUserID(int userID) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByUserID(userID);
        ArrayList<Event> subscribedEvents = new ArrayList<>();
        for (int i = 0; i < subscriptions.size(); i++) {
            int eventID = subscriptions.get(i).getEventID();
            Event event = eventService.getEventByID(eventID);
            subscribedEvents.add(event);
        }
        return subscribedEvents;
    }

    public List<Subscription> getSubscriptionByEventID(int eventID) {
        return subscriptionRepository.findAllByEventID(eventID);
    }

    public String deleteSubscriptionByID(int subscriptionID) {
        subscriptionRepository.deleteById(subscriptionID);
        return "Event Deleted" + subscriptionID;
    }

    public int deleteSubscriptionByUserID(int userID) {
        subscriptionRepository.deleteByUserID(userID);
        return userID;
    }

//    public Subscription updateSelected(int subscriptionID) {
//        Subscription existingSubscription;
//        existingSubscription = subscriptionRepository.findById(subscriptionID).orElse(null);
//        existingSubscription.setSelected(true);
//        return subscriptionRepository.save(existingSubscription);
//    }
    public Subscription updateWeight(Subscription subscription) {

        List<Subscription> existingSubscriptions = subscriptionRepository.findAllByEventID(subscription.getEventID());
        for(int i=0; i<existingSubscriptions.size(); i++) {

                existingSubscriptions.get(i).setWeight(subscription.getWeight());
                return subscriptionRepository.save(existingSubscriptions.get(i));
        }
        return null;


    }
    public Subscription autoUpdateWeight(Subscription subscription) {

        List<Subscription> existingSubscriptions = subscriptionRepository.findAllByEventID(subscription.getEventID());
        for(int i=0; i<existingSubscriptions.size(); i++) {

            existingSubscriptions.get(i).setWeight(subscription.getWeight());
            return subscriptionRepository.save(existingSubscriptions.get(i));
        }
        return null;


    }
}

