package org.hei.school.fifa_management_if_european_championships.model;
import org.hei.school.fifa_management_if_european_championships.model.Club;
import java.util.List;
import java.util.UUID;

public class Championship {
    private UUID id;
    private String name;
    private String country;
    private List<Club> clubs;

    public Championship(UUID id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    // Getters & setters pour tous les champs, y compris List<Club>

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<Club> getClubs() { return clubs; }
    public void setClubs(List<Club> clubs) { this.clubs = clubs; }
}
