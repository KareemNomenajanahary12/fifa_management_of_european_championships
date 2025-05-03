package org.hei.school.fifa_management_if_european_championships.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SeasonStatus {
    NOT_STARTED,STARTED, FINISHED;

    @JsonCreator
    public static SeasonStatus parseEnumIgnoreCase(String key) {
        return SeasonStatus.valueOf(key.toUpperCase());
    }
}
