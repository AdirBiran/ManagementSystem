package Presentation;

import Domain.Asset;
import Domain.Team;
import Presentation.User;

import java.util.HashMap;
import java.util.List;

public abstract class Manager extends User {

    public Manager(String firstName,String lastName, String ID, String mail) {
        super(firstName,lastName, ID, mail);
    }

    /**
     *
     */
    public void ManageAssets()
    {

    }

}
