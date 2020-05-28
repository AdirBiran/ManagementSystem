package Presentation;

import java.net.Socket;

public class NotificationsListener implements Runnable
{

    private Socket socket;
    private Client client;
    private final int secondsInterval = 2;

    public NotificationsListener(Socket socket, Client client)
    {
        this.socket = socket;
        this.client = client;

    }

    public void run()
    {
        while (true)
        {
            if (!client.getUserID().equals(""))
                client.askForNotifications();


            try
            {
                Thread.sleep(secondsInterval * 1000);
            }
            catch (Exception e)
            {

            }
        }
    }



}
