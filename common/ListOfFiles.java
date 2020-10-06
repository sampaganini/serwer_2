package files.project.manager.common;

import java.io.File;
import java.io.Serializable;
/**
 * klasa do pobierania nazw plikow w folderze danym sciezka
 */
public class ListOfFiles implements Serializable {

    public static String[] getNamesInFolder(String userPath)
    {
        File userFiles = new File(userPath);
        return userFiles.list();
    }


}
