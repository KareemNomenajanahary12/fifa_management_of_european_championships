package org.hei.school.fifa_management_if_european_championships.dao.mapper;

import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class CoachMapper implements Function<ResultSet, Coach> {

    @Override
    public Coach apply(ResultSet resultSet) {
        try {
            Coach coach = new Coach();
            coach.setId(UUID.fromString(resultSet.getString("coach_id")));
            coach.setName(resultSet.getString("coach_name"));
            coach.setNationality(resultSet.getString("coach_nationality"));
            return coach;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Coach", e);
        }
    }
}
