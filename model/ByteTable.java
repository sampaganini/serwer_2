package files.project.manager.model;

import java.io.*;
/**
 * zamiana pliku na tablice bajtow i na odwrot
 */
public class ByteTable
{
   public static byte[] readFileToByteArray(File readFile)
    {
        FileInputStream fileInputStream = null;

        byte[] bArray = new byte[(int) readFile.length()];
        try{
            fileInputStream = new FileInputStream(readFile);
            fileInputStream.read(bArray);
            fileInputStream.close();

        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
    public static void writeByteToFile(File writeFile, byte[] bytes)
    {
        try {

            OutputStream fileOutputStream = new FileOutputStream(writeFile);
            fileOutputStream.write(bytes);
            System.out.println("Success");
            fileOutputStream.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
