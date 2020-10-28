package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.SubscriberWeighting;
import com.alevel.compsci.advay.studentselector.entity.Subscription;

import java.util.List;

public interface ISubscriptionService {
    Subscription saveSubscription(Subscription subscription);

    List<Subscription> getAllSubscriptions();

    Subscription getSubscriptionByID(int subscriptionID);

    List<Subscription> getSubscriptionByUserID(int userID);

    List<SubscriberWeighting> findSubscriberWeightingsByEventID(int eventID);

    List<Event> getEventByUserID(int userID);

    List<Subscription> getSubscriptionByEventID(int eventID);

    String deleteSubscriptionByID(int subscriptionID);

    int deleteSubscriptionByUserID(int userID);

    Subscription updateWeight(Subscription subscription);
}
