package Presentation;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.LinkedList;


public class Client  {

    private int serverPort;
    private Socket socket = null;

    public Client (int serverPort)
    {
        this.serverPort = serverPort;

        try
        {
            this.socket = new Socket("localhost", serverPort);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<String> sendToServer(String stringToSend)
    {
        List<String> results = new LinkedList<>();
        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            if (stringToSend.charAt(stringToSend.length()-1) != '\n')
                stringToSend = stringToSend + "\n";

            outputStream.writeBytes(stringToSend);
            outputStream.flush();
            results.add(receiveFromServer());

        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return results;
    }

    public String receiveFromServer()
    {
        String res = "-1";

        try
        {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inputStream));

            res = clientReader.readLine();
            System.out.println("Client receive from server : "+res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return res;

    }

}

