package org.hei.school.fifa_management_if_european_championships.model;

import lombok.*;
import org.hei.school.fifa_management_if_european_championships.dao.Season.SeasonDAOImplementation;

import java.util.UUID;

@Data
@Getter
@Setter
@EqualsAndHashCode
public class Season {
    private Integer year;
    private String alias;
    private UUID id;
    private SeasonStatus seasonStatus;

    public Season() {
        this.id = UUID.randomUUID();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SeasonStatus getSeasonStatus() {
        return seasonStatus;
    }

    public void setSeasonStatus(SeasonStatus seasonStatus) {
        this.seasonStatus = seasonStatus;
    }

    @Override
    public String toString() {
        return "Season { " +
                " year = " + year +
                ", alias = '" + alias + '\'' +
                ", id = " + id +
                ", seasonStatus = " + seasonStatus +
                '}';
    }

    public static void main(String[] args) {
        SeasonDAOImplementation i = new SeasonDAOImplementation();

        i.getAll();
    }
}
