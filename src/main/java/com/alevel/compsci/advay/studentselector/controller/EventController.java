package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.service.EventService;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * RestController is Spring Stereotype which wires up the class as Spring bean
 * The common pattern is to have Web/HTTP related logic in the controllers
 * and business logic in the service implementation.
 */
@RestController
/**
 * All Event related HTTP Requests handled by this class
 */

public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private SubscriptionService subscriptionService;

    /**
     *This method is called when the client requests
     * HTTP POST method with URL context /event
     * @param event
     * The client specifies event object in JSON in the HTTP request
     * The marshalling and unmarshalling is done by Spring
     * using the Jackson JSON library.
     * @return
     */
    @PostMapping("/event")
    public Event addEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    /**
     *This method is called when the client requests
     * HTTP GET method with URL context /events
     * @return
     */
    @GetMapping("/events")
    public List<Event> findAllEvent() {
        return eventService.getAllEvents();
    }

    /**
     *This method is called when the client requests
     *HTTP GET method with URL context /eventByID/{id}
     * @param id is specified in request URL path
     * @return
     */
    @GetMapping("/eventByID/{id}")
    public Event findEventById(@PathVariable int id) {
        return eventService.getEventByID(id);
    }

    /**
     *This method is called when the client requests
     *HTTP GET method with URL context /eventByName/{name}
     * @param name is specified in request URL path
     * @return
     */

    @GetMapping("/eventByName/{name}")
    public List<Event> findEventsByEventTitle(@PathVariable String name) {
        return eventService.getEventsByEventTile(name);
    }

    /**
     *This method is called when the client requests
     *HTTP GET method with URL context /eventByOrganiserID/{id}
     * @param id
     * @return
     */
    @GetMapping("/eventByOrganiserID/{id}")
    public List<Event> findEventByOrganiserId(@PathVariable int id) {
        return eventService.getEventByOrganiserID(id);
    }

    /**
     *This method is called when the client requests
     *HTTP GET method with URL context /eventByUserID/{id}
     * @param id
     * @return
     */

    @GetMapping("/eventByUserID/{id}")
    public List<Event> findEventByUserId(@PathVariable int id) {
        return subscriptionService.getEventByUserID(id);
    }

    /**
     *This method is called when the client requests
     * HTTP PUT method with URL context /updateEvent
     * @param event
     * The client specifies event object in JSON in the HTTP request
     * @return
     */
    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    /**
     *This method is called when the client requests
     * HTTP DELETE method with URL context /event/{id}
     * @param id is specified in request URL path
     * @return
     */
    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEventByID(id);
    }
}
