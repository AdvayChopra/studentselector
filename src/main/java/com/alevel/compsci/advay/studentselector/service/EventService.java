package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired

    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        if (event.getEventTitle() != null && event.getEventDescription() != null
                && event.getEventStatus() == true && event.getOrganiserID() != 0 && event.getSelectionNum() > 0) {
            return eventRepository.save(event);
        } else {
            return null;
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventByOrganiserID(int userID) {
        return eventRepository.findAllByOrganiserID(userID);

    }

    public Event getEventByID(int eventID) {
        return eventRepository.findById(eventID).orElse(null);
    }

    public List<Event> getEventsByEventTile(String eventTitle) {
        return eventRepository.findAllByEventTitle(eventTitle);
    }

    public String deleteEventByID(int eventID) {
        eventRepository.deleteById(eventID);
        return "Event Deleted" + eventID;
    }

    public String deleteEventByOrganiserID(int organiserID) {
        eventRepository.deleteByOrganiserID(organiserID);
        return "Event deleted" + organiserID;
    }

    public Event updateEvent(Event event) {
        Event existingEvent;
        existingEvent = eventRepository.findById(event.getEventID()).orElse(null);
        existingEvent.setEventTitle(event.getEventTitle());
        existingEvent.setOrganiserID(event.getOrganiserID());
        existingEvent.setSelectionNum(event.getSelectionNum());
        existingEvent.setEventDescription(event.getEventDescription());
        existingEvent.setEventStatus(event.getEventStatus());
        existingEvent.setEventID(event.getEventID());
        return eventRepository.save(existingEvent);
    }

    public Event updateEventStatus(int eventID) {
        Event existingEvent;
        existingEvent = eventRepository.findById(eventID).orElse(null);
        existingEvent.setEventStatus(false);
        return eventRepository.save(existingEvent);
    }

}
