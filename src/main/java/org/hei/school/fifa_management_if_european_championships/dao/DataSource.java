package org.hei.school.fifa_management_if_european_championships.dao;

import org.springframework.context.annotation.Configuration;

import org.hei.school.fifa_management_if_european_championships.service.exception.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {
    private final static int defaultPort = 5432;
    private final String host = System.getenv("DATABASE_HOST");
    private final String user = System.getenv("DATABASE_USER");
    private final String password = System.getenv("DATABASE_PASSWORD");
    private final String database = System.getenv("DATABASE_NAME");
    private final String jdbcUrl;

    public DataSource() {
        jdbcUrl = "jdbc:postgresql://" + host + ":" + defaultPort + "/" + database;
    }

    public Connection getConnection() {
        try {
            System.out.println("✅ Connection with database successfully");
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect with database");
            throw new ServerException(e);
        }
    }
}

