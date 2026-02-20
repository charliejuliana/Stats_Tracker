package backend.services;

import backend.repositories.StatsRepo;
import models.Player;
import models.PlayerStats;

import java.util.List;

public class StatsService {
    private final StatsRepo repo;
    public StatsService(StatsRepo statsRepo) {
        repo = statsRepo;
    }

    public List<Player> getAllPlayers() {
        return repo.getPlayers();
    }

    public List<PlayerStats> getAllPlayerStats() {
        return repo.getPlayerStats();
    }
}
