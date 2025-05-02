package org.hei.school.fifa_management_if_european_championships.service;


import org.hei.school.fifa_management_if_european_championships.dao.Season.SeasonDAOImplementation;
import org.hei.school.fifa_management_if_european_championships.model.Season;
import org.hei.school.fifa_management_if_european_championships.model.SeasonStatus;
import org.hei.school.fifa_management_if_european_championships.model.SeasonToCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeasonService {
    private final SeasonDAOImplementation implementation;

    @Autowired
    public SeasonService(SeasonDAOImplementation implementation) {
        this.implementation = implementation;
    }

    public List<Season> getAllSeasons() {
        return implementation.getAll();
    }

    public List<Season> createSeasons(List<SeasonToCreate> toCreateList) {
        List<Season> seasons = toCreateList.stream().map(seasonToCreate -> {
            Season season = new Season();
            season.setYear(seasonToCreate.getYear());
            season.setAlias(seasonToCreate.getAlias());
            season.setId(UUID.randomUUID());
            season.setSeasonStatus(SeasonStatus.NOT_STARTED);
            return  season;
        }).toList();

        return implementation.saveAll(seasons);
    }
}
