package state;

import dao.*;
import data.*;
import model.*;
import org.icehockey.GetInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateTeamState implements IHockeyState {

    private HockeyContext hockeyContext;
    private League league;
    private String conferenceName;
    private String divisionName;
    private String teamName;
    private String generalManagerName;
    private String headCoachName;


    public CreateTeamState(HockeyContext hockeyContext){
        this.hockeyContext = hockeyContext;
        this.league = hockeyContext.getLeague();
    }

    @Override
    public void entry() {

        conferenceName  = GetInput.getUserInput("Please enter conference name the team belongs to");

        List<Conference> conferenceList =  league.getConferenceList();

        for(Conference conference: conferenceList ){
            while(!(conference.getName().equals(conferenceName))){
                conferenceName  = GetInput.getUserInput("Please enter conference name from the existing ones");
            }
        }
        divisionName  = GetInput.getUserInput("Please enter division name the team belongs to");

        for(Conference conference : conferenceList){
            for(Division division : conference.getDivisionList()){
                while(!(division.getName().equals(divisionName))){
                    divisionName  = GetInput.getUserInput("Please enter division name from the existing ones");
                }
            }
        }
        teamName  = GetInput.getUserInput("Please enter team name");

        if(teamName.isEmpty()){
            System.out.println("Please enter the team name!");
        }
        generalManagerName  = GetInput.getUserInput("Please enter name of general manager");

        headCoachName  = GetInput.getUserInput("Please enter name of head coach ");
    }

    @Override
    public void process() {
        //Instantiate Model Objects

        List<Conference> conferenceList = league.getConferenceList();
        for(Conference conference : conferenceList ){
            if(conference.getName().equals(conferenceName)){
                List<Division> divisionList  = conference.getDivisionList();
                for(Division division: divisionList){
                    if(division.getName().equals(divisionName)) {
                        Team team = new Team();
                        team.setName(teamName);
                        team.setHeadCoach(headCoachName);
                        team.setGeneralManager(generalManagerName);

                        division.getTeamList().add(team);

                    }
                }
                conference.setDivisionList(divisionList);
            }
        }

        league.setConferenceList(conferenceList);

        hockeyContext.setLeague(league);
    }

    @Override
    public IHockeyState exit() {
        PlayerChoiceState playerChoiceState = null;
        if (league != null) {
            //Persist to DB and transition to next state
            league.setCreatedBy(hockeyContext.getUser().getId());
            IAddLeagueFactory leagueDao = new AddLeagueDao();
            IAddConferenceFactory conferenceDao = new AddConferenceDao();
            IAddDivisionFactory divisionDao = new AddDivisionDao();
            IAddTeamFactory teamDao = new AddTeamDao();


            IAddSeasonFactory seasonDao = new AddSeasonDao();

            Season season = new Season();
            season.setName("2020");

            try {
                int leagueId = leagueDao.addLeague(league);
                int seasonId = seasonDao.addSeason(season);

                if(leagueId != 0 && seasonId != 0){
                    if(league.getFreeAgent() != null) {
                        int freeAgentId = addFreeAgent(leagueId, seasonId);
                        addPlayerList(freeAgentId, seasonId, league.getFreeAgent().getPlayerList());
                    }
                    if(league.getConferenceList() != null && !league.getConferenceList().isEmpty()){
                        for (Conference conference : league.getConferenceList()) {
                            conference.setLeagueId(leagueId);
                            int conferenceId = conferenceDao.addConference(conference);

                            for (Division division : conference.getDivisionList()) {
                                division.setConferenceId(conferenceId);
                                int divisionId = divisionDao.addDivision(division);

                                for (Team team : division.getTeamList()) {
                                    team.setDivisionId(divisionId);
                                    int teamId = teamDao.addTeam(team);
                                    addPlayerList(teamId, seasonId, team.getPlayerList());
                                }
                            }

                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Unable to save the league! Please try again");
                System.exit(1);
                e.printStackTrace();
            }
            playerChoiceState = new PlayerChoiceState(hockeyContext, "How many seasons do you want to simulate", "createOrLoadTeam");

        }
        return playerChoiceState;
    }

    private int addFreeAgent(int leagueId, int seasonId) throws Exception {
        IAddFreeAgentFactory freeAgentDao = new AddFreeAgentDao();
        FreeAgent freeAgent = league.getFreeAgent();
        freeAgent.setSeasonId(seasonId);
        freeAgent.setLeagueId(leagueId);
        return freeAgentDao.addFreeAgent(freeAgent);
    }

    private void addPlayerList(int parentId, int seasonId, List<Player> playerList) throws Exception {
        if(playerList != null && !playerList.isEmpty()) {
            IAddPlayerFactory playerDao = new AddPlayerDao();
            for (Player player : playerList) {
                player.setFreeAgentId(parentId);
                player.setSeasonId(seasonId);
                playerDao.addPlayer(player);
            }
        }
    }

}
