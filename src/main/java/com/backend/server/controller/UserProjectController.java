package com.backend.server.controller;


import com.backend.server.dto.User;
import com.backend.server.dto.UserProject;
import com.backend.server.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @PutMapping("/user-project/update-contributors")
    public ResponseEntity<Map<String, String>> updateContributors(@RequestBody UserProject userProject){
        Map<String, String> response = new HashMap<>();
        try{
            String status = userProjectService.updateContributors(userProject);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<?> login(@PathVariable String email){
        Map<String, Object> response = new HashMap<>();
        try{
            String username = userProjectService.login(email);
            response.put("username", username);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
