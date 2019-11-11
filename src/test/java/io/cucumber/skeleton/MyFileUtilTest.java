package io.cucumber.skeleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFileUtilTest {

    @DisplayName("List Files")
    @Test
    public void testListFiles() {
        MyFileUtil fileUtil = new MyFileUtil();
        String pwd = System.getProperty("user.dir");
        String dirPath = pwd + "/src/test/resources/fixtures/html_files";
    }

    @DisplayName("List Files by Extension")
    @Test
    public void testListFilesByExtension() {
        MyFileUtil myFileUtil = new MyFileUtil();
        String pwd = System.getProperty("user.dir");
        String dirPath = pwd + "/src/test/resources/fixtures/html_files";
        myFileUtil.filesByExtension(dirPath, "html");
    }

    @DisplayName("List SQL Files by Extension")
    @Test
    public void testListFilesByExtensionSql() {
        MyFileUtil myFileUtil = new MyFileUtil();
        String pwd = System.getProperty("user.dir");
        String dirPath = pwd + "/src/test/resources/fixtures";
        myFileUtil.filesByExtension(dirPath, "sql");
    }

    @DisplayName("Get SQL File by Name and Extension")
    @Test
    public void testGetSqlFileByNameAndExtension() {
        MyFileUtil myFileUtil = new MyFileUtil();
        String pwd = System.getProperty("user.dir");
        String dirPath = pwd + "/src/test/resources/fixtures";
        String expectedFilePath = "/home/alexander/Development/study/cpc-java/src/test/resources/fixtures/sql_templates/vendor/period_totals.sql";
        File expectedFile = new File(expectedFilePath);
        assertEquals(expectedFile, myFileUtil.fileByNameAndExtension(dirPath,"sql", "period_totals"));

    }
}
