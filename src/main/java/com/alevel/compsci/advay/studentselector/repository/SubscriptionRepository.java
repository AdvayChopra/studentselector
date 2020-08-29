package com.alevel.compsci.advay.studentselector.repository;

import com.alevel.compsci.advay.studentselector.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {


    List<Subscription> findAllByEventID(int eventID);



    List<Subscription> findAllByUserID(int userID);
    Subscription findByUserID(int userID);


    void deleteByUserID(int userID);
}