package simulation.model;


import db.data.IPlayerFactory;
import db.data.ITeamFactory;
import userIO.ConsoleOutput;
import userIO.ConsoleOutputForTeamCreation;
import userIO.GetInput;
import userIO.UseInputForTeamCreation;

import java.util.ArrayList;
import java.util.List;

public class Team extends ParentObj{
    public static final String GOALIE = "goalie";
    public static final String DEFENSE = "defense";
    public static final String FORWARD = "forward";
    public Team(){}

    public Team(int id){
        setId(id);
    }

    public Team(int id, ITeamFactory factory) throws Exception {
        setId(id);
        factory.loadTeamById(id, this);
    }

    public Team(String name, ITeamFactory factory) throws Exception {
        factory.loadTeamByName(name, this);
    }

    private String hometown;

    private String mascot;

    private int divisionId;

    private Coach coach;
    private Manager manager;

//    private String generalManager;
//
//    private String headCoach;

    private List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void addTeam(ITeamFactory addTeamFactory) throws Exception {
        addTeamFactory.addTeam(this);
    }

    public void loadPlayerListByTeamId(IPlayerFactory loadPlayerFactory) throws Exception {
        this.playerList = loadPlayerFactory.loadPlayerListByTeamId(getId());
    }

    public List<Integer> createChosenPlayerIdList(FreeAgent freeAgent){
        UseInputForTeamCreation teamCreationInput = new UseInputForTeamCreation();
        ConsoleOutputForTeamCreation teamCreationOutput = new ConsoleOutputForTeamCreation();
        int numberOfSkaters=0;
        int numberOfGoalies=0;
        List<Player> freeAgentList = freeAgent.getPlayerList();
        List<Integer> totalOfSkillsList = new ArrayList<>();
        for(int i=0;i<freeAgentList.size();i++){
            Player freeAgentPlayer = freeAgentList.get(i);
            totalOfSkillsList.add(new Integer(freeAgentPlayer.sumOfSkills()));
        }

        List<Integer> goodFreeAgentsIdList = freeAgent.getGoodFreeAgentsList(totalOfSkillsList);
        teamCreationOutput.showInstructionsForTeamCreation();
        teamCreationOutput.showGoodFreeAgentList(freeAgentList,goodFreeAgentsIdList);
        teamCreationOutput.showBelowAverageFreeAgentList(freeAgentList,goodFreeAgentsIdList);

        List<Integer> chosenPlayersIdList = new ArrayList<>();
        int playerId;
        while(numberOfGoalies!=2 || numberOfSkaters!=18){
            playerId = teamCreationInput.getPlayerId(freeAgentList.size()-1);
            if(playerId<0 || playerId>=freeAgentList.size() || chosenPlayersIdList.contains(playerId)){
                continue;
            }
            Player player = freeAgentList.get(playerId);
            if(numberOfGoalies!=2 && player.getPosition().equals(GOALIE)){
                chosenPlayersIdList.add(playerId);
                numberOfGoalies=numberOfGoalies+1;
                teamCreationOutput.showCountOfNeededPlayers(2-numberOfGoalies, 18-numberOfSkaters);
            }else if(numberOfSkaters!=18 && (player.getPosition().equals(DEFENSE) || player.getPosition().equals(FORWARD))){
                chosenPlayersIdList.add(playerId);
                numberOfSkaters=numberOfSkaters+1;
                teamCreationOutput.showCountOfNeededPlayers(2-numberOfGoalies, 18-numberOfSkaters);
            }
        }
        return chosenPlayersIdList;
    }
}
