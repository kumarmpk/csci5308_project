package model;

import data.ILoadConferenceFactory;
import data.ILoadDivisionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ConferenceTest {

    private static ILoadConferenceFactory factory;

    @BeforeClass
    public static void setFactoryObj(){
        factory = new LoadConferenceMock();
    }

    @Test
    public void defaultConstructorTest() {
        Conference conference = new Conference();
        assertEquals(conference.getId(), 0);
    }

    @Test
    public void conferenceTest() {
        Conference conference = new Conference(1);
        assertEquals(conference.getId(), 1);
    }

    @Test
    public void conferenceFactoryTest() throws Exception {
        Conference conference = new Conference(1, factory);
        assertEquals(conference.getId(), 1);
        assertEquals(conference.getName(), "Conference1");

        conference = new Conference(2, factory);
        assertNull(conference.getName());
    }

    @Test
    public void getLeagueIdTest() throws Exception {
        Conference conference = new Conference(1, factory);
        assertTrue(conference.getLeagueId() == (1));
    }

    @Test
    public void setLeagueIdTest(){
        Conference conference = new Conference();
        int leagueId = 1;
        conference.setLeagueId( leagueId);
        assertTrue(conference.getLeagueId() == leagueId);
    }

    @Test
    public void getDivisionListTest() throws Exception {
        Conference conference = new Conference(1, factory);
        List<Division> divisionList = conference.getDivisionList();
        assertNotNull(divisionList);

        assertTrue(divisionList.get(0).getId() == (1));
        assertTrue(divisionList.get(1).getId() == (2));
        assertTrue(divisionList.get(0).getName().equals("Division1"));
        assertNull(divisionList.get(1).getName());
    }

    @Test
    public void setDivisionListTest() throws Exception {
        ILoadDivisionFactory divisionFactory = new LoadDivisionMock();
        List<Division> divisionList = new ArrayList<>();
        Division division = new Division(1, divisionFactory);
        divisionList.add(division);
        division = new Division(2, divisionFactory);
        divisionList.add(division);

        Conference conference = new Conference();
        conference.setDivisionList(divisionList);

        assertTrue(conference.getDivisionList().get(0).getId() == (1));
        assertTrue(conference.getDivisionList().get(1).getId() == (2));
        assertTrue(conference.getDivisionList().get(0).getName().equals("Division1"));
        assertNull(conference.getDivisionList().get(1).getName());
    }


}
