package Service;


import Data.Database;
import Domain.*;
import Logger.Logger;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Server {

    private UserSystem userSystem;
    private GuestSystem guestSystem;
    private AdminSystem adminSystem;
    private TeamManagementSystem teamSystem;
    private NotificationSystem notificationSystem;
    private PersonalPageSystem personalPageSystem;
    private FinanceTransactionsSystem financeTransactionsSystem;

    private HashSet<String> loggedUsers;


    public static void main(String[] args)
    {


    }



    private volatile boolean stop;
    private ServerSocket welcomeSocket;
    private int maxUsers;

    public Server(int port, int maxUsers) {

        userSystem = new UserSystem();
        guestSystem = new GuestSystem();
        notificationSystem = new NotificationSystem();
        adminSystem = new AdminSystem(notificationSystem);
        teamSystem = new TeamManagementSystem(notificationSystem);
        personalPageSystem = new PersonalPageSystem();
        financeTransactionsSystem = new FinanceTransactionsSystem(notificationSystem);

        loggedUsers = new HashSet<>();

        this.maxUsers = maxUsers;

        welcomeSocket = null;
        try
        {
            Logger.logServer("Server created at port " + port + ", Maximum users: " + maxUsers);
            welcomeSocket = new ServerSocket(port);
        }
        catch (Exception e)
        {
            Logger.logError("Server Fail: can't create server");
            e.printStackTrace();
        }
        this.stop = false;
    }

    public void start() {


        new Thread(() -> {
            try {
                runServer();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();

    }

    private void runServer() {

        ThreadPoolExecutor exec = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        exec.setCorePoolSize(maxUsers);
        exec.setMaximumPoolSize(maxUsers);


        while (!stop) {
            try {
                Socket clientSocket = welcomeSocket.accept();

                String ip = clientSocket.getInetAddress().toString();
                Logger.logServer("Client  " + ip + "  connected");

                exec.execute(() -> {
                    handleClient(clientSocket);

                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        exec.shutdown();

        try {
            exec.awaitTermination(1, TimeUnit.HOURS);
        }
        catch (Exception e)
        {
            Logger.logError("Server timeout");
            e.printStackTrace();
        }

    }

    private void handleClient(Socket clientSocket)
    {

        while (clientSocket.isConnected())
        {
            try
            {
                DataInputStream stream = new DataInputStream(clientSocket.getInputStream());
                BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
                String lineReceived = rd.readLine();

                System.out.println("Server Received Packet: " + lineReceived);

                String[] splitLine = lineReceived.replace("\n", "").split("\\|");
                String operation = splitLine[0];


                switch (operation)
                {

                    // ------------------- GUEST -------------------
                    case "logIn": // Done
                        handleLogin(splitLine, clientSocket);

                        break;

                    case "search": // Done
                        handleSearch(splitLine, clientSocket);

                        break;

                    case "register": // Done
                        handleRegister(splitLine, clientSocket);

                        break;


                    case "viewInformationAboutTeams": // Done
                        handleviewInformationAboutTeams(clientSocket);
                        break;

                    case "viewInformationAboutPlayers": // Done
                        handleviewInformationAboutPlayers(clientSocket);
                        break;

                    case "viewInformationAboutCoaches": // Done
                        handleviewInformationAboutCoaches(clientSocket);
                        break;

                    case "viewInformationAboutLeagues": // Done
                        handleviewInformationAboutLeagues(clientSocket);
                        break;

                    case "viewInformationAboutSeasons": // Done
                        handleviewInformationAboutSeasons(clientSocket);
                        break;

                    case "viewInformationAboutReferees": // Done
                        handleviewInformationAboutReferees(clientSocket);
                        break;

                    // ------------------- USER -------------------

                    case "logOut": // Done
                        handleLogout(splitLine, clientSocket);
                        break;

                    case "viewSearchHistory": // Done
                        handleviewSearchHistory(splitLine, clientSocket);
                        break;

                    case "addComplaint": // Done
                        handleaddComplaint(splitLine, clientSocket);
                        break;

                    case "getFanPages": // Done
                        handlegetFanPages(splitLine, clientSocket);
                        break;

                    case "viewPersonalDetails": // Done
                        handleviewPersonalDetails(splitLine, clientSocket);
                        break;

                    case "editPersonalInfo": // Done
                        handleeditPersonalInfo(splitLine, clientSocket);
                        break;

                    case "editFanPersonalDetails": // Done
                        handleeditFanPersonalDetails(splitLine, clientSocket);
                        break;

                    // ------------------- ADMIN -------------------

                    case "removeUser":
                        handleremoveUser(splitLine, clientSocket);
                        break;

                    case "addNewPlayer": // Done
                        handleaddNewPlayer(splitLine, clientSocket);

                        break;

                    case "addNewCoach": // Done
                        handleaddNewCoach(splitLine, clientSocket);
                        break;

                    case "addNewTeamOwner": // Done
                        handleaddNewTeamOwner(splitLine, clientSocket);

                        break;

                    case "addNewTeamManager": // Done
                        handleaddNewTeamManager(splitLine, clientSocket);

                        break;

                    case "addNewUnionRepresentative": // Done
                        handleaddNewUnionRepresentative(splitLine, clientSocket);

                        break;

                    case "addNewAdmin": // Done
                        handleaddNewAdmin(splitLine, clientSocket);

                        break;

                    case "permanentlyCloseTeam": // Done
                        handlepermanentlyCloseTeam(splitLine, clientSocket);

                        break;

                    case "responseToComplaint":
                        handleresponseToComplaint(splitLine, clientSocket);

                        break;

                    case "viewLog": // Done
                        handleviewLog(splitLine, clientSocket);

                        break;

                    case "trainModel": // Done
                        handletrainModel(splitLine, clientSocket);

                        break;

                    // ------------------- TEAM MANAGEMENT -------------------


                    case "addAssetPlayer": // Done
                        handleaddAssetPlayer(splitLine, clientSocket);
                        break;

                    case "addAssetCoach": // Done
                        handleaddAssetCoach(splitLine, clientSocket);
                        break;

                    case "addAssetTeamManager": // Done
                        handleaddAssetTeamManager(splitLine, clientSocket);
                        break;

                    case "addField": // Done
                        handleaddField(splitLine, clientSocket);
                        break;

                    case "removeField": // Done
                        handleremoveField(splitLine, clientSocket);
                        break;

                    case "removeAssetPlayer": // Done
                        handleremoveAssetPlayer(splitLine, clientSocket);
                        break;

                    case "removeAssetCoach": // Done
                        handleremoveAssetCoach(splitLine, clientSocket);
                        break;

                    case "removeAssetTeamManager": // Done
                        handleremoveAssetTeamManager(splitLine, clientSocket);
                        break;

                    case "updateAsset": // Done
                        handleupdateAsset(splitLine, clientSocket);
                        break;

                    case "createTeam":
                        handlecreateTeam(splitLine, clientSocket);
                        break;

                    case "appointmentTeamOwner": // Done
                        handleappointmentTeamOwner(splitLine, clientSocket);
                        break;

                    case "appointmentTeamManager": // Done
                        handleappointmentTeamManager(splitLine, clientSocket);
                        break;

                    case "removeAppointmentTeamOwner": // Done
                        handleremoveAppointmentTeamOwner(splitLine, clientSocket);
                        break;

                    case "removeAppointmentTeamManager": // Done
                        handleremoveAppointmentTeamManager(splitLine, clientSocket);
                        break;

                    case "closeTeam": // Done
                        handlecloseTeam(splitLine, clientSocket);
                        break;

                    case "reOpeningTeam": // Done
                        handlereOpeningTeam(splitLine, clientSocket);
                        break;


                    // ------------------- Personal Page -------------------

                    case "uploadToPage": // Done
                        handleuploadToPage(splitLine, clientSocket);
                        break;

                    // ------------------- Finance Transactions -------------------

                    case "reportNewIncome": // Done
                        handlereportNewIncome(splitLine, clientSocket);
                        break;

                    case "reportNewExpanse": // Done
                        handlereportNewExpanse(splitLine, clientSocket);
                        break;

                    case "getBalance": // Done
                        handlegetBalance(splitLine, clientSocket);

                        break;


                    // ------------------- Default -------------------

                    default:
                        Logger.logError("Server: invalid operation received");
                        //Liat added this line to test presentation stuff
                        sendLineToClient("", clientSocket);
                        System.out.println("Invalid operation received: " + operation);
                        break;

                }


            }
            catch (Exception e)
            {
                Logger.logError("Server: Data reading");
                e.printStackTrace();
            }
        }



    }

    private void handlegetBalance(String[] splitLine, Socket clientSocket)
    {
        double budget = financeTransactionsSystem.getBalance(Database.getUser(splitLine[1]), Database.getTeam(splitLine[2]));
        sendLineToClient("" + budget, clientSocket);
    }

    private void handlereportNewExpanse(String[] splitLine, Socket clientSocket)
    {
        boolean success = financeTransactionsSystem.reportNewExpanse(Database.getUser(splitLine[1]), Database.getTeam(splitLine[2]), Double.parseDouble(splitLine[3]));

        if (success)
            sendLineToClient("Succeed reporting new expanse", clientSocket);
        else
            sendLineToClient("Failed reporting new expanse", clientSocket);
    }

    private void handlereportNewIncome(String[] splitLine, Socket clientSocket)
    {
        boolean success = financeTransactionsSystem.reportNewIncome(Database.getUser(splitLine[1]), Database.getTeam(splitLine[2]), Double.parseDouble(splitLine[3]));

        if (success)
            sendLineToClient("Succeed reporting new income", clientSocket);
        else
            sendLineToClient("Failed reporting new income", clientSocket);
    }

    private void handleuploadToPage(String[] splitLine, Socket clientSocket)
    {

        boolean success = personalPageSystem.uploadToPage(Database.getUser(splitLine[1]), splitLine[2]);

        if (success)
            sendLineToClient("Succeed uploading to personal page", clientSocket);
        else
            sendLineToClient("Failed uploading to personal page", clientSocket);
    }

    private void handleaddAssetPlayer(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.addAssetPlayer(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed adding a Player to team", clientSocket);
        else
            sendLineToClient("Failed adding a Player to team", clientSocket);
    }

    private void handleaddAssetCoach(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.addAssetCoach(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed adding a Coach to team", clientSocket);
        else
            sendLineToClient("Failed adding a Coach to team", clientSocket);
    }

    private void handleaddAssetTeamManager(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.addAssetTeamManager(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]), Double.parseDouble(splitLine[4]),stringToBoolean(splitLine[5]), stringToBoolean(splitLine[6]));

        if (success)
            sendLineToClient("Succeed adding a Team Manager to team", clientSocket);
        else
            sendLineToClient("Failed adding a Team Manager to team", clientSocket);
    }

    private void handleaddField(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.addField(Database.getUser(splitLine[1]), (Field)(Database.getAsset(splitLine[2])), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed adding a Field to team", clientSocket);
        else
            sendLineToClient("Failed adding a Field to team", clientSocket);
    }

    private void handleremoveField(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeField(Database.getUser(splitLine[1]), (Field)(Database.getAsset(splitLine[2])), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing a Field from team", clientSocket);
        else
            sendLineToClient("Failed removing a Field from team", clientSocket);
    }

    private void handleremoveAssetPlayer(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeAssetPlayer(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing a Player from team", clientSocket);
        else
            sendLineToClient("Failed removing a Player from team", clientSocket);
    }

    private void handleremoveAssetCoach(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeAssetCoach(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing a Coach from team", clientSocket);
        else
            sendLineToClient("Failed removing a Coach from team", clientSocket);
    }

    private void handleremoveAssetTeamManager(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeAssetTeamManager(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing a Team Manager from team", clientSocket);
        else
            sendLineToClient("Failed removing a Team Manager from team", clientSocket);
    }

    private void handleupdateAsset(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.updateAsset(Database.getUser(splitLine[1]), splitLine[2], splitLine[3], splitLine[4]);

        if (success)
            sendLineToClient("Succeed updating asset", clientSocket);
        else
            sendLineToClient("Failed updating asset", clientSocket);
    }

    private void handlecreateTeam(String[] splitLine, Socket clientSocket)
    {
    }

    private void handleappointmentTeamOwner(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.appointmentTeamOwner(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed appointing a Team Owner to team", clientSocket);
        else
            sendLineToClient("Failed appointing a Team Owner to team", clientSocket);
    }

    private void handleappointmentTeamManager(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.appointmentTeamManager(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]), Double.parseDouble(splitLine[4]), stringToBoolean(splitLine[5]), stringToBoolean(splitLine[6]));

        if (success)
            sendLineToClient("Succeed appointing a Team Manager to team", clientSocket);
        else
            sendLineToClient("Failed appointing a Team Manager to team", clientSocket);
    }

    private void handleremoveAppointmentTeamOwner(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeAppointmentTeamOwner(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing appointed Team Owner from team", clientSocket);
        else
            sendLineToClient("Failed removing appointed Team Owner from team", clientSocket);
    }

    private void handleremoveAppointmentTeamManager(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.removeAppointmentTeamManager(Database.getUser(splitLine[1]), Database.getUser(splitLine[2]), Database.getTeam(splitLine[3]));

        if (success)
            sendLineToClient("Succeed removing appointed Team Manager from team", clientSocket);
        else
            sendLineToClient("Failed removing appointed Team Manager from team", clientSocket);
    }

    private void handlecloseTeam(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.closeTeam(Database.getUser(splitLine[1]), Database.getTeam(splitLine[2]));

        if (success)
            sendLineToClient("Succeed closing team", clientSocket);
        else
            sendLineToClient("Failed closing team", clientSocket);
    }

    private void handlereOpeningTeam(String[] splitLine, Socket clientSocket)
    {
        boolean success = teamSystem.reOpeningTeam(Database.getUser(splitLine[1]), Database.getTeam(splitLine[2]));

        if (success)
            sendLineToClient("Succeed reopening team", clientSocket);
        else
            sendLineToClient("Failed reopening team", clientSocket);
    }

    private void handlegetFanPages(String[] splitLine, Socket clientSocket)
    {

        List<String> results = userSystem.getFanPages(splitLine[1]);
        String res = ListToString(results);

        sendLineToClient(res, clientSocket);
    }

    private void handleaddComplaint(String[] splitLine, Socket clientSocket)
    {
        boolean success = userSystem.addComplaint(splitLine[1], splitLine[2]);

        if (success)
            sendLineToClient("Succeed adding a complaint", clientSocket);
        else
            sendLineToClient("Failed adding a complaint", clientSocket);
    }

    private void handleeditPersonalInfo(String[] splitLine, Socket clientSocket)
    {
         userSystem.editPersonalInfo(splitLine[1], splitLine[2], splitLine[3]);

         sendLineToClient("Succeed Editing personal info", clientSocket);

    }

    private void handleeditFanPersonalDetails(String[] splitLine, Socket clientSocket)
    {

        boolean success = userSystem.editFanPersonalDetails(splitLine[1], splitLine[2], splitLine[3], splitLine[4], splitLine[5]);

        if (success)
            sendLineToClient("Succeed Editing fan personal info", clientSocket);
        else
            sendLineToClient("Failed Editing fan personal info", clientSocket);
    }

    private void handleviewSearchHistory(String[] splitLine, Socket clientSocket)
    {
        List<String> results = userSystem.viewSearchHistory(splitLine[1]);
        String res = ListToString(results);
        sendLineToClient(res, clientSocket);

    }

    private void handleremoveUser(String[] splitLine, Socket clientSocket)
    {

    }

    private void handletrainModel(String[] splitLine, Socket clientSocket)
    {
        boolean success = adminSystem.trainModel(splitLine[1]);

        if (success)
            sendLineToClient("Succeed activating the training model", clientSocket);
        else
            sendLineToClient("Failed activating the training model", clientSocket);


    }

    private void handleviewLog(String[] splitLine, Socket clientSocket)
    {

        List<String> res = adminSystem.viewLog(splitLine[1], splitLine[2]);
        String sendToClient = ListToString(res);

        sendLineToClient(sendToClient, clientSocket);
    }


    // +++++++++++++++++++++++++++ Handle Functions +++++++++++++++++++++++++++

    private void handleSearch(String[] splitLine, Socket clientSocket)
    {
        List<String> resultsList = guestSystem.search(splitLine[1]);

        String sendToClient = ListToString(resultsList);

        sendLineToClient(sendToClient, clientSocket);

    }

    private void handleviewPersonalDetails(String[] splitLine, Socket clientSocket)
    {
        String res = userSystem.viewPersonalDetails(splitLine[1]);
        sendLineToClient(res, clientSocket);
    }


    private void handleresponseToComplaint(String[] splitLine, Socket clientSocket)
    {


    }

    private void handlepermanentlyCloseTeam(String[] splitLine, Socket clientSocket)
    {
        Team team = Database.getTeam(splitLine[2]); /**/

        boolean success = adminSystem.permanentlyCloseTeam(splitLine[1], splitLine[2]);
        if (success)
            sendLineToClient("Succeed closing the team " + team.getName(), clientSocket);
        else
            sendLineToClient("Failed closing the team " + team.getName(), clientSocket);
    }

    private void handleaddNewAdmin(String[] splitLine, Socket clientSocket)
    {
        String addedAdminId = adminSystem.addNewAdmin(splitLine[1], splitLine[2], splitLine[3], splitLine[4], splitLine[5]);

        if (addedAdminId == null)
            sendLineToClient("Failed adding a new Admin", clientSocket);
        else
            sendLineToClient("Succeed adding a new Admin", clientSocket);
    }

    private void handleaddNewUnionRepresentative(String[] splitLine, Socket clientSocket)
    {
        String addedRepresentativeId = adminSystem.addNewUnionRepresentative(splitLine[1], splitLine[2], splitLine[3], splitLine[4]);

        if (addedRepresentativeId == null)
            sendLineToClient("Failed adding a new Union Representative", clientSocket);
        else
            sendLineToClient("Succeed adding a new Union Representative", clientSocket);
    }

    private void handleaddNewTeamManager(String[] splitLine, Socket clientSocket)
    {
        String addedTeamManagerId = adminSystem.addNewTeamManager(splitLine[1], splitLine[2], splitLine[3], splitLine[4], Double.parseDouble(splitLine[5]), stringToBoolean(splitLine[6]), stringToBoolean(splitLine[7]));

        if (addedTeamManagerId == null)
            sendLineToClient("Failed adding a new Team Manager", clientSocket);
        else
            sendLineToClient("Succeed adding a new Team Manager", clientSocket);
    }

    private void handleaddNewTeamOwner(String[] splitLine, Socket clientSocket)
    {
        String addedTeamOwnerId = adminSystem.addNewTeamOwner(splitLine[1], splitLine[2], splitLine[3], splitLine[4]);

        if (addedTeamOwnerId == null)
            sendLineToClient("Failed adding a new Team Owner", clientSocket);
        else
            sendLineToClient("Succeed adding a new Team Owner", clientSocket);
    }

    private void handleaddNewCoach(String[] splitLine, Socket clientSocket)
    {
        String addedCoachId = adminSystem.addNewCoach(splitLine[1], splitLine[2], splitLine[3], splitLine[4],splitLine[5] ,splitLine[6], Double.parseDouble(splitLine[7]));

        if (addedCoachId == null)
            sendLineToClient("Failed adding a new Coach", clientSocket);
        else
            sendLineToClient("Succeed adding a new Coach", clientSocket);
    }

    private void handleaddNewPlayer(String[] splitLine, Socket clientSocket)
    {
        String addedPlayerId = adminSystem.addNewPlayer(splitLine[1], splitLine[2], splitLine[3], splitLine[4], stringToDate(splitLine[5]), splitLine[6], Double.parseDouble(splitLine[7]));
        if (addedPlayerId == null)
            sendLineToClient("Failed adding a new Player", clientSocket);
        else
            sendLineToClient("Succeed adding a new Player", clientSocket);

    }


    private void handleRegister(String[] splitLine, Socket clientSocket)
    {
        String userId = guestSystem.register(splitLine[1],splitLine[2],splitLine[3],splitLine[4],splitLine[5], splitLine[6]);
        if(userId == null){
            sendLineToClient("Registration Failed!", clientSocket);
            return;
        }

        List<String> roles = userSystem.getUserRoles(userId); /**/

        if (!loggedUsers.contains(userId))
            loggedUsers.add(userId);

        String sendToClient = userId + "|";

        for (String r : roles)
            sendToClient = sendToClient + r + "|";

        sendLineToClient(sendToClient.substring(0,sendToClient.length()-1), clientSocket);
    }

    private void handleLogout(String[] splitLine, Socket clientSocket)
    {
        userSystem.logOut();

        if (loggedUsers.contains(splitLine[1]))
            loggedUsers.remove(splitLine[1]);

        sendLineToClient("Logout Successful", clientSocket);

    }

    private void handleLogin(String[] splitLine, Socket clientSocket)
    {
        String loggedUserId = guestSystem.logIn(splitLine[1], splitLine[2]);
        if (loggedUserId == null)
        {
            sendLineToClient("Login Failed", clientSocket);
        }
        else
        {
            List<String> roles = userSystem.getUserRoles(loggedUserId); /**/

            if (!loggedUsers.contains(loggedUserId))
                loggedUsers.add(loggedUserId);

            String sendToClient = loggedUserId + "|";

            for (String r : roles)
                sendToClient = sendToClient + r + "|";

            sendLineToClient(sendToClient.substring(0,sendToClient.length()-1), clientSocket);
        }


    }

    private void handleviewInformationAboutReferees(Socket clientSocket)
    {
        List<String> res = guestSystem.viewInformationAboutReferees();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);

    }

    private void handleviewInformationAboutSeasons(Socket clientSocket)
    {
        List<String> res = guestSystem.viewInformationAboutSeasons();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);
    }

    private void handleviewInformationAboutLeagues(Socket clientSocket)
    {
        List<String> res = guestSystem.viewInformationAboutLeagues();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);
    }

    private void handleviewInformationAboutCoaches(Socket clientSocket)
    {
        List<String> res = guestSystem.viewInformationAboutCoaches();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);
    }

    private void handleviewInformationAboutPlayers(Socket clientSocket)
    {
        List<String> res = guestSystem.viewInformationAboutPlayers();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);
    }

    private void handleviewInformationAboutTeams(Socket clientSocket)
    {
        System.out.println("Server handling teams request");
        List<String> res = guestSystem.viewInformationAboutTeams();
        String result = ListToString(res);
        sendLineToClient(result, clientSocket);
    }


    // +++++++++++++++++++++++++++ Other Functions +++++++++++++++++++++++++++


    private void sendLineToClient(String stringToSend, Socket client)
    {
        try
        {
            DataOutputStream outStream = new DataOutputStream(client.getOutputStream());

            if (stringToSend.length() == 0 || stringToSend.charAt(stringToSend.length()-1) != '\n')
                stringToSend = stringToSend + "\n";

            outStream.writeBytes(stringToSend);
            outStream.flush();

        }
        catch (Exception e)
        {
            Logger.logError("Sending " + stringToSend + " Failed");
            e.printStackTrace();
        }
    }

    private List<String> objListToStringList(List<Object> objList)
    {
        List<String> res = new LinkedList<>();

        for (Object ob : objList)
            res.add(ob.toString());

        return res;
    }

    private String ListToString(List<String> list)
    {

        String res = "";

        if (list.size() == 0)
            return res;

        for (String s : list)
            res = res + s + "~";

        res = res.substring(0, res.length()-1);
        res = res + "\n";

        return res;
    }

    private Date stringToDate(String s)
    {
        String[] split = s.split("-");
        Calendar cal = Calendar.getInstance();

        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]) - 1;
        int day = Integer.parseInt(split[2]);

        cal.set(year, month, day);
        Date date = new Date(cal.getTimeInMillis());
        return date;
    }

    private boolean stringToBoolean(String s)
    {
        if (s.toLowerCase().equals("true"))
            return true;

        return false;
    }



    public void stop() {

        stop = true;
        Logger.logServer("Server Stopped");
    }



}
