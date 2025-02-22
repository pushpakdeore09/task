package com.backend.server.service;

import com.backend.server.dto.EventRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EntityManager entityManager;

    public String addEvent(EventRequest eventRequest){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Add_Event");

        query.registerStoredProcedureParameter("Name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Is_Paid_Free", Boolean.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Type", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Image", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Date", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Name", eventRequest.getName());
        query.setParameter("Username", eventRequest.getUsername());
        query.setParameter("Title", eventRequest.getTitle());
        query.setParameter("Is_Paid_Free", eventRequest.isPaidFree());
        query.setParameter("Type", eventRequest.getType());
        query.setParameter("Image", eventRequest.getImage());
        query.setParameter("Date", eventRequest.getDate());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }
}
