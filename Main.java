package files_task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(97,5,35,247.34);
        GameProgress gameProgress2 = new GameProgress(88,3,24,147.77);
        GameProgress gameProgress3 = new GameProgress(34,8,55,1247.81);
        GameProgress gameProgress4 = new GameProgress(69,16,70,3840.34);

        saveGame("D://Games/savegames/save1.dat", gameProgress1);
        saveGame("D://Games/savegames/save2.dat", gameProgress2);
        saveGame("D://Games/savegames/save3.dat", gameProgress3);
        saveGame("D://Games/savegames/save4.dat", gameProgress4);

        List<String> scrFiles = Arrays.asList("D://Games/savegames/save1.dat", "D://Games/savegames/save2.dat",
                "D://Games/savegames/save3.dat", "D://Games/savegames/save4.dat");

        zipFiles("D://Games/savegames/savedGames.zip", scrFiles);

        for (String delFile:scrFiles){
            File tmp = new File(delFile);
            if(tmp.exists()){
                tmp.delete();
            }
        }

    }
    public static void saveGame(String filePath, GameProgress gameProgress){
        try(FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(gameProgress);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> scrFiles){
        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))){
            for(String scrFile:scrFiles){
                File fileToZip = new File(scrFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zipOut.write(buffer);
                fis.close();
                zipOut.closeEntry();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
