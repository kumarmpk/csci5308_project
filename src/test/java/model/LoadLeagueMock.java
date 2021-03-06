package model;

import data.ILoadConferenceFactory;
import data.ILoadLeagueFactory;
import data.ILoadPlayerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadLeagueMock implements ILoadLeagueFactory {

    private List formConferenceList() throws Exception {
        List<Conference> conferenceList = new ArrayList<>();

        ILoadConferenceFactory conferenceFactory = new LoadConferenceMock();
        Conference conference = new Conference(1, conferenceFactory);
        conferenceList.add(conference);

        conference = new Conference(2, conferenceFactory);
        conferenceList.add(conference);

        return conferenceList;
    }

    private FreeAgent formFreeAgent() throws Exception {
        FreeAgent freeAgent = new FreeAgent();

        freeAgent.setId(1);
        List<Player> playerList = new ArrayList<>();

        ILoadPlayerFactory playerFactory = new LoadPlayerMock();
        Player player = new Player(1, playerFactory);
        playerList.add(player);

        player = new Player(5, playerFactory);
        playerList.add(player);

        freeAgent.setPlayerList(playerList);

        return freeAgent;
    }

    @Override
    public void loadLeagueById(int id, League league) throws Exception {

        switch (new Long(id).intValue()){
            case 1:
                //all correct data
                league.setName("League1");
                league.setStartDate(new Date(2000, 0, 0));
                league.setEndDate(new Date(2050, 0, 0));
                league.setCountry("Canada");
                league.setCreatedBy(1);
                league.setConferenceList(formConferenceList());
                league.setFreeAgent(formFreeAgent());
                break;

            case 2:
                //name null
                league.setName(null);
                league.setStartDate(new Date(2000, 0, 0));
                league.setEndDate(new Date(2050, 0, 0));
                league.setCreatedBy(2);
                league.setConferenceList(formConferenceList());
                league.setFreeAgent(formFreeAgent());
                break;

            case 3:
                //end date less than start date
                league.setName("Invalid Date");
                league.setStartDate(new Date(2010, 0, 0));
                league.setEndDate(new Date(2000, 0, 0));
                league.setCreatedBy(3);
                league.setConferenceList(formConferenceList());
                league.setFreeAgent(formFreeAgent());
                break;
        }

    }

    @Override
    public League loadLeagueByName(String leagueName) throws Exception {
        League league = new League();
        league.setName("League1");
        league.setStartDate(new Date(2000, 0, 0));
        league.setEndDate(new Date(2050, 0, 0));
        league.setCountry("Canada");
        league.setCreatedBy(1);
        league.setConferenceList(formConferenceList());
        league.setFreeAgent(formFreeAgent());
        return league;
    }

}
