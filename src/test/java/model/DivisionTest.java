package model;

import data.ILoadTeamFactory;
import data.ILoadDivisionFactory;
import org.junit.Test;
import org.junit.BeforeClass;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class DivisionTest {

    private static ILoadDivisionFactory factory;

    @BeforeClass
    public static void setFactoryObj(){
        factory = new LoadDivisionMock();
    }

    @Test
    public void defaultConstructorTest() {
        Division division = new Division();
        assertEquals(division.getId(), 0);
    }

    @Test
    public void divisionTest() {
        Division division = new Division(1);
        assertEquals(division.getId(), 1);
    }

    @Test
    public void divisionFactoryTest() throws Exception {
        Division division = new Division(1, factory);
        assertEquals(division.getId(), 1);
        assertEquals(division.getName(), "Division1");

        division = new Division(2, factory);
        assertNull(division.getName());
    }

    @Test
    public void getConferenceIdTest() throws Exception {
        Division division = new Division(1, factory);
        assertTrue(division.getConferenceId() == (1));
    }

    @Test
    public void setConferenceIdTest(){
        Division division = new Division();
        int conferenceId = 1;
        division.setConferenceId(conferenceId);
        assertTrue(division.getConferenceId() == conferenceId);
    }

    @Test
    public void getTeamListTest() throws Exception {
        Division division = new Division(1, factory);
        List<Team> teamList = division.getTeamList();
        assertNotNull(teamList);
        assertTrue(teamList.get(0).getId() == (1));
        assertTrue(teamList.get(1).getId() == (2));
        assertTrue(teamList.get(0).getName().equals("Team1"));
        assertNull(teamList.get(1).getName());
    }

    @Test
    public void setPlayerListTest() throws Exception {
        ILoadTeamFactory teamFactory = new LoadTeamMock();
        List<Team> teamList = new ArrayList<>();
        Team team = new Team(1, teamFactory);
        teamList.add(team);
        team = new Team(2, teamFactory);
        teamList.add(team);

        Division division = new Division();
        division.setTeamList(teamList);

        assertTrue(division.getTeamList().get(0).getId() == (1));
        assertTrue(division.getTeamList().get(1).getId() == (2));
        assertTrue(division.getTeamList().get(0).getName().equals("Team1"));
        assertNull(division.getTeamList().get(1).getName());
    }


}
