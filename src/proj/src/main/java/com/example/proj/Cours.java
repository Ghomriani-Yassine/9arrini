package com.example.proj;

public class Cours {
    private String id;
    private String nom;
    private String description;

    public Cours(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    // Getters and setters for id, nom, and description
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

