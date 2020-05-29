package Data;

import Domain.*;
import Service.FootballManagementSystem;

import java.util.LinkedList;
import java.util.List;

public class initTest {

    Admin admin;
    Coach coach;
    Complaint complaint;
    Event event;
    Fan fan;
    Field field;
    Game game;
    Guest guest;
    League league;
    LeagueInSeason leagueInSeason;
    Player player;
    Referee referee;
    Season season;
    Team team;
    TeamManager teamManager;
    TeamOwner teamOwner;
    UnionRepresentative unionRepresentative;
    User user;
    FootballManagementSystem system;

    List<Admin> admins =new LinkedList<>();
    List<Coach> coaches=new LinkedList<>();
    List<Complaint> complaints=new LinkedList<>();
    List<Event> events=new LinkedList<>();
    List<Fan> fans=new LinkedList<>();
    List<Field> fields=new LinkedList<>();
    List<Game> games=new LinkedList<>();
    List<Guest> guests=new LinkedList<>();
    List<League> leagues=new LinkedList<>();
    List<LeagueInSeason> leagueInSeasons=new LinkedList<>();
    List<Player> players=new LinkedList<>();
    List<Referee> referees=new LinkedList<>();
    List<Season> seasons=new LinkedList<>();
    List<Team> teams=new LinkedList<>();
    List<TeamManager> teamManagers=new LinkedList<>();
    List<TeamOwner> teamOwners=new LinkedList<>();
    List<UnionRepresentative> unionRepresentatives=new LinkedList<>();
    List<User> users=new LinkedList<>();


    public initTest(boolean initSystem) {
        system = new FootballManagementSystem();
        if(initSystem) {
            system.systemInit(true);
            String leagueInSeasonId = system.dataReboot();
            leagueInSeason = Database.getLeagueInSeason(leagueInSeasonId);
        }else{
            leagueInSeason =system.getDatabase().getAllLeaguesInSeasons().get(0);
        }

        season = leagueInSeason.getSeason();
        league = leagueInSeason.getLeague();
        //games =leagueInSeason.getGamesId();
        //game =leagueInSeason.getGamesId().get(0);
        teams = leagueInSeason.getTeams();
        team = leagueInSeason.getTeams().get(0);
        referees = leagueInSeason.getReferees();
        referee = leagueInSeason.getReferees().get(0);
        users = Database.getAllUsers();
        if(users.size() != 0){
            user = users.get(0);
        }else{
            User newUser = new User("test","test","U","test@test.com");
            Database.addUser("Aa123",newUser);
            user=newUser;
        }

        players = Database.getAllPlayers();
        if(players.size() != 0) {
            player = players.get(0);
        }else{
            Player newPlayer = new Player(user.getID(),Database.getCurrentDate() ,Player.RolePlayer.goalkeeper ,100);
            Database.addPlayer(newPlayer);
            player=newPlayer;
        }

        admins = Database.getAllAdmins();
        if(admins.size() !=0 ) {
            admin = admins.get(0);
        }else{
            Admin newAdmin = new Admin(user.getID());
            Database.addAdmin(newAdmin);
            admin=newAdmin;
        }

        coaches = Database.getAllCoaches();
        if(coaches.size() != 0) {
            coach = coaches.get(0);
        }else{
            Coach newCoach = new Coach(user.getID(),Coach.TrainingCoach.UEFA_B,
                    Coach.RoleCoach.main,100);
            Database.addCoach(newCoach);
            coach=newCoach;
        }

        fans = Database.getAllFans();
        if(fans.size() != 0) {
            fan = fans.get(0);
        }else{
            Fan newFan = new Fan(user.getID(),"0502055454","AA");
            Database.addFan(newFan);
            fan=newFan;
        }

        complaints =Database.getAllComplaints();
        if(complaints.size() != 0){
            complaint =complaints.get(0);
        }else{
            Complaint newComplaint = new Complaint("test",fan);
            Database.addComplaint(newComplaint);
            complaint = newComplaint;
        }

        fields = Database.getAllFields();
        if(fields.size() != 0) {
            field = fields.get(0);
        }else{
            Field newField = new Field("AA","AA",100,100);
            Database.addField(newField);
            field=newField;
        }

        teamManagers = Database.getAllTeamManagers();
        if(teamManagers.size() != 0) {
            teamManager = teamManagers.get(0);
        }else{
            TeamManager newTeamManager = new TeamManager(user.getID(),100,true,true);
            Database.addTeamManager(newTeamManager);
            teamManager=newTeamManager;
        }

        teamOwners = Database.getAllTeamOwners();
        if(teamOwners.size() != 0) {
            teamOwner = teamOwners.get(0);
        }else{
            TeamOwner newTeamOwner = new TeamOwner(user.getID());
            Database.addTeamOwner(newTeamOwner);
            teamOwner=newTeamOwner;
        }

        unionRepresentatives = Database.getAllUnions();
        if(unionRepresentatives.size() != 0) {
            unionRepresentative = unionRepresentatives.get(0);
        }else{
            UnionRepresentative newUnion = new UnionRepresentative(user.getID());
            //Database.addU(newUnion);
            unionRepresentative=newUnion;
        }

        event = new Event(Event.EventType.Foul,game,"test");
        events.add(event);

        guest = new Guest();
        guests.add(guest);

    }

    public Admin getAdmin() {
        return admin;
    }

    public Coach getCoach() {
        return coach;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public Event getEvent() {
        return event;
    }

    public Fan getFan() {
        return fan;
    }

    public Field getField() {
        return field;
    }

    public Game getGame() {
        return game;
    }

    public Guest getGuest() {
        return guest;
    }

    public League getLeague() {
        return league;
    }

    public LeagueInSeason getLeagueInSeason() {
        return leagueInSeason;
    }

    public Player getPlayer() {
        return player;
    }

    public Referee getReferee() {
        return referee;
    }

    public Season getSeason() {
        return season;
    }

    public Team getTeam() {
        return team;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    public UnionRepresentative getUnionRepresentative() {
        return unionRepresentative;
    }

    public User getUser() {
        return user;
    }

    public FootballManagementSystem getSystem() {
        return system;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public List<LeagueInSeason> getLeagueInSeasons() {
        return leagueInSeasons;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<TeamManager> getTeamManagers() {
        return teamManagers;
    }

    public List<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public List<UnionRepresentative> getUnionRepresentatives() {
        return unionRepresentatives;
    }

    public List<User> getUsers() {
        return users;
    }
}
