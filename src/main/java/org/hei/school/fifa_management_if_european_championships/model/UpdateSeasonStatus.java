package org.hei.school.fifa_management_if_european_championships.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UpdateSeasonStatus {
    private SeasonStatus newStatus;

    public SeasonStatus getNewStatus() {
        return newStatus;
    }

    @JsonProperty("newStatus")
    public void setNewStatus(String status) {
        if (status != null) {
            this.newStatus = SeasonStatus.valueOf(status.toUpperCase());
        }
    }

    public UpdateSeasonStatus(SeasonStatus newStatus) {
        this.newStatus = newStatus;
    }
}
