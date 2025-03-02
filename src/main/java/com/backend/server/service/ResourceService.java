package com.backend.server.service;

import com.backend.server.dto.ResourceRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceService {

    @Autowired
    private EntityManager entityManager;


    public String addResource(ResourceRequest resourceRequest){
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

    public List<ResourceRequest> getResourceByResource(String resource) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetResourceByResource");

        query.registerStoredProcedureParameter("Resource", String.class, ParameterMode.IN);
        query.setParameter("Resource", resource);

        query.execute();

        List<Object[]> results = query.getResultList();
        List<ResourceRequest> resourceRequests = new ArrayList<>();
        ResourceRequest resourceRequest = new ResourceRequest();
        for(Object[] result: results){

            resourceRequest.setUsername((String) result[0]);
            resourceRequest.setResource((String) result[1]);
            String subResource = (String) result[2];
            List<String> subResources = Arrays.asList(subResource.replace("[", "").replace("]","").split(", "));

            resourceRequest.setSubResources(subResources);
            resourceRequest.setDescription((String) result[3]);
        }
        resourceRequests.add(resourceRequest);
        return resourceRequests;
    }

    public Map<String, List<String>> getResourceByUsername(String username){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetResourceByUsername");
        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.setParameter("Username", username);

        query.execute();

        List<String> resources = query.getResultList();

        Map<String, List<String>> result = new HashMap<>();
        result.put("resource", resources);
        return result;

    }
}
