package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.EventService;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private SubscriptionService subscriptionService;

    /**
     *
     * @param event
     * @return
     */
    @PostMapping("/event")
    public Event addEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/events")
    public List<Event> findAllEvent() {
        return eventService.getAllEvents();
    }

    @GetMapping("/eventByID/{id}")
    public Event findEventById(@PathVariable int id) {
        return eventService.getEventByID(id);
    }

    @GetMapping("/eventByName/{name}")
    public List<Event> findEventsByEventTitle(@PathVariable String name) {
        return eventService.getEventsByEventTile(name);
    }

    @GetMapping("/eventByOrganiserID/{id}")
    public List<Event> findEventByOrganiserId(@PathVariable int id) {
        return eventService.getEventByOrganiserID(id);
    }

    @GetMapping("/eventByUserID/{id}")
    public List<Event> findEventBySubscriptionId(@PathVariable int id) {
        return subscriptionService.getEventByUserID(id);
    }

    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEventByID(id);
    }
}
