package files.project.manager.model;

import files.project.manager.common.Data;
import files.project.manager.common.ListOfFiles;
import files.project.manager.common.TypeData;
import files.project.manager.controller.ServerViewController;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * odbieranie otrzymanej wiadomosci
 */
public class ReceiveData extends Thread{

    private ObjectInputStream objectInputStream;
    public List<Data> listOfmes;
    public String nameOfUser;

    public ReceiveData(ObjectInputStream objectInputStream,List<Data> listOfmes) {
        this.objectInputStream = objectInputStream;
        this.listOfmes = listOfmes;
    }

    /**
     * usuwanie zawartosci i folderu
     * @param path
     * @throws IOException
     */
    void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);
    }
    /**
     * odbieranie roznych typow wiadomosci :WELCOME wiadomosci powitalnej ,FILE wiadomosci z plikiem ,LISTOFFILES - listy plikow w folderze uzytkownika
     * ,F_FOR_SOMEBODY - pliku dla kogosc
     */
    public void run()
    {
        Data objectToRead;
        try {
            while ((objectToRead = (Data) objectInputStream.readObject()) != null) {
                System.out.println("Otrzymano" + objectToRead.getTypeOfData());
                TypeData typeOfObject = objectToRead.getTypeOfData();
                switch (typeOfObject) {
                    case WELCOME:
                        nameOfUser = objectToRead.getUserName();
                        String userName = objectToRead.getUserName();
                        var listOfFilesFromUser = objectToRead.getListOfFiles();
                        ServerViewController.addUserToList(userName);
                        String path = Config.getPath() + "\\" + userName;
                        File f = new File(path);
                        boolean dirCreated = f.mkdir();
                        List<String> listOfFilesWeGot = new ArrayList<String>(Arrays.asList(ListOfFiles.getNamesInFolder(path)));
                        createObjectReqFiles.createMessWithDiff(listOfFilesWeGot,listOfFilesFromUser,listOfmes);
                        break;
                    case FILE:
                        //getpathtouserdir to mamy nazwe pliku
                        String pathToFile = Config.getPath() + "\\" + objectToRead.getUserName() + "\\" + objectToRead.getPathToUserDir();
                        File file = new File(pathToFile);
                        ByteTable.writeByteToFile(file, objectToRead.getFileInBytes());
                        break;
                    case LIST_OF_FILES:
                        List<String> listOfUserFiles = objectToRead.getListOfFiles();
                        break;
                    case F_FOR_SMBDY:
                        String pathToFileForS = Config.getPath() + "\\" + objectToRead.getUserName() + "\\" + objectToRead.getPathToUserDir();
                        File fileToS = new File(pathToFileForS);
                        ByteTable.writeByteToFile(fileToS, objectToRead.getFileInBytes());
                        System.out.println("chce wyslac plik "+ objectToRead.getPathToUserDir() + " do uzytkownika: "+objectToRead.getUserName());
                        //Data mess = new Data(TypeData.FILE,objectToRead.getPathToUserDir(),objectToRead.getUserName(),objectToRead.getFileInBytes(),null,null);
                        //listOfmes.add(mess);

                       for(HolderMess temp:ClientHandler.holderMesses)
                        {
                            if(temp.dataToReceive.nameOfUser.equals(objectToRead.getUserName()))
                            {
                                //Data mess = new Data(TypeData.DEBUG)
                                System.out.println("############");
                                System.err.println(temp.dataToReceive.nameOfUser + "z holdmess nazwa uztkownika");
                                System.err.println(objectToRead.getPathToUserDir() +  " nazwa pliku ktory doistajemy ");
                                System.out.println("############");
                                Data mess = new Data(TypeData.FILE,objectToRead.getPathToUserDir(),objectToRead.getUserName(),objectToRead.getFileInBytes(),null,null);
                                temp.dataToSend.listOfmes.add(mess);
                                System.err.println("udalo sie wejsc do ifa znalezc uzytkownika i wyslac");
                                break;


                            }
                        }
                        break;
                    case BYE:
                        String pathToDelete = Config.getPath() + "\\" + objectToRead.getUserName();
                        Path Path = Paths.get(pathToDelete);
                        deleteDirectoryRecursion(Path);
                        ServerViewController.deleteUserInList(objectToRead.getUserName());

                }

                Thread.sleep(1000);
            }
             }catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


