package com.backend.server.service;

import com.backend.server.dto.UserProject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService {

    @Autowired
    private EntityManager entityManager;

    public String updateContributors(UserProject userProject){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UpdateContributors");
        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Project_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Contributors", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Username", userProject.getUsername());
        query.setParameter("Project_ID", userProject.getProjectId());
        query.setParameter("Contributors", userProject.getContributors().toString());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }
}
