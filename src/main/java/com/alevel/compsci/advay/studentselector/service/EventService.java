package com.alevel.compsci.advay.studentselector.service;

import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JPA implementation of the Event Service Interface
 * The services are a wrapper around the respositories
 * to implement all the business logic.
 * The common pattern is not to have business logic in the
 * controllers.
 */
@Service
public class EventService implements IEventService {
    @Autowired

    private EventRepository eventRepository;

    /**
     * The method implement business logic before
     * saving the event into the repository
     * @param event
     * @return
     */
    @Override
    public Event saveEvent(Event event) {
        if (event.getEventTitle() != null && event.getEventDescription() != null
                && event.getEventStatus() == true && event.getOrganiserID() != 0 && event.getSelectionNum() > 0) {
            return eventRepository.save(event);
        } else {
            return null;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventByOrganiserID(int userID) {
        return eventRepository.findAllByOrganiserID(userID);

    }

    @Override
    public Event getEventByID(int eventID) {
        return eventRepository.findById(eventID).orElse(null);
    }

    @Override
    public List<Event> getEventsByEventTile(String eventTitle) {
        return eventRepository.findAllByEventTitle(eventTitle);
    }

    @Override
    public String deleteEventByID(int eventID) {
        eventRepository.deleteById(eventID);
        return "Event Deleted" + eventID;
    }

    @Override
    public String deleteEventByOrganiserID(int organiserID) {
        eventRepository.deleteByOrganiserID(organiserID);
        return "Event deleted" + organiserID;
    }

    /**
     * Initialising the event ojbect before saving
     * into the repository
     * @param event
     * @return
     */
    @Override
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

    /**
     * The method implement business logic before
     * updating the event status into the repository
     * @param eventID
     * @return
     */
    @Override
    public Event updateEventStatus(int eventID) {
        Event existingEvent;
        existingEvent = eventRepository.findById(eventID).orElse(null);
        existingEvent.setEventStatus(false);
        return eventRepository.save(existingEvent);
    }

}
