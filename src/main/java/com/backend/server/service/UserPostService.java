package com.backend.server.service;

import com.backend.server.dto.UserPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;

@Service
public class UserPostService {

    @Autowired
    private EntityManager entityManager;

    public String addPost(UserPost userPost){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Add_Post");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Post_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Description", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ImageUrl", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Time", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Username", userPost.getUsername());
        query.setParameter("Post_ID", userPost.getPostId());
        query.setParameter("Title", userPost.getTitle());
        query.setParameter("Description", userPost.getDescription());
        query.setParameter("ImageUrl", userPost.getImageUrl());
        query.setParameter("Time", LocalDateTime.now().toString());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }

    public String updateLikes(String title, String action){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UpdateLikes");

        query.registerStoredProcedureParameter("Title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Action", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Title", title);
        query.setParameter("Action", action);

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");

    }
}
