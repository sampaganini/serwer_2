package files.project.manager.model;

import files.project.manager.common.Data;
import files.project.manager.common.TypeData;
import files.project.manager.controller.ServerViewController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * wysyłanie wiadomosci
 */

public class DataSending extends Thread {
    /**
     * branie wiadomosci z listy ktora znajduje sie w datasending i receive i wysylanie jej
     * do odpowiednich uzytkownikow
     */

    private ObjectOutputStream objectOutputStream;
    public List<Data> listOfmes;

    public DataSending(ObjectOutputStream objectOutputStream,List<Data>listOfmes) {
        this.objectOutputStream = objectOutputStream;
        this.listOfmes = listOfmes;
    }
    public void run()
    {

        while(true)
        {
            if(listOfmes.size() == 0)
            {

                    List<String> lista = new ArrayList<String>();
                    List<String> listServer = ServerViewController.getListOfUsers();
                    for (String temp : listServer) {
                        lista.add(temp);
                    }
                    Data listOfUsers = new Data(TypeData.LIST_OF_USERS, " ", " ", null, lista, null);


                try {
                    objectOutputStream.writeObject(listOfUsers);
                    objectOutputStream.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Data messToSend = listOfmes.get(0);
                System.out.println(messToSend.getListOfFiles());
                try {
                    objectOutputStream.writeObject(messToSend);
                    objectOutputStream.flush();
                    listOfmes.remove(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
