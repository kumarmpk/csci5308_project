package simulation.state.gamestatemachine;

import simulation.model.GameSimulation;
import simulation.model.IPlayer;
import simulation.model.ISimulate;
import simulation.model.Shift;
import simulation.state.GameContext;
import simulation.state.HockeyContext;
import simulation.state.IGameState;
import simulation.trophyPublisherSubsribers.TrophySystemPublisher;

import java.util.HashMap;
import java.util.Random;

public class GoalState implements IGameState {

    Random rand;
    GameContext gameContext;
    GameSimulation gameSimulation;
    Shift offensive;
    Shift defensive;
    ISimulate simulateConfig;
    boolean goal;

    public GoalState(GameContext gameContext) {
        simulateConfig  = HockeyContext.getInstance().getUser().getLeague().getGamePlayConfig().getSimulate();
        rand = new Random();
        this.gameContext = gameContext;
        gameSimulation = gameContext.getGameSimulation();
        offensive = gameContext.getOffensive();
        defensive = gameContext.getDefensive();
    }

    @Override
    public IGameState process() throws Exception {
        if(offensive.getGoalie().getSaving() > defensive.getGoalie().getSaving()){
            goal = true;
        }else{
            goal = false;
        }
        if(rand.nextDouble() < simulateConfig.getUpset()){
            reverseGoal();
        }

        if(goal && (rand.nextDouble() < simulateConfig.getGoalChance())){
            IPlayer forwardWhoMadeAGoal = offensive.getForward().get(rand.nextInt(offensive.getForward().size()));
            updateTrophyPublisherGoal(forwardWhoMadeAGoal);
            goal = true;
        }else{
            IPlayer goalieWhoSavedFromAGoal = defensive.getGoalie();
            updateTrophyPublisherSave(goalieWhoSavedFromAGoal);
            goal = false;
        }
        updateSimulationStats();
        return null;
    }

    private boolean reverseGoal(){
        return !goal;
    }

    private void updateTrophyPublisherGoal(IPlayer forwardPlayer) {
        TrophySystemPublisher.getInstance().notify("goalScoreUpdate",forwardPlayer,1);
    }

    private void updateTrophyPublisherSave(IPlayer goalie) {
        TrophySystemPublisher.getInstance().notify("savesUpdate",goalie,1);
    }

    private void updateSimulationStats() {
        HashMap<String,Integer> goals = gameSimulation.getGoals();
        HashMap<String,Integer> shots = gameSimulation.getShots();
        HashMap<String,Integer> saves = gameSimulation.getSaves();
        shots.put(offensive.getTeamName(), shots.get(offensive.getTeamName()) + 1);
        if(goal){
            goals.put(offensive.getTeamName(), goals.get(offensive.getTeamName()) + 1);
        }else{
            saves.put(defensive.getTeamName(), saves.get(defensive.getTeamName()) + 1);
        }
    }
}
