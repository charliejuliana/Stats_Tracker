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

    public void insertPlayer(String firstName, String lastName, int height, int weight, int jerseyNumber, String position, int teamId) {

        try (PreparedStatement sql = conn.prepareStatement(
                "INSERT INTO Player " +
                        "(first_name, last_name, height_inches, weight_pounds, jersey_number, position, team_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);")) {

            sql.setString(1, firstName);
            sql.setString(2, lastName);
            sql.setInt(3, height);
            sql.setInt(4, weight);
            sql.setInt(5, jerseyNumber);
            sql.setString(6, position);
            sql.setInt(7, teamId);

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
