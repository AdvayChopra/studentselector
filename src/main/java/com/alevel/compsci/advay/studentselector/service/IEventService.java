package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.Event;

import java.util.List;

public interface IEventService {
    Event saveEvent(Event event);

    List<Event> getAllEvents();

    List<Event> getEventByOrganiserID(int userID);

    Event getEventByID(int eventID);

    List<Event> getEventsByEventTile(String eventTitle);

    String deleteEventByID(int eventID);

    String deleteEventByOrganiserID(int organiserID);

    Event updateEvent(Event event);

    Event updateEventStatus(int eventID);
}
