package files.project.manager.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * połaczenie z klientami akceptacja socketow
 */
public class Connection extends Thread{
    private ServerSocket serverSocket;
    private String serverFolder ;

    public Connection(String serverFolder) {
        this.serverFolder = serverFolder;
    }

    //TODO: sprobowac zrobic static jak w hoscie config static final
    public void run()
    {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1342);
            serverSocket.setReuseAddress(true);
            while(true){
                Socket client = serverSocket.accept();
                System.out.println("nowy klient się połączył\n");
                ClientHandler clientSocket = new ClientHandler(client,serverFolder);
                new Thread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
