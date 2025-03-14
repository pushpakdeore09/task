package com.backend.server.service;

import com.backend.server.dto.EventRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EntityManager entityManager;

    public String addEvent(EventRequest eventRequest) {
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
        query.setParameter("Is_Paid_Free", eventRequest.isIs_Paid_Free());
        query.setParameter("Type", eventRequest.getType());
        query.setParameter("Image", eventRequest.getImage());
        query.setParameter("Date", eventRequest.getDate());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }

    public List<EventRequest> getAllEvents() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetAllEvents");

        query.execute();
        List<Object[]> result = query.getResultList();

        List<EventRequest> eventRequests = new ArrayList<>();

        for (Object[] row : result) {
            String name = (String) row[0];
            String username = (String) row[1];
            String title = (String) row[2];
            Boolean is_Paid_Free = (Boolean) row[3];
            String type = (String) row[4];
            String image = (String) row[5];
            String date = (String) row[6];

            EventRequest eventRequest = new EventRequest(date, image, is_Paid_Free, name, title, type, username);

            eventRequests.add(eventRequest);
        }
        return eventRequests;
    }

    public String updateEvent(EventRequest eventRequest){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Update_Event");

        query.registerStoredProcedureParameter("Name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Is_Paid_Free", Boolean.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Type", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Image", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Date", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Name", eventRequest.getName());
        query.setParameter("Title", eventRequest.getTitle());
        query.setParameter("Is_Paid_Free", eventRequest.isIs_Paid_Free());
        query.setParameter("Type", eventRequest.getType());
        query.setParameter("Image", eventRequest.getImage());
        query.setParameter("Date", eventRequest.getDate());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }
}
