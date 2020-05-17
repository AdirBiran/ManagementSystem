package Domain;

import Data.Database;

import java.util.List;
import java.util.LinkedList;
import java.util.Observable;

public class Team extends Observable {

    private String id;
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private PersonalPage page;
    private List<User> teamOwners; // at least one
    private List<User> teamManagers;
    private List<User> players; // at least 11
    private List<User> coaches; // at least one
    private Budget budget;
    private List<Game> games;
    private List<Field> fields;
    private List<LeagueInSeason> leagues;
    private boolean active;
    private boolean permanentlyClosed; //closed by admin and cannot open again

    public Team(String name, List<User> teamOwners, List<User> players, List<User> coaches, Field field) {
        this.id = "T"+IdGenerator.getNewId();
        this.name = name;
        if(teamOwners==null||teamOwners.size()<1)
           throw new RuntimeException("not enough TeamOwners");
        this.teamOwners = new LinkedList<>();
        linkTeamOwner(teamOwners);

        this.page = new PersonalPage("Team "+name+"'s page!", teamOwners.get(0));
        teamOwners.get(0).addRole(new HasPage(this.page));

        if(players==null||players.size()<11)
            throw new RuntimeException("not enough Players");
        this.players = players;
        linkPlayers();
        if(coaches==null||coaches.size()<1)
            throw new RuntimeException("not enough Coaches");
        this.coaches = coaches;
        linkCoaches();
        this.budget = new Budget();
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

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    public boolean addLeague(LeagueInSeason league) {
        if(!leagues.contains(league)){
            this.leagues.add(league);
            league.addATeam(this);
            return true;
        }
        return false;
    }

    private void linkField() {
        for(Field field :fields){
            field.addTeam(this);
        }

    }

    private void linkCoaches() {
        for(User user :coaches){
            //check if has coach add add team
            Coach coach = (Coach) user.checkUserRole("Coach");
            if(coach==null)
                throw new RuntimeException("unauthorized coach");
            else
                coach.addTeam(this);
        }
    }

    private void linkPlayers() {
        for(User user :players){
            Player player = (Player) user.checkUserRole("Player");
            if(player==null)
                throw new RuntimeException("unauthorized player");
            else
                player.addTeam(this);
        }
    }

    private void linkTeamOwner(List<User> userList) {
        for(User user : userList){
            if(user!= null)
                addTeamOwner(user);
        }
    }

    @Override
    public String toString() {
        return "Team" +
                ", id=" + id +
                ": name=" + name;
    }

    public String AllDetailsAboutTeam(){
        return "Team Owners: " + printTeamOwners() +
                "Players: " + printPlayers() +
                "Coaches: " +printCoaches() +
                "Games: " +printGames();
    }

    private String printGames() {
        String printNames = "";
        for(Game game : games)
            printNames= printNames + game.getName() +",";
        return printNames;
    }

    private String printCoaches() {
        String printNames = "";
        for(User user : coaches)
            printNames= printNames + user.getName() +",";
        return printNames;
    }

    private String printPlayers() {
        String printNames = "";
        for(User user : players)
            printNames= printNames + user.getName() +",";
        return printNames;
    }

    private String printTeamOwners() {
        String printNames = "";
        for(User user : teamOwners)
            printNames= printNames + user.getName() +",";
        return printNames;
    }

    public void addAWin() {
        this.wins++;
    }

    public void addALoss() {
        this.losses++;
    }

    public void addADraw() {
        this.draws++;
    }

    public boolean addTeamOwner(User user) {
        if(!teamOwners.contains(user)) {
            this.teamOwners.add(user);
            TeamOwner teamOwner = (TeamOwner) user.checkUserRole("TeamOwner");
            if(teamOwner!=null){
                teamOwner.addTeam(this);
            }
            else{
                teamOwner = new TeamOwner(user);
                teamOwner.addTeam(this);
                user.addRole(teamOwner);
            }
            this.addObserver(teamOwner);
            return true;
        }
        return false;
    }

    public boolean addTeamManager(User user, double price,boolean manageAssets , boolean finance) {
        if(!teamManagers.contains(user)) {
            this.teamManagers.add(user);
            TeamManager teamManager = (TeamManager)user.checkUserRole("TeamManager");
            if(teamManager!=null){
                teamManager.addTeam(this);
            }
            else{
                teamManager = new TeamManager(user, price, manageAssets, finance);
                teamManager.addTeam(this);
                user.addRole(teamManager);
            }
            this.addObserver(teamManager);
            return true;
        }
        return false;
    }

    public boolean addPlayer(User user) {
        if(!players.contains(user)) {
            Player player = (Player)user.checkUserRole("Player");
            if(player!=null){
                for(Team playersTeams: player.getTeams()){
                    if(doListsHaveLeaguesInCommon(this.leagues,playersTeams.leagues))
                        return false;
                }
                this.players.add(user);
                player.addTeam(this);
                return true;
            }
        }
        return false;
    }

    private boolean doListsHaveLeaguesInCommon(List<LeagueInSeason> list1, List<LeagueInSeason> list2){
        for(LeagueInSeason league1:list1){
            for(LeagueInSeason league2: list2){
                if(league1.equals(league2))
                    return true;
            }
        }
        return false;
    }

    public boolean addCoach(User user) {
        if(!coaches.contains(user)) {
            Coach coach = (Coach) user.checkUserRole("Coach");
            if(coach!=null){
                this.coaches.add(user);
                coach.addTeam(this);
                return true;
            }
        }
        return false;
    }

    public boolean addGame(Game game) {
        if(!games.contains(game)){
            this.games.add(game);
            return true;
        }
        return false;
    }

    /**remove**/
    public boolean removeTeamOwner(User teamOwner) {
        if(teamOwners.contains(teamOwner) && teamOwners.size()>1) {
            teamOwners.remove(teamOwner);
            TeamOwner teamOwnerRole = (TeamOwner) teamOwner.checkUserRole("TeamOwner");
            teamOwnerRole.removeTeam(this);
            teamOwnerRole.update(this, "Your subscription has been removed from the team "+this.name);
            return true;
        }
        return false;
    }

    public boolean removeTeamManager(User teamManager) {
        if(teamManagers.contains(teamManager)) {
            teamManagers.remove(teamManager);
            TeamManager teamManagerRole = (TeamManager) teamManager.checkUserRole("TeamManager");
            teamManagerRole.removeTeam(this);
            teamManagerRole.update(this, "Your subscription has been removed from the team "+this.name);
            return true;
        }
        return false;
    }

    public boolean removePlayer(User player) {
        if(players.contains(player) && players.size()>11) {
            Role role = player.checkUserRole("Player");
            ((Player)role).removeTeam(this);
            this.players.remove(player);
            return true;
        }
        return false;
    }

    public boolean removeCoach(User coach) {
        if(coaches.contains(coach) && coaches.size()>1) {
            Role role = coach.checkUserRole("Coach");
            ((Coach)role).removeTeam(this);
            this.coaches.remove(coach);
            return true;
        }
        return false;
    }

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

    public List<User> getPlayers() {
        return players;
    }

    public List<User> getCoaches() {
        return coaches;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Field> getFields() {
        return fields;
    }

    /**GET ONE FIELD*/
    public Field getField() {
        return fields.get(0);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if(active){
            setChanged();
            notifyObservers(this.name + " is open");
            updateAllSystemAdmins("team "+this.name+" open");
        }
        else {
            setChanged();
            notifyObservers(this.name + " is closed");
            updateAllSystemAdmins("team "+this.name+" closed");
        }
    }

    private void updateAllSystemAdmins(String news) {
        for(User admin : Database.getSystemAdmins()){
            Admin adminRole = (Admin)admin.checkUserRole("Admin");
            if(adminRole instanceof Admin){
                adminRole.update(this, news);
            }
        }
    }

    public boolean isPermanentlyClosed() {
        return permanentlyClosed;
    }

    public void setPermanentlyClosed(boolean permanentlyClosed) {
        this.permanentlyClosed = permanentlyClosed;
        if(permanentlyClosed) {
            setChanged();
            notifyObservers(this.name + " is permanently closed");
            updateAllSystemAdmins(this.name + " is permanently closed");
        }

    }

    public String getID() {
        return id;
    }


    public double getPrice() {
        return 0;
    }

    public boolean addField(Field field) {
        if(!fields.contains(field)) {
            fields.add(field);
            return true;
        }
        return false;
    }

    public boolean removeField(Field field) {
        if(fields.size()>1) {
            fields.remove(field);
            return true;
        }
        return false;
    }
}
