package com.example.SunriseSignup.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.example.SunriseSignup.Entity.Event;
import com.example.SunriseSignup.Repository.EventRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }

    @PostMapping("/add-event/")
    public String addEvent(WebRequest request) {
        System.out.println("Came into add event Controller");

        // Retrieve parameters from the request
        String name = request.getParameter("eventTitle");
        String date = request.getParameter("eventDate");

        // Create a new event object and set its properties
        Event event = new Event();
        event.setName(name);
        event.setDate(LocalDate.parse(date));

        // Save the event to the repository
        eventRepository.save(event);
        System.out.println("Event details saved successfully");

        // Return success message
        return "members-only";
    }

}
