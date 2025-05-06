package org.hei.school.fifa_management_if_european_championships.endpoint.rest;

import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateClub {
    private UUID id; // Peut être null pour une création
    private String name;
    private String acronym;
    private Integer yearCreation;
    private String stadium;
    private UUID coachId; // Coach déjà existant
}
