package com.alevel.compsci.advay.studentselector.service;
import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;

import com.alevel.compsci.advay.studentselector.entity.SubscriberWeighting;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.UserService;
import com.alevel.compsci.advay.studentselector.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA implementation of the Subscription Service Interface
 * The service is  a wrapper around the Subscription respository
 * to implement all the business logic.
 * The common pattern is not to have business logic in the
 * controllers.
 */
@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired

    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    /**
     * The method implement business logic before
     * saving the event into the repository
     * @param subscription
     * @return
     */
    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();

    }

    @Override
    public Subscription getSubscriptionByID(int subscriptionID) {
        return subscriptionRepository.findById(subscriptionID).orElse(null);
    }

    @Override
    public List<Subscription> getSubscriptionByUserID(int userID) {
        return subscriptionRepository.findAllByUserID(userID);
    }

    @Override
    public List<SubscriberWeighting> findSubscriberWeightingsByEventID(int eventID){
        List<Subscription> subscriptions = getSubscriptionByEventID(eventID);
        ArrayList<SubscriberWeighting> subscriberWeightings = new ArrayList<>();
        //Loops through all subscriptions for each Event
        for (int i = 0; i < subscriptions.size(); i++) {
            SubscriberWeighting weighting=new SubscriberWeighting();
            int userID = subscriptions.get(i).getUserID();
            AppUser user = userService.getUserByID(userID);
            weighting.setUsername(user.getUsername());
            weighting.setWeight(1);
            //Adds the weight to the subscriberWeightings list
            subscriberWeightings.add(weighting);

        }
        return subscriberWeightings;
    }

    @Override
    public List<Event> getEventByUserID(int userID) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByUserID(userID);
        ArrayList<Event> subscribedEvents = new ArrayList<>();
        //Loops through all subscriptions for a user
        for (int i = 0; i < subscriptions.size(); i++) {
            int eventID = subscriptions.get(i).getEventID();
            Event event = eventService.getEventByID(eventID);
            //Adds the event to the subscribedEvents list.
            subscribedEvents.add(event);
        }
        return subscribedEvents;
    }

    @Override
    public List<Subscription> getSubscriptionByEventID(int eventID) {
        return subscriptionRepository.findAllByEventID(eventID);
    }

    @Override
    public String deleteSubscriptionByID(int subscriptionID) {
        subscriptionRepository.deleteById(subscriptionID);
            return "Event Deleted" + subscriptionID;
    }

    @Override
    public int deleteSubscriptionByUserID(int userID) {
        subscriptionRepository.deleteByUserID(userID);
        return userID;
    }

    /**
     * This method implements the logic
     * for updating the weight of a subscription
     * @param subscription
     * @return
     */
    @Override
    public Subscription updateWeight(Subscription subscription) {

        List<Subscription> existingSubscriptions = subscriptionRepository.findAllByEventID(subscription.getEventID());
        for(int i=0; i<existingSubscriptions.size(); i++) {

                existingSubscriptions.get(i).setWeight(subscription.getWeight());
                return subscriptionRepository.save(existingSubscriptions.get(i));
        }
        return null;
    }
}

