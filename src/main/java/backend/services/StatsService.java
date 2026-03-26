package backend.services;

import backend.repositories.StatsRepo;
import models.Player;
import models.PlayerStats;
import models.Team;

import java.util.List;

public class StatsService {
    private final StatsRepo repo;
    public StatsService(StatsRepo statsRepo) {
        repo = statsRepo;
    }

    public List<Team> getAllTeams() { return repo.getTeams(); }

    public List<Player> getAllPlayers() {
        return repo.getPlayers();
    }

    public List<PlayerStats> getAllPlayerStats() {
        return repo.getPlayerStats();
    }

    public void addPlayer(String firstName, String lastName, int height, int weight, int jersey, String position, int teamId) {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        repo.insertPlayer(firstName, lastName, height, weight, jersey, position, teamId);
    }

    public void deletePlayer(int playerId) {
        repo.deletePlayer(playerId);
    }

    public void updatePlayer(int playerId, String firstName, String lastName, int height, int weight, int jersey, String position, int teamId) {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        repo.updatePlayer(playerId, firstName, lastName, height, weight, jersey, position, teamId);
    }
}
