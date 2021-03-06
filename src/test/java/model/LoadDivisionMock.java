package model;

import data.ILoadTeamFactory;
import data.ILoadDivisionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadDivisionMock implements ILoadDivisionFactory {

    private List formTeamList() throws Exception {
        List<Team> teamList = new ArrayList<>();

        ILoadTeamFactory teamFactory = new LoadTeamMock();
        Team team = new Team(1, teamFactory);
        teamList.add(team);

        team = new Team(2, teamFactory);
        teamList.add(team);

        return teamList;
    }

    @Override
    public void loadDivisionById(int id, Division division) throws Exception {

        switch (new Long(id).intValue()){
            case 1:
                //all correct data
                division.setName("Division1");
                division.setStartDate(new Date(2000, 0, 0));
                division.setEndDate(new Date(2050, 0, 0));
                division.setConferenceId(1);
                division.setTeamList(formTeamList());
                break;

            case 2:
                //name null
                division.setName(null);
                division.setStartDate(new Date(2000, 0, 0));
                division.setEndDate(new Date(2050, 0, 0));
                division.setConferenceId(1);
                division.setTeamList(formTeamList());
                break;

            case 3:
                //end date less than start date
                division.setName("Invalid Date");
                division.setStartDate(new Date(2010, 0, 0));
                division.setEndDate(new Date(2000, 0, 0));
                division.setConferenceId(1);
                division.setTeamList(formTeamList());
                break;
        }

    }

    @Override
    public Division loadDivisionByName(String divisionName) throws Exception {
        Division division = new Division();
        division.setName("Division1");
        division.setStartDate(new Date(2000, 0, 0));
        division.setEndDate(new Date(2050, 0, 0));
        division.setConferenceId(1);
        division.setTeamList(formTeamList());
        return division;
    }

}
