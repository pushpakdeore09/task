package com.backend.server.controller;

import com.backend.server.dto.UserPost;
import com.backend.server.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserPostController {


    @Autowired
    private UserPostService userPostService;

    @PostMapping("/add-post")
    public ResponseEntity<?> addPost(@RequestBody UserPost userPost){
        Map<String, String> response = new HashMap<>();
        try{
            String status = userPostService.addPost(userPost);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-likes/{title}/{action}")
    public ResponseEntity<?> updateLike(@PathVariable String title, @PathVariable String action){
        Map<String, String> response = new HashMap<>();
        try{
            String status = userPostService.updateLikes(title, action);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
