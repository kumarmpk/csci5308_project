package simulation.state.gamestatemachine;

import simulation.model.GameSimulation;
import simulation.model.ISimulate;
import simulation.model.Shift;
import simulation.state.GameContext;
import simulation.state.HockeyContext;
import simulation.state.IGameState;

import java.util.Random;

public class ShootingState implements IGameState {

    Random rand;
    GameContext gameContext;
    GameSimulation gameSimulation;
    Shift team1Shift;
    Shift team2Shift;
    Shift offensive;
    Shift defensive;
    ISimulate simulateConfig;

    public ShootingState(GameContext gameContext) {
        simulateConfig  = HockeyContext.getInstance().getUser().getLeague().getGamePlayConfig().getSimulate();
        rand = new Random();
        this.gameContext = gameContext;
        this.offensive = gameContext.getOffensive();
        this.defensive = gameContext.getDefensive();
        this.gameSimulation = gameContext.getGameSimulation();
        this.team1Shift = gameSimulation.getTeam1Shift();
        this.team2Shift = gameSimulation.getTeam2Shift();
    }

    @Override
    public IGameState process() throws Exception {
        if(team1Shift.getTeamShiftShootingTotal() > team2Shift.getTeamShiftShootingTotal()){
            offensive = team1Shift;
            defensive = team2Shift;
        }else{
            offensive = team2Shift;
            defensive = team1Shift;
        }
        if(rand.nextDouble() < simulateConfig.getUpset()){
            Shift temp = offensive;
            offensive = defensive;
            defensive = temp;
        }
        gameContext.setDefensive(defensive);
        gameContext.setOffensive(offensive);
        return next();
    }

    public IGameState next() throws Exception{
        return new DefenseState(gameContext);
    }
}
