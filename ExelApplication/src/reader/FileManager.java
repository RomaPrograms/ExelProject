package reader;

import dbconnection.DbConnection;

import java.sql.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FileManager {

//    private static final String SQL1 = "CREATE TABLE addedfiles (\n" +
//            "    id          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//            "    yearOfTable INT,\n" +
//            "    chairName   VARCHAR,\n" +
//            "    pathToFile  VARCHAR\n" +
//            ");\n";
//    private static final String SQL2 = "CREATE TABLE chairs (\n" +
//            "    chairId       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//            "    chairUnivName VARCHAR,\n" +
//            "    chairYear     INT,\n" +
//            "    chairName     VARCHAR,\n" +
//            "    chairNpp      DOUBLE,\n" +
//            "    chairSchool   DOUBLE,\n" +
//            "    chairMethodic DOUBLE,\n" +
//            "    chairScience  DOUBLE,\n" +
//            "    mathtechBase  DOUBLE,\n" +
//            "    chairIdeolog  DOUBLE,\n" +
//            "    chairSecurity DOUBLE,\n" +
//            "    chairRate     DOUBLE,\n" +
//            "    chairQual     DOUBLE,\n" +
//            "    addedFilesID  INT\n" +
//            ");";
//    private static final String SQL3 = "CREATE TABLE constants (\n" +
//            "    idconstants     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//            "    constStudy      DOUBLE,\n" +
//            "    constMethodical DOUBLE,\n" +
//            "    constScience    DOUBLE,\n" +
//            "    constMatBase    DOUBLE,\n" +
//            "    constIdeology   DOUBLE,\n" +
//            "    constVSandOBVS  DOUBLE,\n" +
//            "    constCMP        DOUBLE\n" +
//            ");\n";
//    private static final String SQL4 = "CREATE TABLE persons (\n" +
//            "    pid        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//            "    pchair     VARCHAR,\n" +
//            "    pyear      INT,\n" +
//            "    prank      VARCHAR,\n" +
//            "    pname      VARCHAR,\n" +
//            "    pcategory  VARCHAR,\n" +
//            "    prate      DOUBLE,\n" +
//            "    prateQual  DOUBLE,\n" +
//            "    addedFiles INT\n" +
//            ");";
//    private static final String SQL5 = "INSERT INTO constants values"
//            + " (1, 0, 0, 0, 0, 0, 0, 0)";

    static {
        File mainDir = new File("___Tables");
        if (!mainDir.exists()) {
            mainDir.mkdir();
//            Connection connection = DbConnection.getConnection();
//            try {
//                PreparedStatement stat = connection.prepareStatement(SQL1);
//                stat.executeUpdate();
//                stat = connection.prepareStatement(SQL2);
//                stat.executeUpdate();
//                stat = connection.prepareStatement(SQL3);
//                stat.executeUpdate();
//                stat = connection.prepareStatement(SQL4);
//                stat.executeUpdate();
//                stat = connection.prepareStatement(SQL5);
//                stat.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
        }
    }

    public FileManager() {
    }

    private boolean CopyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            return true;
        } catch (Exception e) {
            System.err.println("FileManager CopyFile error!!!");
            return false;
        } finally {
            is.close();
            os.close();
        }
    }

    public String AddFile(File path) {
        try {
            TableFileReader tfReader = new TableFileReader(path);
            String year = tfReader.GetDateFromFile();
            String chairName = tfReader.GetChairFromFile();
            String fuckName = tfReader.GetUnivName();
            if (year == null || chairName == null) {
                System.err.println("FileManager AddFile error: unknown file format!!!");
                return null;
            }

            File newDir = new File("___Tables\\" + year);
            if (!newDir.exists()) {
                if (!newDir.mkdir()) {
                    return null;
                }
            }

            File newFilePath = new File("___Tables\\" + year + "\\" + fuckName + "-" + chairName + ".xls");
            if (!newFilePath.exists()) {
                if (!newFilePath.createNewFile()) {
                    return null;
                }
            }

            if (!CopyFile(path, newFilePath)) {
                return null;
            }

            return newFilePath.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("FileManager AddFile error!!!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean DeleteFile(File path) {
        try {
            if (path.exists()) {
                File fileDir = path.getParentFile();
                if (!path.delete()) {
                    return false;
                }
                if ((fileDir.listFiles()).length == 0) {
                    if (!fileDir.delete()) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("FileManager error!!!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<String> GetLoadedFilesList() {
        ArrayList<String> filesNameList = new ArrayList<>();
        try {
            File mainFilePath = new File("___Tables");
            if (mainFilePath.exists()) {
                File[] filesMas = mainFilePath.listFiles();
                for (File file : filesMas) {
                    filesNameList.add(file.getName());
                }
            }
            return filesNameList;
        } catch (Exception e) {
            System.err.println("FileManager GetLoadedFilesList error!!!");
            System.err.println(e.getMessage());
            return filesNameList;
        }
    }

}

