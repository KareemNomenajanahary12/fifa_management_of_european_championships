package org.hei.school.fifa_management_if_european_championships.dao.mapper;

import org.hei.school.fifa_management_if_european_championships.model.Club;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class ClubMapper implements Function<ResultSet, Club> {

    private final CoachMapper coachMapper;

    public ClubMapper(CoachMapper coachMapper) {
        this.coachMapper = coachMapper;
    }

    @Override
    public Club apply(ResultSet resultSet) {
        try {
            Club club = new Club();
            club.setId(UUID.fromString(resultSet.getString("id")));
            club.setName(resultSet.getString("name"));
            club.setAcronym(resultSet.getString("acronym"));
            club.setYearCreation(resultSet.getInt("year_creation"));
            club.setStadium(resultSet.getString("stadium_name"));

            // Récupère le coach associé au club
            Coach coach = coachMapper.apply(resultSet);
            club.setCoach(coach);

            return club;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Club", e);
        }
    }
}
