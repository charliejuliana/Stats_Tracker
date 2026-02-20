package backend.repositories;

import models.Player;
import models.PlayerStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsRepo {

    private final Connection conn;

    public StatsRepo(Connection conn) {
        this.conn = conn;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        try (PreparedStatement sql = conn.prepareStatement("Select id, first_name, last_name FROM player")) {
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    players.add(new Player(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public List<PlayerStats> getPlayerStats() {
        List<PlayerStats> playerStats = new ArrayList<>();
        try (PreparedStatement sql = conn.prepareStatement("Select points, assists, rebounds, steals, blocks FROM player_stats" +
                " WHERE player_id = 1;")) {
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    playerStats.add(new PlayerStats(rs.getInt("points"), rs.getInt("assists"), rs.getInt("rebounds"), rs.getInt("steals"),rs.getInt("blocks")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerStats;
    }
}
