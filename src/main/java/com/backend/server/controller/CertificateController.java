package com.backend.server.controller;

import com.backend.server.dto.Certificate;
import com.backend.server.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/add-certificate")
    public ResponseEntity<?> addCertificate(@RequestBody Certificate certificate){
        Map<String, String> response = new HashMap<>();
        try{
            String status = certificateService.addCertificate(certificate);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-certificate/{certificateId}/{username}")
    public ResponseEntity<?> deleteCertificate(@PathVariable String certificateId, @PathVariable String username){
        Map<String, String> response = new HashMap<>();
        try{
            String status = certificateService.deleteCertificate(certificateId, username);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-certificate")
    public ResponseEntity<?> updateCertificate(@RequestBody Certificate certificate){
        Map<String, String> response = new HashMap<>();
        try{
            String status = certificateService.updateCertificate(certificate);
            response.put("message", status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-certificate/{username}/{certificateId}")
    public ResponseEntity<?> getCertificate(@PathVariable String username, @PathVariable String certificateId){
        Map<String, Object> response = new HashMap<>();
        try{
            Certificate certificate = certificateService.getCertificate(username, certificateId);
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
