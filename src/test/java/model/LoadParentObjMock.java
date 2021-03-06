package model;

import data.ILoadParentObjFactory;
import java.util.Date;

public class LoadParentObjMock implements ILoadParentObjFactory {

    @Override
    public void loadParentObj(int id, ParentObj parentObj){

        switch (new Long(id).intValue()){
            case 1:
                //all valid data
                parentObj.setName("Parent");
                parentObj.setStartDate(new Date(2000, 0, 0));
                parentObj.setEndDate(new Date(2050, 0, 0));
                break;

            case 2:
                //invalid name
                parentObj.setName(null);
                parentObj.setStartDate(new Date(2000, 0, 0));
                parentObj.setEndDate(new Date(2050, 0, 0));
                break;

            case 3:
                //invalid date
                parentObj.setName("Invalid Date");
                parentObj.setStartDate(new Date(2010, 0, 0));
                parentObj.setEndDate(new Date(2000, 0, 0));
                break;
        }
    }
}
