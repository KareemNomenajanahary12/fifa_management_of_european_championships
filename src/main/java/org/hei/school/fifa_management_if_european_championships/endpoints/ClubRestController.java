package org.hei.school.fifa_management_if_european_championships.endpoints;

import lombok.RequiredArgsConstructor;
import org.hei.school.fifa_management_if_european_championships.endpoint.mapper.ClubRestMapper;
import org.hei.school.fifa_management_if_european_championships.endpoint.rest.ClubRest;
import org.hei.school.fifa_management_if_european_championships.endpoint.rest.CreateOrUpdateClub;
import org.hei.school.fifa_management_if_european_championships.model.Club;
import org.hei.school.fifa_management_if_european_championships.model.Coach;
import org.hei.school.fifa_management_if_european_championships.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubRestController {

    private final ClubService clubService;
    private final ClubRestMapper clubRestMapper;

    @GetMapping
    public List<ClubRest> getAllClubs(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "20") int size) {
        return clubService.getAll(page, size)
                .stream()
                .map(clubRestMapper::toRest)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public ClubRest getClubById(@PathVariable UUID id) {
        Club club = clubService.findById(id);
        return clubRestMapper.toRest(club);
    }

    @PutMapping
    public ResponseEntity<Object> createOrUpdateClubs(
            @RequestBody List<ClubRest> createOrUpdateClub
    ) {
        if (createOrUpdateClub == null || createOrUpdateClub.isEmpty()) {
            return ResponseEntity.badRequest().body("Club list cannot be empty");
        }

        List<Club> clubs = createOrUpdateClub.stream()
                .map(clubRestMapper::toClubModel)
                .toList();

        List<Club> savedClubs = clubService.saveAll(clubs);

        List<ClubRest> savedClubRests = savedClubs.stream()
                .map(clubRestMapper::toRest)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(savedClubRests);
    }




}
