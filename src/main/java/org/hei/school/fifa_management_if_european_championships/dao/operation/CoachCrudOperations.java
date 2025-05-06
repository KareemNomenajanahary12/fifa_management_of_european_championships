package org.hei.school.fifa_management_if_european_championships.dao.operation;

import org.hei.school.fifa_management_if_european_championships.dao.DataSource;
import org.hei.school.fifa_management_if_european_championships.dao.mapper.CoachMapper;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.hei.school.fifa_management_if_european_championships.service.exception.ServerException;
import org.hei.school.fifa_management_if_european_championships.service.exception.NotFoundException;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CoachCrudOperations implements CrudOperations<Coach> {
    private final DataSource dataSource;
    private final CoachMapper coachMapper;

    public CoachCrudOperations(DataSource dataSource, CoachMapper coachMapper) {
        this.dataSource = dataSource;
        this.coachMapper = coachMapper;
    }
    @Override
    public List<Coach> getAll(int page, int size) {
        String query = """
            SELECT id, name, nationality FROM coaches
            LIMIT ? OFFSET ?
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Pagination logic: OFFSET = (page - 1) * size
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Coach> coaches = new ArrayList<>();

            while (resultSet.next()) {
                coaches.add(coachMapper.apply(resultSet));
            }

            return coaches;
        } catch (SQLException e) {
            throw new ServerException("Database error while fetching coaches");
        }
    }

    @Override
    public Coach findById(UUID id) {
        String query = """
                SELECT id, name, nationality FROM coaches WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return coachMapper.apply(resultSet);
            } else {
                throw new NotFoundException("Coach with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException("Database error while fetching coach with ID: " + id);
        }
    }

    @Override
    public List<Coach> saveAll(List<Coach> coaches) {
        String query = """
                INSERT INTO coaches (id, name, nationality) 
                VALUES (?, ?, ?)
                ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, nationality = EXCLUDED.nationality
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Coach coach : coaches) {
                if (coach.getId() == null) {
                    coach.setId(UUID.randomUUID());
                }

                preparedStatement.setObject(1, coach.getId(), Types.OTHER);
                preparedStatement.setString(2, coach.getName());
                preparedStatement.setString(3, coach.getNationality());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            return coaches;
        } catch (SQLException e) {
            throw new ServerException("Error while saving coaches");
        }
    }
}
