package model;

import common.Constants;
import data.IPlayerFactory;
import data.IUserFactory;

public class User extends ParentObj{

    public User(){}

    public User(long id){
        setId(id);
    }

    public User(long id, IUserFactory factory){
        setId(id);
        factory.loadUser(id, this);
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
