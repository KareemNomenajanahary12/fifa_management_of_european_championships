package org.hei.school.fifa_management_if_european_championships.model;
import java.util.UUID;

public class Coach {
    private UUID id;
    private String name;
    private String nationality;
    private Club club; // Relation avec le club

    public Coach(UUID id, String name, String nationality, Club club) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.club = club;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}

