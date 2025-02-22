package com.backend.server.controller;

import com.backend.server.dto.EventRequest;
import com.backend.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add-event")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest){
        Map<String, String> response = new HashMap<>();
        try{
            String status = eventService.addEvent(eventRequest);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
