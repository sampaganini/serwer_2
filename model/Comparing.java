package files.project.manager.model;

import java.io.File;
import java.util.List;
/**
 * klasa do porownywania listy plikow. uzywam jej pozniej do porownywania listy plikow na uzytkowniku
 * a na serwerze w  jego folderze
 */
public class Comparing {

    public static List<String> compare(List<String> listOfFilesWeGot,List<String> listOfFilesWeReceive) {

        listOfFilesWeReceive.removeAll(listOfFilesWeGot);
        System.out.println(listOfFilesWeReceive);
        return listOfFilesWeReceive;
    }
}
