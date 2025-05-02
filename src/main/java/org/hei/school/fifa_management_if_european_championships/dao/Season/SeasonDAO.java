package org.hei.school.fifa_management_if_european_championships.dao.Season;

import org.hei.school.fifa_management_if_european_championships.model.Season;

import java.util.List;

public interface SeasonDAO {
    List<Season> getAll();
    List<Season> saveAll();
    Season updateStatus(int year);
}
