package org.hei.school.fifa_management_if_european_championships.model;

import java.util.List;
import java.util.UUID;


    public class Club {
        private UUID id;
        private String name;
        private String acronym;
        private String stadiumName;
        private Championship championship; // relation avec Championship
        private List<Player> players;      // relation avec les joueurs
        private Coach coach;              // relation avec l'entraîneur

        public Club(UUID id, String name, String acronym, String stadiumName, Championship championship) {
            this.id = id;
            this.name = name;
            this.acronym = acronym;
            this.stadiumName = stadiumName;
            this.championship = championship;
        }

        // Getters & Setters
        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAcronym() {
            return acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        public String getStadiumName() {
            return stadiumName;
        }

        public void setStadiumName(String stadiumName) {
            this.stadiumName = stadiumName;
        }

        public Championship getChampionship() {
            return championship;
        }

        public void setChampionship(Championship championship) {
            this.championship = championship;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public void setPlayers(List<Player> players) {
            this.players = players;
        }

        public Coach getCoach() {
            return coach;
        }

        public void setCoach(Coach coach) {
            this.coach = coach;
        }
    }
