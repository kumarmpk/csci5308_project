package simulation.state.gamestatemachine;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import simulation.dao.IUserDao;
import simulation.factory.HockeyContextConcrete;
import simulation.factory.IHockeyContextFactory;
import simulation.mock.UserMock;
import simulation.model.GameSimulation;
import simulation.model.ITeam;
import simulation.model.User;
import simulation.state.GameContext;
import simulation.state.IGameState;
import simulation.state.IHockeyContext;

import static org.junit.Assert.*;

public class PenaltyStateTest {

    private static IHockeyContext hockeyContext;
    private static IUserDao userFactory;
    private static IHockeyContextFactory hockeyContextFactory;
    private static ITeam team11;
    private static ITeam team12;
    private static GameSimulation gameSimulation;
    private static GameContext gameContext;

    @BeforeClass
    public static void init() throws Exception {
        userFactory = new UserMock();
        hockeyContextFactory = HockeyContextConcrete.getInstance();
        hockeyContext = hockeyContextFactory.newHockeyContext();
        User user = new User(4, userFactory);
        hockeyContext.setUser(user);
        team11 = hockeyContext.getUser().getLeague().getTeamByTeamName("Team11");
        team12 = hockeyContext.getUser().getLeague().getTeamByTeamName("Team12");
        gameSimulation = new GameSimulation(team11,team12);
        gameSimulation.setTeam1Shift(gameSimulation.getTeam1Shift().getShift(team11,gameSimulation.getTeamPlayersCount()));
        gameSimulation.setTeam2Shift(gameSimulation.getTeam2Shift().getShift(team12,gameSimulation.getTeamPlayersCount()));
        gameContext = new GameContext(gameSimulation);
        gameContext.setOffensive(gameSimulation.getTeam1Shift());
        gameContext.setDefensive(gameSimulation.getTeam2Shift());
    }

    @Test
    public void testConstructor(){
        PenaltyState penaltyState  = new PenaltyState(gameContext);
        assertTrue(penaltyState instanceof PenaltyState);
    }

    @Test
    public void testProcess() throws Exception {
        PenaltyState penaltyState  = new PenaltyState(gameContext);
        assertNull(penaltyState.process());
    }

    @Test
    public void testNext() throws Exception {
        PenaltyState penaltyState  = new PenaltyState(gameContext);
        assertTrue(penaltyState.next() == null);
        assertNull(penaltyState.next());
    }

}