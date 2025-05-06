package org.hei.school.fifa_management_if_european_championships.model;
import java.util.UUID;

public class Player {
    private UUID id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
    private Club club;

    public Player(UUID id, String name, int number, PlayerPosition position, String nationality, int age, Club club) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
