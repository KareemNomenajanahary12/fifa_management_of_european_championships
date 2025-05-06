package org.hei.school.fifa_management_if_european_championships.endpoint.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ClubRest {
    private UUID id;
    private String name;
    private String acronym;
    private String stadiumName;
    private int yearCreation;
    private CoachRest coach;

}
