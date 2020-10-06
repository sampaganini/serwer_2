package files.project.manager.model;

import files.project.manager.common.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ustawienie sciezki serwera
 */
public class Config {
    private static String serverPath = "D:\\java\\SERVER";
   // public static List<Data> fifo = Collections.synchronizedList(new ArrayList<Data>());


    public static String getPath() {
        return serverPath;
    }




}
