package org.hei.school.fifa_management_if_european_championships.endpoint.mapper;

import lombok.RequiredArgsConstructor;
import org.hei.school.fifa_management_if_european_championships.endpoint.rest.ClubRest;
import org.hei.school.fifa_management_if_european_championships.endpoint.rest.CoachRest;
import org.hei.school.fifa_management_if_european_championships.model.Club;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubRestMapper {

    private final CoachRestMapper coachRestMapper;

    public ClubRest toRest(Club club) {
        if (club == null) {
            return null;
        }

        CoachRest coachRest = coachRestMapper.toRest(club.getCoach());

        return new ClubRest(
                club.getId(),
                club.getName(),
                club.getAcronym(),
                club.getStadium(),
                club.getYearCreation(),
                coachRest
        );
    }

    public Club toClubModel(ClubRest clubRest) {
        if (clubRest == null) {
            return null;
        }

        Club club = new Club();
        club.setId(clubRest.getId());
        club.setName(clubRest.getName());
        club.setAcronym(clubRest.getAcronym());
        club.setStadium(clubRest.getStadiumName());
        club.setYearCreation(clubRest.getYearCreation());

        Coach coach = coachRestMapper.toModel(clubRest.getCoach());
        club.setCoach(coach);

        return club;
    }
}
