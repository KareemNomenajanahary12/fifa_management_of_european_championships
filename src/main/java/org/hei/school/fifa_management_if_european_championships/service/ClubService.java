package org.hei.school.fifa_management_if_european_championships.service;

import lombok.RequiredArgsConstructor;
import org.hei.school.fifa_management_if_european_championships.dao.operation.ClubCrudOperations;
import org.hei.school.fifa_management_if_european_championships.dao.operation.CoachCrudOperations;
import org.hei.school.fifa_management_if_european_championships.model.Club;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final CoachCrudOperations coachCrudOperations;
    private final ClubCrudOperations clubCrudOperations;

    // Méthode pour récupérer tous les clubs avec pagination
    public List<Club> getAll(int page, int size) {
        return clubCrudOperations.getAll(page, size);
    }

    // Méthode pour récupérer un club par son ID
    public Club findById(UUID id) {
        return clubCrudOperations.findById(id);
    }

    // Méthode pour enregistrer ou mettre à jour plusieurs clubs
    public List<Club> saveAll(List<Club> clubs) {
        // Appel direct à saveAll de ClubCrudOperations qui gère les inserts et updates
        return clubCrudOperations.saveAll(clubs);
    }
}
