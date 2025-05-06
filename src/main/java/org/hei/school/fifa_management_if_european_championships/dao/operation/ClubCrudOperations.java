package org.hei.school.fifa_management_if_european_championships.dao.operation;

import org.hei.school.fifa_management_if_european_championships.dao.DataSource;
import org.hei.school.fifa_management_if_european_championships.dao.mapper.ClubMapper;
import org.hei.school.fifa_management_if_european_championships.model.Club;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.hei.school.fifa_management_if_european_championships.service.exception.ServerException;
import org.hei.school.fifa_management_if_european_championships.service.exception.NotFoundException;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ClubCrudOperations implements CrudOperations<Club> {
    private final DataSource dataSource;
    private final ClubMapper clubMapper;
    private final CoachCrudOperations coachCrudOperations;

    public ClubCrudOperations(DataSource dataSource, ClubMapper clubMapper, CoachCrudOperations coachCrudOperations) {
        this.dataSource = dataSource;
        this.clubMapper = clubMapper;
        this.coachCrudOperations = coachCrudOperations;
    }
    @Override
    public List<Club> getAll(int page, int size) {
        String query =
                "SELECT c.id, c.name, c.acronym, c.year_creation, c.stadium_name, " +
                        "co.id AS coach_id, co.name AS coach_name, co.nationality AS coach_nationality " +
                        "FROM clubs c " +
                        "JOIN coaches co ON c.coach_id = co.id " +
                        "LIMIT ? OFFSET ?"
            ;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Pagination logic: OFFSET = (page - 1) * size
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Club> clubs = new ArrayList<>();

            while (resultSet.next()) {
                clubs.add(clubMapper.apply(resultSet));
            }

            return clubs;
        } catch (SQLException e) {
            throw new ServerException("Database error while fetching clubs");
        }
    }

    @Override
    public Club findById(UUID id) {
        String query = """
              
                SELECT\s
                c.id,\s
                c.name,\s
                 c.acronym,\s
                 c.year_creation,\s
                 c.stadium_name,
                 co.id AS coach_id,
                 co.name AS coach_name,
                 co.nationality AS coach_nationality
                 FROM clubs c
                JOIN coaches co ON c.coach_id = co.id
                WHERE c.id = ?
                 """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return clubMapper.apply(resultSet);
            } else {
                throw new NotFoundException("Club with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException("Database error while fetching club with ID: " + id);
        }
    }
    @Override
    public List<Club> saveAll(List<Club> clubs) {
        // 1. Sauvegarde d'abord tous les coachs liés aux clubs
        List<Coach> coaches = clubs.stream()
                .map(Club::getCoach)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        coachCrudOperations.saveAll(coaches);

        // 2. Requête SQL corrigée avec insertion du coach_id
        String sql = "INSERT INTO clubs (id, name, acronym, year_creation, stadium_name, coach_id) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "name = excluded.name, " +
                "acronym = excluded.acronym, " +
                "year_creation = excluded.year_creation, " +
                "stadium_name = excluded.stadium_name, " +
                "coach_id = excluded.coach_id";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Club club : clubs) {

                if (club.getId() == null) {
                    club.setId(UUID.randomUUID());
                }

                // Vérification de la présence du coach
                if (club.getCoach() == null || club.getCoach().getId() == null) {
                    throw new IllegalArgumentException("Chaque club doit avoir un coach avec un ID");
                }

                ps.setObject(1, club.getId(), Types.OTHER);                // id
                ps.setString(2, club.getName());                           // name
                ps.setString(3, club.getAcronym());                        // acronym
                ps.setInt(4, club.getYearCreation());                          // year_creation
                ps.setString(5, club.getStadium());                            // stadium_name
                ps.setObject(6, club.getCoach().getId(), Types.OTHER);         // coach_id

                ps.addBatch();
            }

            ps.executeBatch();
            return clubs;

        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }


}
