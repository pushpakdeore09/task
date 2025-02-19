package com.backend.server.controller;

import com.backend.server.dto.ResourceRequest;
import com.backend.server.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/add-resource")
    public ResponseEntity<Map<String, String>> addResource(@RequestBody ResourceRequest resourceRequest){
        Map<String, String> response = new HashMap<>();
        try{
            String status = resourceService.addResource(resourceRequest);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-resource/{username}")
    public ResponseEntity<?> getResource(@PathVariable String username){
        try{
            List<ResourceRequest> resourceRequests = resourceService.getResource(username);
            return new ResponseEntity<>(resourceRequests, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
