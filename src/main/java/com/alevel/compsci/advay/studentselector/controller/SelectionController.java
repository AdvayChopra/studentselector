package com.alevel.compsci.advay.studentselector.controller;

import com.alevel.compsci.advay.studentselector.entity.AppUser;
import com.alevel.compsci.advay.studentselector.entity.Event;
import com.alevel.compsci.advay.studentselector.entity.Subscription;
import com.alevel.compsci.advay.studentselector.service.EventService;
import com.alevel.compsci.advay.studentselector.service.SubscriptionService;
import com.alevel.compsci.advay.studentselector.service.UserService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class SelectionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    private List<Subscription> subscriptions;

    private ArrayList<Subscription> totalSubscriptions = new ArrayList<>();

    @GetMapping("/selector/{eventID}")
    public String doSelection(@PathVariable int eventID) throws IOException {

        subscriptions = subscriptionService.getSubscriptionByEventID(eventID);
        int totalWeight = 0;
        for(int i=0; i < subscriptions.size(); i++){
            int weight = subscriptions.get(i).getWeight();
            totalWeight = weight + totalWeight;
            for(int j = 0; j < weight; j++){
                totalSubscriptions.add(subscriptions.get(i));
            }
        }
        Random ran = new Random();

        Event event = eventService.getEventByID(eventID);

        Subscription selectedSubscription;
        for (int i = 0; i < event.getSelectionNum(); i++) {
            int randomUser = ran.nextInt(totalWeight);
            selectedSubscription = totalSubscriptions.get(randomUser);
            totalSubscriptions.remove(randomUser);
            int userID = selectedSubscription.getUserID();
            for(int j=0; j<totalSubscriptions.size(); j++){
            if(selectedSubscription.getUserID() == totalSubscriptions.get(j).getUserID()){
                totalSubscriptions.remove(randomUser);
            }
            }

            int selectedUser = selectedSubscription.getUserID();
            selectedSubscription.setSelected(true);


            AppUser user = userService.getUserByID(selectedUser);
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

           // Response response = sg.api(request);
        }
        eventService.updateEventStatus(eventID);
        return "";
    }
}
