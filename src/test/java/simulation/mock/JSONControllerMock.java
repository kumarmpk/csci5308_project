package simulation.mock;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONControllerMock {

    public static JSONObject getJSON(int id) {

        return createJSON();
    }

    private static JSONObject createJSON() {
        JSONObject league = new JSONObject();
        JSONObject gameplayConfig = new JSONObject();
        JSONArray freeAgents = new JSONArray();
        JSONArray players = new JSONArray();
        JSONArray conferences = new JSONArray();
        JSONArray divisions = new JSONArray();
        JSONArray teams = new JSONArray();
        JSONArray coaches = new JSONArray();
        JSONArray managers = new JSONArray();

        JSONObject aging = new JSONObject();
        aging.put("averageRetirementAge", (long) 35);
        aging.put("maximumAge", (long) 50);

        JSONObject gameResolver = new JSONObject();
        gameResolver.put("randomWinChance", 0.1);

        JSONObject injuries = new JSONObject();
        injuries.put("randomInjuryChance", 0.05);
        injuries.put("injuryDaysLow", (long) 1);
        injuries.put("injuryDaysHigh", (long) 260);

        JSONObject training = new JSONObject();
        training.put("daysUntilStatIncreaseCheck", (long) 100);

        JSONObject trading = new JSONObject();
        trading.put("lossPoint", (long) 8);
        trading.put("randomTradeOfferChance", 0.05);
        trading.put("maxPlayersPerTrade", (long) 2);
        trading.put("randomAcceptanceChance", 0.05);

        gameplayConfig.put("aging", aging);
        gameplayConfig.put("gameResolver", gameResolver);
        gameplayConfig.put("injuries", injuries);
        gameplayConfig.put("training", training);
        gameplayConfig.put("trading", trading);

        addFreePlayers(freeAgents);
        addPlayers(players);

        JSONObject teamCoach = new JSONObject();
        teamCoach.put("name", "Mary Smith");
        teamCoach.put("skating", 0.5);
        teamCoach.put("shooting", 0.8);
        teamCoach.put("checking", 0.3);
        teamCoach.put("saving", 0.5);


        JSONObject team = new JSONObject();
        team.put("players", players);
        team.put("teamName", "Boston");
        team.put("generalManager", "Mister Fred");
        team.put("headCoach", teamCoach);

        teams.add(team);

        JSONObject division = new JSONObject();
        division.put("divisionName", "Atlantic");
        division.put("teams", teams);

        divisions.add(division);

        JSONObject conference = new JSONObject();
        conference.put("conferenceName", "Eastern Conference");
        conference.put("divisions", divisions);

        conferences.add(conference);

        JSONObject coach1 = new JSONObject();
        coach1.put("name", "Joe Smith");
        coach1.put("skating", 0.5);
        coach1.put("shooting", 0.8);
        coach1.put("checking", 0.3);
        coach1.put("saving", 1.0);
        coaches.add(coach1);

        JSONObject coach2 = new JSONObject();
        coach2.put("name", "Frank Smith");
        coach2.put("skating", 0.5);
        coach2.put("shooting", 0.8);
        coach2.put("checking", 0.3);
        coach2.put("saving", 0.5);
        coaches.add(coach2);

        JSONObject coach3 = new JSONObject();
        coach3.put("name", "Samantha Smith");
        coach3.put("skating", 1.0);
        coach3.put("shooting", 0.5);
        coach3.put("checking", 0.5);
        coach3.put("saving", 0.0);
        coaches.add(coach3);

        managers.add("Karen Potam");
        managers.add("Joseph Squidly");
        managers.add("Tom Spaghetti");

        league.put("leagueName", "Dalhousie Hockey League");
        league.put("gameplayConfig", gameplayConfig);
        league.put("conferences", conferences);
        league.put("freeAgents", freeAgents);
        league.put("coaches", coaches);
        league.put("generalManagers", managers);
        league.put("daysUntilStatIncreaseCheck", 100);

        return league;
    }

    private static void addFreePlayers(JSONArray freeAgents) {
        for (int i = 0; i < 30; i++) {
            JSONObject forward = new JSONObject();
            forward.put("playerName", "Agent " + i);
            forward.put("position", "forward");
            forward.put("captain", false);
            forward.put("age", (long) 27);
            forward.put("skating", (long) 12);
            forward.put("shooting", (long) 18);
            forward.put("checking", (long) 11);
            forward.put("saving", (long) 1);
            freeAgents.add(forward);
        }
        for (int i = 30; i < 55; i++) {
            JSONObject defense = new JSONObject();
            defense.put("playerName", "Agent " + i);
            defense.put("position", "defense");
            defense.put("captain", false);
            defense.put("age", (long) 24);
            defense.put("skating", (long) 13);
            defense.put("shooting", (long) 18);
            defense.put("checking", (long) 19);
            defense.put("saving", (long) 1);
            freeAgents.add(defense);
        }
        for (int i = 55; i < 64; i++) {
            JSONObject goalie = new JSONObject();
            goalie.put("playerName", "Agent " + i);
            goalie.put("position", "goalie");
            goalie.put("captain", false);
            goalie.put("age", (long) 27);
            goalie.put("skating", (long) 10);
            goalie.put("shooting", (long) 9);
            goalie.put("checking", (long) 12);
            goalie.put("saving", (long) 13);
            freeAgents.add(goalie);
        }
    }

    private static void addPlayers(JSONArray players) {
        for (int i = 0; i < 16; i++) {
            JSONObject forward = new JSONObject();
            forward.put("playerName", "Player " + i);
            forward.put("position", "forward");
            forward.put("captain", false);
            forward.put("age", (long) 27);
            forward.put("skating", (long) 12);
            forward.put("shooting", (long) 18);
            forward.put("checking", (long) 11);
            forward.put("saving", (long) 1);
            players.add(forward);
        }
        for (int i = 16; i < 26; i++) {
            JSONObject defense = new JSONObject();
            defense.put("playerName", "Player " + i);
            defense.put("position", "defense");
            defense.put("captain", false);
            defense.put("age", (long) 24);
            defense.put("skating", (long) 13);
            defense.put("shooting", (long) 18);
            defense.put("checking", (long) 19);
            defense.put("saving", (long) 1);
            players.add(defense);
        }
        for (int i = 26; i < 30; i++) {
            JSONObject goalie = new JSONObject();
            goalie.put("playerName", "Player " + i);
            goalie.put("position", "goalie");
            goalie.put("captain", false);
            goalie.put("age", (long) 27);
            goalie.put("skating", (long) 10);
            goalie.put("shooting", (long) 9);
            goalie.put("checking", (long) 12);
            goalie.put("saving", (long) 13);
            players.add(goalie);
        }
    }


}
