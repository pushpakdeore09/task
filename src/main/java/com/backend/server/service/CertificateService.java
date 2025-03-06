package com.backend.server.service;

import com.backend.server.dto.Certificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    @Autowired
    private EntityManager entityManager;

    public String addCertificate(Certificate certificate){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Add_Certificate");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Certificate_Id", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ImageUrl", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("IssuedBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Remark", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Username", certificate.getUsername());
        query.setParameter("Certificate_Id", certificate.getCertificateId());
        query.setParameter("ImageUrl", certificate.getImageUrl());
        query.setParameter("IssuedBy", certificate.getIssuedBy());
        query.setParameter("Remark", certificate.getRemark());

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }

    public String deleteCertificate(String certificateId, String username){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Delete_Certificate");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CertificateId", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("CertificateId", certificateId);
        query.setParameter("Username", username);

        query.execute();

        return (String) query.getOutputParameterValue("StatusMessage");
    }

    public String updateCertificate(Certificate certificate){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Update_Certificate");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Certificate_Id", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ImageUrl", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("IssuedBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Remark", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("StatusMessage", String.class, ParameterMode.OUT);

        query.setParameter("Username", certificate.getUsername());
        query.setParameter("Certificate_Id", certificate.getCertificateId());
        query.setParameter("ImageUrl", certificate.getImageUrl());
        query.setParameter("IssuedBy", certificate.getIssuedBy());
        query.setParameter("Remark", certificate.getRemark());

        query.execute();
        return (String) query.getOutputParameterValue("StatusMessage");
    }

    public Certificate getCertificate(String username, String certificateId){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("Get_Certificate");

        query.registerStoredProcedureParameter("Username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Certificate_Id", String.class, ParameterMode.IN);

        query.setParameter("Username", username);
        query.setParameter("Certificate_Id", certificateId);

        query.execute();

        Object[] result = (Object[]) query.getSingleResult();
        Certificate certificate = new Certificate();
        certificate.setUsername((String) result[0]);
        certificate.setCertificateId((String) result[1]);
        certificate.setImageUrl((String) result[2]);
        certificate.setIssuedBy((String) result[3]);
        certificate.setRemark((String) result[4]);

        return certificate;
    }
}
