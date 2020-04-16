package Domain;

import Presentation.*;

import java.util.List;
import java.util.LinkedList;

public class Team{

    private String id;
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
    private List<Field> fields;
    private boolean active;
    private boolean permanentlyClosed; //closed by admin and cannot open again

    public Team(String name, PersonalPage page, List<TeamOwner> teamOwners, List<Player> players, List<Coach> coaches, Field field) {
        this.id = "T"+IdGenerator.getNewId();
        this.name = name;
        this.page = page;

        if(teamOwners==null||teamOwners.size()<1)
           throw new RuntimeException("not enough TeamOwners");
        this.teamOwners = teamOwners;
        linkTeamOwner();
        if(players==null||players.size()<11)
            throw new RuntimeException("not enough Players");
        this.players = players;
        linkPlayers();
        if(coaches==null||coaches.size()<1)
            throw new RuntimeException("not enough Coaches");
        this.coaches = coaches;
        linkCoaches();
        this.budget = new Budget(this);
        this.fields = new LinkedList<>();
        fields.add(field);
        linkField();
        this.wins=0;
        this.losses=0;
        this.draws=0;
        this.teamManagers=new LinkedList<>();
        this.games = new LinkedList<>();
        this.active = true;
        this.permanentlyClosed = false;
    }

    private void linkField() {
        for(Field field :fields){
            field.setTeam(this);
        }

    }

    private void linkCoaches() {
        for(Coach coach :coaches){
            coach.addTeam(this);
        }
    }

    private void linkPlayers() {
        for(Player player :players){
            player.addTeam(this);
        }
    }

    private void linkTeamOwner() {
        for(TeamOwner to:teamOwners){
            to.addTeam(this);
        }
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", permanentlyClosed=" + permanentlyClosed +
                '}';
    }

    public void addAWin() {
        this.wins ++;
    }

    public void addALoss() {
        this.losses ++;
    }

    public void addADraw() {
        this.draws ++;
    }

    public void addTeamOwner(TeamOwner teamOwner) {
        if(!teamOwners.contains(teamOwner)) {
            this.teamOwners.add(teamOwner);
            teamOwner.setAmountOfTeams(1);
        }
    }

    public void addTeamManager(TeamManager teamManager) {
        if(!teamManagers.contains(teamManager)) {
            this.teamManagers.add(teamManager);
            teamManager.setAmountOfTeams(1);
        }
    }

    public void addPlayer(Player player) {
        if(!players.contains(player)) {
            this.players.add(player);
            player.setAmountOfTeams(1);
        }
    }

    public void addCoach(Coach coach) {
        if(!coaches.contains(coach)) {
            this.coaches.add(coach);
            coach.setAmountOfTeams(1);
        }
    }

    public void addGame(Game game) {
        if(!games.contains(game))
        this.games.add(game);
    }



    /**remove**/

    public void removeTeamOwner(TeamOwner teamOwner) {
        if(teamOwners.contains(teamOwner)) {
            this.teamOwners.remove(teamOwner);
            teamOwner.setAmountOfTeams(-1);
        }
    }

    public void removeTeamManager(TeamManager teamManager) {
        if(teamManagers.contains(teamManager)) {
            this.teamManagers.remove(teamManager);
            teamManager.setAmountOfTeams(-1);
        }
    }

    public void removePlayer(Player player) {
        if(players.contains(player)) {
            this.players.remove(player);
            player.setAmountOfTeams(-1);
        }
    }

    public void removeCoach(Coach coach) {
        if(coaches.contains(coach)) {
            this.coaches.remove(coach);
            coach.setAmountOfTeams(-1);
        }
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

    /**/
    public List<Field> getFields() {
        return fields;
    }

    /**LIAT--GET ONE FIELD*/
    public Field getField() {
        return fields.get(0);
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


    public String getID() {
        return id;
    }


    public double getPrice() {
        return 0;
    }
}
