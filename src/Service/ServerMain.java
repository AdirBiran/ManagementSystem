package Service;

public class ServerMain {

    public static void main(String[] args)
    {
        Server server = new Server(7567, 4);
        server.firstInit();
        server.runServer();
        FootballManagementSystem.systemInit(false);
    }
}
