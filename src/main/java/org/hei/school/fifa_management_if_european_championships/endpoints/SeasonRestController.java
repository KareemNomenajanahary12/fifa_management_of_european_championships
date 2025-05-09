package org.hei.school.fifa_management_if_european_championships.endpoints;


import org.hei.school.fifa_management_if_european_championships.model.Season;
import org.hei.school.fifa_management_if_european_championships.model.SeasonToCreate;
import org.hei.school.fifa_management_if_european_championships.model.UpdateSeasonStatus;
import org.hei.school.fifa_management_if_european_championships.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seasons")
public class SeasonRestController {
    private final SeasonService service;

    @Autowired
    public SeasonRestController(SeasonService seasonService) {
        this.service = seasonService;
    }

    @GetMapping
    public List<Season> getAllSeasons() {
        return service.getAllSeasons();
    }
    @PostMapping
    public List<Season> createSeasons(@RequestBody(required = true) List<SeasonToCreate> seasonsToCreate) {
        return service.createSeasons(seasonsToCreate);
    }

    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<?> updateSeasonStatus(
            @PathVariable int seasonYear,
            @RequestBody UpdateSeasonStatus statusUpdate
    ) {
        try {
            Season updated = service.updateSeasonStatus(seasonYear, statusUpdate.getNewStatus());
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }
}
