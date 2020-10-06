package files.project.manager.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * klasa do definiowania wiadomosci wysylanej przez serwer i klienta typu serializable
 */
public class Data implements Serializable {

    private TypeData typeOfData;
    private String pathToUserDir;
    private String userName;
    private byte[] fileInBytes;
    private List listOfUsers;
    public List listOfFiles;
    public Data(TypeData typeOfData, String pathToUserDir, String name, byte[] file,List listOfUser,List listOfFiles) {
        this.typeOfData = typeOfData;
        this.pathToUserDir = pathToUserDir;
        this.userName = name;
        this.fileInBytes = file;
        this.listOfUsers = listOfUser;
        this.listOfFiles = listOfFiles;
    }


    public byte[] getFileInBytes() {
        return fileInBytes;
    }

    public TypeData getTypeOfData() {
        return typeOfData;
    }

    public String getPathToUserDir() {
        return pathToUserDir;
    }

    public String getUserName() {
        return userName;
    }


    public List getListOfUsers() {
        return listOfUsers;
    }

    /**
     *
     * @return string z zapisem wnętrza wiadomosci
     */
    @Override
    public String toString() {
        return "Data{" +

                ", typeOfData=" + typeOfData +
                ", pathToUserDir='" + pathToUserDir + '\'' +
                ", userName='" + userName + '\'' +
                ", fileInBytes=" + Arrays.toString(fileInBytes) +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    public void setListOfFiles(List listOfFiles) {
        this.listOfFiles = listOfFiles;
    }

    public List getListOfFiles() {
        return listOfFiles;
    }
}
