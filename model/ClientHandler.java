package files.project.manager.model;


import files.project.manager.common.Data;
import files.project.manager.common.ListOfFiles;
import files.project.manager.common.TypeData;
import files.project.manager.controller.ServerViewController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * obsługa klienta akceptowanie socketow startowanie data sending
 * and receiving oraz inicjalizacja uchwytu na te wiadomosci
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final String pathToServer;
    public  List<Data> fifo;
    public static List<HolderMess> holderMesses = Collections.synchronizedList(new ArrayList<HolderMess>());

    /**
     *
     * @param socket socket klienta
     * @param pathToServer , sciezka serwera
     */

    public ClientHandler(Socket socket, String pathToServer) {
        this.clientSocket = socket;
        this.pathToServer = pathToServer;
        this.fifo = Collections.synchronizedList(new ArrayList<Data>());
    }

    @Override
    public void run() {

        try {


                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ReceiveData receiveData = new ReceiveData(objectInputStream,fifo);
                receiveData.start();
                DataSending dataSend = new DataSending(objectOutputStream,fifo);
                dataSend.start();
                HolderMess elementToHold = new HolderMess(dataSend,receiveData);
                holderMesses.add(elementToHold);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}