package files.project.manager.model;

import files.project.manager.common.Data;
import files.project.manager.common.TypeData;

import java.util.List;

/**
 * klasa ktora tworzy liste plikow ktore serwer chce uzyskac od uzytkownika
 */
public class createObjectReqFiles {
    public static void createMessWithDiff(List<String> listOfFilesWeGot, List<String> listOfFilesFromUser,List<Data> listOfmes)
    {
        List<String> difference = Comparing.compare(listOfFilesWeGot,listOfFilesFromUser);
        Data mess = new Data(TypeData.REQUIREDFILES," "," ",null,null,difference);
       listOfmes.add(mess);
    }
}
