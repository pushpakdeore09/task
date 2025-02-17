package com.backend.server.dto;

public class AddResourceRequest {

    private String username;
    private String resource;
    private String subResource;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSubResource() {
        return subResource;
    }

    public void setSubResource(String subResource) {
        this.subResource = subResource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
