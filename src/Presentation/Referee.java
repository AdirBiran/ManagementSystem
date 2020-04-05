package Presentation;

import Domain.Game;
import java.util.List;
import java.util.LinkedList;

public class Referee extends User {


    private String training;
    private List<Game> games;

    public Referee(String name, String ID, String mail, String training) {
        super(name, ID, mail);
        this.training = training;
        this.games = new LinkedList<>();
    }

// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void addGame(Game game) {
        if(!games.contains(game))
            this.games.add(game);
    }

    /**
     *
     */
    public void viewGames()
    {

    }

    /**
     *
     */
    public void addEvent()
    {

    }

    /**
     *
     */
    public void generateGameReport()
    {

    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return training;
    }


}
