package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.EventService;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import com.alevel.compsci.advay.studentselector.service.UserService;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RestController is Spring Stereotype which wires up the class as Spring bean
 * The common pattern is to have Web/HTTP related logic in the controllers
 * and business logic in the service implementation.
 */

@RestController
/**
 * All Selection related HTTP Requests handled by this class
 */
public class SelectionController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    private List<Subscription> subscriptions;

    private ArrayList<Subscription> totalSubscriptions = new ArrayList<>();

    /**
     * This method is called when the client requests
     * HTTP GET method with URL context /selector/{eventID}
     * It randomly selects a specified number of subscribers
     * and sends an email to the selected subscribers using SendGrid API
     * The selection process also takes into account
     * the weighting of each subscriber
     *
     * @param eventID
     * @return
     * @throws IOException
     */

    @GetMapping("/selector/{eventID}")
    public String doSelection(@PathVariable int eventID) throws IOException {
        //Gets all subscriptions for an event
        subscriptions = subscriptionService.getSubscriptionByEventID(eventID);
        int totalWeight = 0;
        //Loops through all subscriptions and gets the total weighing
        for (int i = 0; i < subscriptions.size(); i++) {
            int weight = subscriptions.get(i).getWeight();
            totalWeight = weight + totalWeight;
            //populate totalSubscriptions list depending on weight
            for (int j = 0; j < weight; j++) {
                totalSubscriptions.add(subscriptions.get(i));
            }
        }

        Random ran = new Random();
        Event event = eventService.getEventByID(eventID);

        Subscription selectedSubscription;
        //Randomly select subscribers from totalSubscriptions list
        for (int i = 0; i < event.getSelectionNum(); i++) {
            int randomUser = ran.nextInt(totalWeight);
            selectedSubscription = totalSubscriptions.get(randomUser);
            totalSubscriptions.remove(randomUser);
            //Removes selected subscriber from totalSubscriptions list
            for (int j = 0; j < totalSubscriptions.size(); j++) {
                if (selectedSubscription.getUserID() == totalSubscriptions.get(j).getUserID()) {
                    totalSubscriptions.remove(randomUser);
                }
            }

            int selectedUser = selectedSubscription.getUserID();
            //set selected status of the subscription to true
            selectedSubscription.setSelected(true);


            AppUser user = userService.getUserByID(selectedUser);

            //send email to the selected user

            String email = user.getEmail();

            Email from = new Email("donotreply@studentselector.com");

            Email to = new Email(email); // use your own email address here

            String subject = "You Won!";
            Content content = new Content("text/html", "Congrats! You won the event");

            Mail mail = new Mail(from, subject, to, content);
            SendGrid sg = new SendGrid("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");
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
        }
        //change event status to false
        eventService.updateEventStatus(eventID);
        return "";
    }
}
