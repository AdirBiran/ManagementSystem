package Domain;

import java.util.List;
import java.util.LinkedList;

public class Team{

    private String id;
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private PersonalPage page;
    private List<User> teamOwners; // at least one
    private List<User> teamManagers;
    private List<Player> players; // at least 11
    private List<Coach> coaches; // at least one
    private Budget budget;
    private List<Game> games;
    private List<Field> fields;
    private List<LeagueInSeason> leagues;
    private boolean active;
    private boolean permanentlyClosed; //closed by admin and cannot open again

    public Team(String name, PersonalPage page, List<User> teamOwners, List<Player> players, List<Coach> coaches, Field field) {
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
        this.leagues = new LinkedList<>();
    }

    public void addLeague(LeagueInSeason league) {
        if(!leagues.contains(league)){
            this.leagues.add(league);
            league.addATeam(this);
        }
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
        for(User user:teamOwners){
            user.addTeam(this);
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

    public boolean addTeamOwner(User teamOwner) {
        if(!teamOwners.contains(teamOwner)) {
            this.teamOwners.add(teamOwner);
            teamOwner.addTeam(this);
            return true;
        }
        return false;
    }

    public boolean addTeamManager(User teamManager) {
        if(!teamManagers.contains(teamManager)) {
            this.teamManagers.add(teamManager);
            teamManager.addTeam(this);
            return true;
        }
        return false;
    }

    public boolean addPlayer(Player player) {
        if(!players.contains(player)) {
            this.players.add(player);
            player.addTeam(this);
            return true;
        }
        return false;
    }

    public boolean addCoach(Coach coach) {
        if(!coaches.contains(coach)) {
            this.coaches.add(coach);
            coach.addTeam(this);
            return true;
        }
        return false;
    }

    public void addGame(Game game) {
        if(!games.contains(game))
        this.games.add(game);
    }



    /**remove**/

    public boolean removeTeamOwner(User teamOwner) {
        if(teamOwners.contains(teamOwner)) {
            this.teamOwners.remove(teamOwner);
            teamOwner.removeTeam(this);
            return true;
        }
        return false;
    }

    public boolean removeTeamManager(User teamManager) {
        if(teamManagers.contains(teamManager)) {
            this.teamManagers.remove(teamManager);
            teamManager.removeTeam(this);
            return true;
        }
        return false;
    }

    public boolean removePlayer(Player player) {
        if(players.contains(player) && players.size()>12) {
            this.players.remove(player);
            player.removeTeam(this);
            return true;
        }
        return false;
    }

    public boolean removeCoach(Coach coach) {
        if(coaches.contains(coach) && coaches.size()>1) {
            this.coaches.remove(coach);
            coach.removeTeam(this);
            return true;
        }
        return false;
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

    public List<User> getTeamOwners() {
        return teamOwners;
    }

    public List<User> getTeamManagers() {
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
