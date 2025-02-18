package com.backend.server.service;

import com.backend.server.dto.AddResourceRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private EntityManager entityManager;

    public String addResource(AddResourceRequest resourceRequest){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Add_Resource");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Resource", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("SubResource", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Description", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Username", resourceRequest.getUsername());
        query.setParameter("Resource", resourceRequest.getResource());
        query.setParameter("SubResource", resourceRequest.getSubResources().toString());
        query.setParameter("Description", resourceRequest.getDescription());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }
}
