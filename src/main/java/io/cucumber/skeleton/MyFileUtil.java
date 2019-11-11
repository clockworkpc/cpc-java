package io.cucumber.skeleton;

import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


public class MyFileUtil {
    public File fileByNameAndExtension(String dirPath, String extension, String fileName) {
        LinkedList<File> listOfMatchFiles = filesByExtension(dirPath, extension);
        LinkedList<File> filteredList = new LinkedList<File>();

        for (File f:listOfMatchFiles) {
            String path = f.getAbsolutePath();
            String baseName = FilenameUtils.getBaseName(path);
            if (baseName.equals(fileName)) {
                filteredList.add(f);
            }
        }

        return filteredList.getFirst();
    }


    public LinkedList<File> listFilesInADiretory(String dirPath) {
        LinkedList<File> files = (LinkedList<File>) FileUtils.listFiles(new File(dirPath), null, true);
        return files;
    }
    
    public LinkedList<File> filesByExtension(String dirPath, String extension) {
        LinkedList<File> allFiles = listFilesInADiretory(dirPath);
        LinkedList<File> listOfMatchingFiles = new LinkedList<File>();

        for (File f:allFiles) {
            String path = f.getAbsolutePath();
            String fileExtension = FilenameUtils.getExtension(path);

            if (fileExtension.equals(extension)) {
                listOfMatchingFiles.add(f);
            }
        }
        return listOfMatchingFiles;
    }
}
