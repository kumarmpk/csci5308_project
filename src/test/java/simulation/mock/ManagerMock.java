package simulation.mock;

import db.data.IManagerFactory;
import simulation.model.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerMock implements IManagerFactory {

    @Override
    public int addManager(Manager manager) throws Exception {
        manager = new Manager(1);
        return manager.getId();

    }

    @Override
    public void loadManagerById(int managerId, Manager manager) throws Exception {
        switch (managerId){
            case 1:
                manager.setName("Sam Mathew");
                manager.setLeagueId(1);
                manager.setTeamId(0);
                break;
            case 2:
                manager.setName("Sid Patric");
                manager.setLeagueId(1);
                manager.setTeamId(0);
                break;
            case 3:
                manager.setName("Mark Mark");
                manager.setLeagueId(1);
                manager.setTeamId(0);
                break;
            case 4:
                manager.setName("Ranbir Kapoor");
                manager.setLeagueId(1);
                manager.setTeamId(0);
                break;
            case 5:
                manager.setName("Manager 5");
                manager.setLeagueId(1);
                manager.setTeamId(0);
                break;
            case 6:
                manager.setName("Manager 6");
                manager.setLeagueId(1);
                manager.setTeamId(1);
                break;
        }
    }

    @Override
    public List<Manager> loadFreeManagersByLeagueId(int leagueId) throws Exception {
        List<Manager> managerList = new ArrayList<>();
        IManagerFactory managerFactory = new ManagerMock();
        for(int i=1;i<=5; i++){
            Manager manager = new Manager(i,managerFactory);
            managerList.add(manager);
        }
        return managerList;
    }

    @Override
    public Manager loadManagerByTeamId(int teamId) throws Exception {
        Manager manager = new Manager();
        manager.setTeamId(1);
        manager.setName("Alix Sarty");
        manager.setId(1);
        manager.setLeagueId(1);
        return manager;
    }
}