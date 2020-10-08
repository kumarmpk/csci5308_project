package dao;

import common.Constants;
import data.ILoadLeagueFactory;
import model.FreeAgent;
import model.League;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadLeagueDao implements ILoadLeagueFactory {

    @Override
    public void loadLeagueById(int id, League league) throws Exception{
        ICallDB callDB = null;
        try {
            callDB = new CallDB(Constants.loadLeagueByName);
            callDB.setInputParameterInt(1, id);
            callDB.setOutputParameterInt(2);
            callDB.setOutputParameterString(3);
            callDB.setOutputParameterInt(4);

            callDB.executeLoad();


            league = new League();
            league.setId(callDB.returnOutputParameterInt(2));
            league.setName(callDB.returnOutputParameterString(3));
            league.setCreatedBy(callDB.returnOutputParameterInt(4));


        }catch (Exception e){
            throw e;
        } finally {
            callDB.closeConnection();
        }
    }

    @Override
    public League loadLeagueByName(String leagueName) throws Exception {
        ICallDB callDB = null;
        League league = null;
        try {
            callDB = new CallDB(Constants.loadLeagueByName);
            callDB.setInputParameterString(1, leagueName);
            callDB.setOutputParameterInt(2);
            callDB.setOutputParameterString(3);
            callDB.setOutputParameterInt(4);
            callDB.executeLoad();

            league = new League();
            league.setId(callDB.returnOutputParameterInt(2));
            league.setName(callDB.returnOutputParameterString(3));
            league.setCreatedBy(callDB.returnOutputParameterInt(4));

        }catch (Exception e){
            throw e;
        } finally {
            callDB.closeConnection();
        }
        return league;
    }

    @Override
    public List<League> loadLeagueListByUserId(int userId) throws Exception {
        List<League> leagueList = null;
        ICallDB callDB = null;
        try{
            callDB = new CallDB(Constants.loadLeagueListByUserId);
            callDB.setInputParameterInt(1, userId);
            ResultSet rs = callDB.executeLoad();
            if (rs != null) {
                leagueList = new ArrayList<>();
                while (rs.next()) {
                    League league = new League();
                    league.setId(rs.getInt(1));
                    league.setName(rs.getString(2));
                    league.setCreatedBy(rs.getInt(3));
                    leagueList.add(league);
                }
            }
        } catch (Exception e){
            throw e;
        }

        return leagueList;
    }

}
