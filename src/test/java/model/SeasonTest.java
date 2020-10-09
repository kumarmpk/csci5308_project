package model;

import dao.AddSeasonDao;
import data.IAddSeasonFactory;
import data.ILoadSeasonFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeasonTest {

    private static ILoadSeasonFactory loadSeasonFactory;
    private static IAddSeasonFactory addSeasonFactory;

    @BeforeClass
    public static void setFactoryObj(){
        loadSeasonFactory = new LoadSeasonMock();
        addSeasonFactory = new AddSeasonMock();
    }

    @Test
    public void defaultConstructorTest() {
        Season season = new Season();
        assertEquals(season.getId(), 0);
    }

    @Test
    public void seasonTest() {
        Season season = new Season(1);
        assertEquals(season.getId(), 1);
    }

    @Test
    public void seasonFactoryTest() throws Exception {
        Season season = new Season(1, loadSeasonFactory);
        assertEquals(season.getId(), 1);
        assertEquals(season.getName(), "Season1");

        season = new Season(2, loadSeasonFactory);
        assertNull(season.getName());
    }

    @Test
    public void addSeasonTest() throws Exception {
        Season season = new Season();
        season.setId(1);
        season.setName("Season1");
        season.addSeason(addSeasonFactory);
        assertTrue(1 == season.getId());
        assertTrue("Season1".equals(season.getName()));
    }

}
