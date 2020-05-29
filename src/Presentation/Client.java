package Presentation;


import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


public class Client  {

    private Socket socket;
    private final int notificationsIterval = 2000;
    private Timer notificationsTimer;

    public Client (int serverPort)
    {

        try
        {
            //socket = new Socket("132.72.65.47", serverPort);
            socket = new Socket("localhost", serverPort);
            notificationsTimer = new Timer();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void startNotifications(String uid)
    {
        notificationsTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                checkNotifications(uid);

            }
        }, notificationsIterval, notificationsIterval);
    }

    public void stopNotifications()
    {
        notificationsTimer.cancel();
    }

	

    public List<String> sendToServer(String stringToSend)
    {
        List<String> res = new LinkedList<>();

        try {

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Client sent to server " + stringToSend.replace("\n", ""));

            if (stringToSend.length() == 0 || stringToSend.charAt(stringToSend.length()-1) != '\n')
                stringToSend = stringToSend + "\n";

            outputStream.writeBytes(stringToSend);
            outputStream.flush();

            res = createListFromServerString(receiveFromServer());

        }
        catch (SocketException se)
        {
            showNotification("server was terminated ... :(");
            // liat
            // need to throw here alert that server was terminated
            // now throws error when trying to login when server closes when user is active
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        
        return res;
    }

    public String receiveFromServer()
    {
        String res = "-1";

        try
        {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inputStream));

            res = clientReader.readLine();

            String operator = res.split("\\|")[0];

            if (operator.equals("Notification"))
                System.out.println("Notification: " +  res.split("\\|")[1] + "\n");

            else
                System.out.println("Client receive from server : " + res + "\n");


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return res;

    }

    private List<String> createListFromServerString(String ans)
    {
        List<String> res = new LinkedList<>();

        String[] split = ans.split("~");

        for (String s : split)
            res.add(s);

        return res;
    }

    public String ListToString(List<String> list)
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


    public List<String> checkNotifications(String clientID)
    {

        List<String> notifications = sendToServer("checkNotifications|"+clientID);
        return notifications;
    }

    private void showNotification(String notification){
        GeneralController gc = new GeneralController();
        gc.showAlert(notification, Alert.AlertType.CONFIRMATION);
        // liat
        // need to show notification on alert
        // can't use Alert here, not FX application error

    }
}

