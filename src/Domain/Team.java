package Domain;

import Presentation.*;
import com.sun.media.jfxmediaimpl.platform.ios.IOSMediaPlayer;

import java.util.List;
import java.util.LinkedList;

public class Team {

    private String name;
    private int wins;
    private int losses;
    private int draws;
    private PersonalPage page;
    private List<TeamOwner> teamOwners; // at least one
    private List<TeamManager> teamManagers;
    private List<Player> players; // at least 11
    private List<Coach> coaches; // at least one
    private Budget budget;
    private List<Game> games;
    private Field field;
    private boolean active;
    private boolean permanentlyClosed; //closed by admin and cannot open again

    public Team(String name, PersonalPage page, List<TeamOwner> teamOwners, List<Player> players, List<Coach> coaches, Budget budget, Field field) {
        this.name = name;
        this.page = page;
        if(teamOwners==null||teamOwners.size()<1)
            throw new RuntimeException("not enough TeamOwners");
        this.teamOwners = teamOwners;
        if(players==null||players.size()<11)
            throw new RuntimeException("not enough Players");
        this.players = players;
        if(coaches==null||coaches.size()<1)
            throw new RuntimeException("not enough Coaches");
        this.coaches = coaches;
        this.budget = budget;
        this.field = field;
        this.wins=0;
        this.losses=0;
        this.draws=0;
        this.teamManagers=new LinkedList<>();
        this.games = new LinkedList<>();
        this.active = true;
        this.permanentlyClosed = false;
    }

    public void addAWin(int wins) {
        this.wins ++;
    }

    public void addALoss(int losses) {
        this.losses ++;
    }

    public void addADraw(int draws) {
        this.draws ++;
    }

    public void addTeamOwner(TeamOwner teamOwner) {
        if(!teamOwners.contains(teamOwner))
            this.teamOwners.add(teamOwner);
    }

    public void addTeamManager(TeamManager teamManager) {
        if(!teamManagers.contains(teamManager))
            this.teamManagers.add(teamManager);
    }

    public void addPlayer(Player player) {
        if(!players.contains(player))
            this.players.add(player);
    }

    public void addCoach(Coach coach) {
        if(!coaches.contains(coach))
            this.coaches.add(coach);
    }

    public void addGame(Game game) {
        if(!games.contains(game))
        this.games.add(game);
    }
    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public PersonalPage getPage() {
        return page;
    }

    public List<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public List<TeamManager> getTeamManagers() {
        return teamManagers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<Game> getGames() {
        return games;
    }

    public Field getField() {
        return field;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPermanentlyClosed() {
        return permanentlyClosed;
    }

    public void setPermanentlyClosed(boolean permanentlyClosed) {
        this.permanentlyClosed = permanentlyClosed;
    }
}
