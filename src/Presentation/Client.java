package Presentation;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.LinkedList;




public class Client  {

    private int serverPort;
    private Socket socket = null;
    private NotificationsListener notificationsListener;
    private Thread listener = null;
    private String userID = "";

    public Client (int serverPort)
    {
        this.serverPort = serverPort;

        try
        {
            this.socket = new Socket("localhost", serverPort);
            this.notificationsListener = new NotificationsListener(socket, this);
            this.listener = new Thread(notificationsListener);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public String getUserID()
    {
        return this.userID;
    }

    public void startGettingNotifications(String userID)
    {
        this.userID = userID;
        listener.start();
    }

    public void stopGettingNotifications()
    {
        this.userID = "";
        listener.interrupt();
    }
	

    public List<String> sendToServer(String stringToSend)
    {
        List<String> res = new LinkedList<>();


        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            if (stringToSend.length() == 0 || stringToSend.charAt(stringToSend.length()-1) != '\n')
                stringToSend = stringToSend + "\n";

            outputStream.writeBytes(stringToSend);
            outputStream.flush();

            System.out.println("Client sent to server " + stringToSend);

            res = createListFromServerString(receiveFromServer());

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
            //if (res.split("\\|")[0].equals("Notification"))
            //{
            //    String notification = res.split("\\|")[1];
            //    sendNotification(notification);
//
            //}
//
            //res = clientReader.readLine();
            System.out.println("Client receive from server : "+res);
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


    /**
     * use this function to show notification to user:
     */
    private void sendNotification(String notification){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,notification);
        alert.setHeaderText("System Notification!");
        alert.show();
	}

    public List<String> askForNotifications()
    {
        List<String> notifications = sendToServer("Notifications|"+getUserID());
        return notifications;

    }
}

