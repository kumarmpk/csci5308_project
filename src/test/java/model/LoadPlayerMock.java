package model;

import data.ILoadPlayerFactory;
import java.util.Date;

public class LoadPlayerMock implements ILoadPlayerFactory {

    @Override
    public void loadPlayerById(int id, Player player){

        switch (new Long(id).intValue()){
            case 1:
                //all correct data
                player.setName("Player1");
                player.setStartDate(new Date(2000, 0, 0));
                player.setEndDate(new Date(2050, 0, 0));
                player.setAge(15);
                player.setHometown("Halifax");
                player.setPosition("goalie");
                player.setTeamId(1);
                player.setCaptain(true);
                break;

            case 2:
                //name null
                player.setName(null);
                player.setStartDate(new Date(2000, 0, 0));
                player.setEndDate(new Date(2050, 0, 0));
                player.setAge(15);
                player.setHometown("Halifax");
                player.setPosition("goalie");
                player.setTeamId(1);
                player.setCaptain(false);
                break;

            case 3:
                //end date less than start date
                player.setName("Invalid Date");
                player.setStartDate(new Date(2010, 0, 0));
                player.setEndDate(new Date(2000, 0, 0));
                player.setAge(15);
                player.setHometown("Halifax");
                player.setPosition("goalie");
                player.setTeamId(1);
                player.setCaptain(true);
                break;

            case 4:
                //invalid position
                player.setName("Invalid Position");
                player.setStartDate(new Date(2010, 0, 0));
                player.setEndDate(new Date(2000, 0, 0));
                player.setAge(15);
                player.setHometown("Halifax");
                player.setPosition("referee");
                player.setTeamId(1);
                player.setCaptain(false);
                break;

            case 5:
                //all correct data
                player.setName("Player5");
                player.setStartDate(new Date(2010, 0, 0));
                player.setEndDate(new Date(2040, 0, 0));
                player.setAge(17);
                player.setHometown("Montreal");
                player.setPosition("forward");
                player.setTeamId(1);
                player.setCaptain(true);
                break;
        }

    }

}
