package com.codecool.dungeoncrawl.dao;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;
    private PlayerDaoJdbc playerDaoJdbc;
    private PlayerDao player;
    public  GameStateDaoJdbc(DataSource dataSource, PlayerDao player) {
        this.dataSource = dataSource;
        this.player=player;
    }
    public java.sql.Date getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return java.sql.Date.valueOf(dateFormat.format(date));
    }

    @Override
    public void add(GameState state, int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id, name, actual_map) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, player_id);
            statement.setString(4, state.getName());
            statement.setInt(5, state.getActualMap());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state, int id, int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ?, player_id = ?, actual_map=? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            state.setSavedAt(getDateNow());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, player_id);
            statement.setInt(4, state.getActualMap());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT current_map, saved_at, player_id, name, actual_map FROM game_state WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            PlayerModel player=playerDaoJdbc.get(resultSet.getInt(3));
            GameState state = new GameState(resultSet.getString(1), resultSet.getDate(2),player, resultSet.getInt(4), resultSet.getString(5));
            state.setId(id);
            return state;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, current_map, saved_at, player_id ,actual_map, name FROM game_state";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<GameState> result = new ArrayList<>();
            while(resultSet.next()) {
                PlayerModel player= new PlayerDaoJdbc(dataSource).get(resultSet.getInt(4));
                GameState state = new GameState(resultSet.getString(2), resultSet.getDate(3),player, resultSet.getInt(5), resultSet.getString(6));
                state.setId(resultSet.getInt(1));
                result.add(state);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public GameState get(String name) {
        List<GameState> games=getAll();
        for(GameState gameState: games){
            if(gameState.getName()!=null) {
                if (gameState.getName().equals(name)) return gameState;
            }
        }
        return null;
    }
    @Override
    public int getPlayerId(int game_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT  player_id FROM game_state WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, game_id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return -1;
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


    }
}
