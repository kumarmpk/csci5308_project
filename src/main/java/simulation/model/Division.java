package simulation.model;

import simulation.dao.IDivisionDao;
import simulation.dao.ITeamDao;
import java.util.ArrayList;
import java.util.List;

public class Division extends SharedAttributes implements IDivision {

    private int conferenceId;
    private List<ITeam> teamList = new ArrayList<>();

    public Division() {
        setId(System.identityHashCode(this));
    }

    public Division(int id) {
        setId(id);
    }

    public Division(int id, IDivisionDao factory) throws Exception {
        if (factory == null) {
            return;
        }
        setId(id);
        factory.loadDivisionById(id, this);
    }

    public Division(simulation.serializers.ModelsForDeserialization.model.Division divisionFromDeserialization){
        this.conferenceId = divisionFromDeserialization.conferenceId;
        for(simulation.serializers.ModelsForDeserialization.model.Team team : divisionFromDeserialization.teamList){
            this.teamList.add(new Team(team));
        }
        this.setName(divisionFromDeserialization.name);
        this.setId(divisionFromDeserialization.id);
    }

    public List<ITeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<ITeam> teamList) {
        if (teamList == null) {
            return;
        }
        this.teamList = teamList;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void addDivision(IDivisionDao addDivisionFactory) throws Exception {
        if (addDivisionFactory == null) {
            return;
        }
        addDivisionFactory.addDivision(this);
    }

    public void loadTeamListByDivisionId(ITeamDao teamFactory) throws Exception {
        if (teamFactory == null) {
            return;
        }
        this.teamList = teamFactory.loadTeamListByDivisionId(getId());
    }

    public List<String> getTeamNameList() {
        List<String> teamNameList = new ArrayList<>();
        for (ITeam team : this.getTeamList()) {
            teamNameList.add(team.getName().toLowerCase());
        }
        return teamNameList;
    }

}
