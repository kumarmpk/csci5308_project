package db.dao;

import db.data.IPlayerFactory;
import simulation.model.Player;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao implements IPlayerFactory {
    @Override
    public int addPlayer(Player player) throws Exception {
        ICallDB callDB = null;
        try {
            callDB = new CallDB("AddPlayer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            callDB.setInputParameterString(1, player.getName());
            callDB.setInputParameterString(2, player.getPosition().toString());
            callDB.setInputParameterBoolean(3, player.isCaptain());
            callDB.setInputParameterInt(4, player.getTeamId());
            callDB.setInputParameterInt(5, player.getFreeAgentId());
            callDB.setInputParameterInt(6, player.getAge());
            callDB.setInputParameterInt(7, player.getSkating());
            callDB.setInputParameterInt(8, player.getShooting());
            callDB.setInputParameterInt(9, player.getChecking());
            callDB.setInputParameterInt(10, player.getSaving());
            callDB.setInputParameterBoolean(11, player.getInjured());
            callDB.setInputParameterDate(12, Date.valueOf(player.getInjuryStartDate()));
            callDB.setInputParameterInt(13, player.getInjuryDatesRange());
            callDB.setInputParameterDouble(14, player.getStrength());

            callDB.setOutputParameterInt(15);
            callDB.execute();
            player.setId(callDB.returnOutputParameterInt(15));

        } catch (SQLException sqlException) {
            throw sqlException;
        } finally {
            assert callDB != null;
            callDB.closeConnection();
        }
        return player.getId();
    }

    @Override
    public void loadPlayerById(int id, Player player) throws Exception {

        ICallDB callDB = null;
        try {
            callDB = new CallDB("LoadPlayerById(?)");
            callDB.setInputParameterInt(1, id);
            ResultSet rs = callDB.executeLoad();

            if (rs != null) {
                setPlayerFromDB(player, rs);

            }

        } catch (Exception e) {
            throw e;
        } finally {
            assert callDB != null;
            callDB.closeConnection();
        }
    }

    private void setPlayerFromDB(Player player, ResultSet rs) throws SQLException {
        player.setId(rs.getInt(1));
        player.setName(rs.getString(2));
        player.setPosition(Player.Position.valueOf(rs.getString(3)));
        player.setCaptain(rs.getBoolean(4));
        player.setTeamId(rs.getInt(5));
        player.setFreeAgentId(rs.getInt(6));
        player.setAge(rs.getInt(7));
        player.setSkating(rs.getInt(8));
        player.setShooting(rs.getInt(9));
        player.setChecking(rs.getInt(10));
        player.setSaving(rs.getInt(11));
        player.setInjured(rs.getBoolean(12));
        player.setInjuryStartDate(rs.getDate(13).toLocalDate());
        player.setInjuryDatesRange(rs.getInt(14));
    }

    @Override
    public List<Player> loadPlayerListByFreeAgentId(int freeAgentId) throws Exception {
        List<Player> playerList = null;
        ICallDB callDB = null;
        try {
            callDB = new CallDB("LoadPlayerListByFreeAgentId(?)");
            callDB.setInputParameterInt(1, freeAgentId);
            ResultSet rs = callDB.executeLoad();

            if (rs != null) {
                playerList = new ArrayList<>();
                while (rs.next()) {
                    Player player = new Player();
                    setPlayerFromDB(player, rs);
                    playerList.add(player);
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            assert callDB != null;
            callDB.closeConnection();
        }
        return playerList;
    }

    @Override
    public List<Player> loadPlayerListByTeamId(int teamId) throws Exception {
        List<Player> playerList = null;
        ICallDB callDB = null;
        try {
            callDB = new CallDB("LoadPlayerListByTeamId(?)");
            callDB.setInputParameterInt(1, teamId);
            ResultSet rs = callDB.executeLoad();

            if (rs != null) {
                playerList = new ArrayList<>();
                while (rs.next()) {
                    Player player = new Player();
                    setPlayerFromDB(player, rs);
                    playerList.add(player);
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            assert callDB != null;
            callDB.closeConnection();
        }
        return playerList;
    }

    @Override
    public void updatePlayerById(int id, Player player) throws Exception {
        ICallDB callDB = null;
        try {
            callDB = new CallDB("UpdatePlayerById(?,?,?,?,?,?,?,?,?,?,?,?)");
            callDB.setInputParameterInt(1, player.getId());
            callDB.setInputParameterInt(2, player.getTeamId());
            callDB.setInputParameterInt(3, player.getFreeAgentId());
            callDB.setInputParameterInt(4, player.getAge());
            callDB.setInputParameterInt(5, player.getSkating());
            callDB.setInputParameterInt(6, player.getShooting());
            callDB.setInputParameterInt(7, player.getChecking());
            callDB.setInputParameterInt(8, player.getSaving());
            callDB.setInputParameterBoolean(9, player.getInjured());
            callDB.setInputParameterDate(10, Date.valueOf(player.getInjuryStartDate()));
            callDB.setInputParameterInt(11, player.getInjuryDatesRange());
            callDB.setInputParameterDouble(12, player.getStrength());
            callDB.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            assert callDB != null;
            callDB.closeConnection();
        }
    }
}
