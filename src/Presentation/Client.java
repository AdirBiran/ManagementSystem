package Presentation;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;



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
        List<String> res = new LinkedList<>();


        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            if (stringToSend.length() == 0 || stringToSend.charAt(stringToSend.length()-1) != '\n')
                stringToSend = stringToSend + "\n";

            outputStream.writeBytes(stringToSend);
            outputStream.flush();

            System.out.println("Client sent to server " + stringToSend);

            results = createListFromServerString(receiveFromServer());

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


}

