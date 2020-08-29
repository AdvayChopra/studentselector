package com.alevel.compsci.advay.studentselector.repository;

import com.alevel.compsci.advay.studentselector.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByEventTitle(String eventTitle);

    List<Event> findAllByOrganiserID(int organiserID);

    void deleteByOrganiserID(int organiserID);
}
