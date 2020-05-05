package Service;


import Domain.User;
import Logger.Logger;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Server {


    public static void main(String[] args)
    {



    }



    private volatile boolean stop;
    private ServerSocket welcomeSocket;
    private int maxUsers;

    public Server(int port, int maxUsers) {
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

        try
        {
            DataInputStream stream = new DataInputStream(clientSocket.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            String lineReceived = rd.readLine();
            System.out.println("Server Received Packet: " + lineReceived);

            String[] splitLine = lineReceived.replace("\n", "").split("\\|");
            String operation = splitLine[0];


            if (operation.equals("login"))
                handleLogin(splitLine, clientSocket);

            else if (operation.equals("logout"))
                handleLogout(splitLine, clientSocket);

            else if (operation.equals("register"))
                handleRegister(splitLine, clientSocket);

            else if (operation.equals("openTeam"))
                handleOpenTeam(splitLine, clientSocket);

            else if (operation.equals("registerTeam"))
                handleRegisterTeam(splitLine, clientSocket);

            else if (operation.equals("defineScorePolicy"))
                handleDefineScorePolicy(splitLine, clientSocket);

            else if (operation.equals("defineAssignmentPolicy"))
                handleDefineAssignmentPolicy(splitLine, clientSocket);

            else if (operation.equals("gameManagement"))
                handleGameManagement(splitLine, clientSocket);

            else
            {
                Logger.logError("Server: invalid operation received");
                System.out.println("Invalid operation received: " + operation);
            }
        }
        catch (Exception e)
        {
            Logger.logError("Server: Data reading");
            e.printStackTrace();
        }

    }

    // +++++++++++++++++++++++++++ Handle Functions +++++++++++++++++++++++++++

    private void handleGameManagement(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleDefineAssignmentPolicy(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleDefineScorePolicy(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleRegisterTeam(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleOpenTeam(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleRegister(String[] splitLine, Socket clientSocket)
    {
        GuestSystem guestSystem = new GuestSystem();
        User user = guestSystem.register(splitLine[1],splitLine[2],splitLine[3],splitLine[4],splitLine[5], splitLine[6]);
        String id="";
        if(user == null){
            sendLineToClient("|registration failed!", clientSocket);
            return;
        }
        id = user.getID();
        //return format - {userID, role1, role2,...}
        sendLineToClient(id + "|Fan", clientSocket);
    }

    private void handleLogout(String[] splitLine, Socket clientSocket)
    {

    }

    private void handleLogin(String[] splitLine, Socket clientSocket)
    {
        //return format - {userID, role1, role2,...}

    }


    // +++++++++++++++++++++++++++ Other Functions +++++++++++++++++++++++++++


    private void sendLineToClient(String stringToSend, Socket client)
    {
        try
        {
            DataOutputStream outStream = new DataOutputStream(client.getOutputStream());

            if (stringToSend.charAt(stringToSend.length()-1) != '\n')
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



    public void stop() {

        stop = true;
        Logger.logServer("Server Stopped");
    }



}
