package reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileManager {
    static {
        File mainDir = new File("___Tables");
        if (!mainDir.exists()) {
            mainDir.mkdir();
        }
    }

    public FileManager() { }

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

            File newFilePath = new File("___Tables\\" + year + "\\" + chairName + ".xls");
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
                File[] filesMas =  mainFilePath.listFiles();
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

