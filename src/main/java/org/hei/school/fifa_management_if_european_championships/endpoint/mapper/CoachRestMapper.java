package org.hei.school.fifa_management_if_european_championships.endpoint.mapper;

import lombok.RequiredArgsConstructor;
import org.hei.school.fifa_management_if_european_championships.endpoint.rest.CoachRest;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoachRestMapper {

    public CoachRest toRest(Coach coach) {
        return new CoachRest(
                coach.getName(),
                coach.getNationality()
        );
    }

    // Cette méthode n'a plus besoin de Club, car le club est maintenant une propriété de Coach
    public Coach toModel(CoachRest coachRest) {
        Coach coach = new Coach();
        coach.setName(coachRest.getName());
        coach.setNationality(coachRest.getNationality());
        // Le club est déjà géré par la relation dans Coach, donc pas besoin de l'ajouter ici.
        return coach;
    }
}
