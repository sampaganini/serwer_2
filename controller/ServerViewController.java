package files.project.manager.controller;

import files.project.manager.common.ListOfFiles;
import files.project.manager.model.Config;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.ObjectInputFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ustawianie widoku serewera
 */

public class ServerViewController implements Initializable {


    public static int timeSleep = 1000;

   private static List<String> listOfUsers = Collections.synchronizedList(new ArrayList<String>());

    /**
     * dodawanie uzytkownika do listy uzytkownikow na serwerze
     * @param userName nazwa uzytkownika dodawanego
     */
   public static void addUserToList(String userName)
   {
       listOfUsers.add(userName);
   }

    /**
     * usuwanie uzytkownika z listy uzytkownikow na serwerze
     * @param userName
     */
    public static void deleteUserInList(String userName)
    {
        listOfUsers.remove(userName);
    }

    @FXML
    private  Label user1Label;

    @FXML
    private ListView<String> user1ListView;

    @FXML
    private  Label user2Label;

    @FXML
    private ListView<String> user2ListView;

    @FXML
    private  Label user3Label;

    @FXML
    private ListView<String> user3ListView;

    @FXML
    private  Label user4Label;

    @FXML
    private ListView<String> user4ListView;

    public static List<String> getListOfUsers() {
        return listOfUsers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user1Label.setText("Label");
        user2Label.setText("Label");
        user3Label.setText("Label");
        user4Label.setText("Label");
      Thread setUsers = new Thread(this::setUsersLabel);
      setUsers.start();
      Thread setFiles = new Thread(this::RefreshListOfFiles);
      setFiles.start();

    }

    /**
     * ustawianie labeli uzytkownikow
     */
    public void setUsersLabel()
    {

        while(true) {
            Platform.runLater(() -> {

                int countOfUsers = 0;
                for(String temp:listOfUsers)
                {
                    if(countOfUsers == 0)
                    {
                        user1Label.setText(temp);
                    }
                    if(countOfUsers == 1)
                    {
                        user2Label.setText(temp);
                    }
                    if(countOfUsers == 2)
                    {
                        user3Label.setText(temp);
                    }
                    if(countOfUsers == 3)
                    {
                        user4Label.setText(temp);
                    }
                    countOfUsers++;
                }
            });
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        }

    /**
     * odswiezanie listy plikow w folderze danego uzytkownika
     */
        public void RefreshListOfFiles()
        {
            while(true)
            {
                Platform.runLater(() -> {
                    if(!user1Label.getText().equals("Label") )
                    {
                        String pathOfUser = Config.getPath() + "\\" + user1Label.getText();

                        String[] listFiles= ListOfFiles.getNamesInFolder(pathOfUser);
                        user1ListView.getItems().clear();

                            for (String temp : listFiles) {
                                user1ListView.getItems().add(temp);
                            }
                    }
                    if(!user2Label.getText().equals("Label") )
                    {
                        String pathOfUser = Config.getPath() + "\\" + user2Label.getText();


                        String[] listFiles= ListOfFiles.getNamesInFolder(pathOfUser);
                        user2ListView.getItems().clear();

                        for (String temp : listFiles) {
                            user2ListView.getItems().add(temp);
                        }
                    }
                    if(!user3Label.getText().equals("Label") )
                    {
                        String pathOfUser = Config.getPath() + "\\" + user3Label.getText();

                        String[] listFiles= ListOfFiles.getNamesInFolder(pathOfUser);
                        user3ListView.getItems().clear();

                        for (String temp : listFiles) {
                            user3ListView.getItems().add(temp);
                        }
                    }
                    if(!user4Label.getText().equals("Label") )
                    {
                        String pathOfUser = Config.getPath() + "\\" + user4Label.getText();


                        String[] listFiles= ListOfFiles.getNamesInFolder(pathOfUser);
                        user4ListView.getItems().clear();

                        for (String temp : listFiles) {
                            user4ListView.getItems().add(temp);
                        }
                    }


                });
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


