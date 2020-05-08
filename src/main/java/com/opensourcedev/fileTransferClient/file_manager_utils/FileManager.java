package com.opensourcedev.fileTransferClient.file_manager_utils;

import java.io.IOException;

public interface FileManager {

    boolean fileExists(String dir, String fileName);
    void createFile(String filePath ,String fileName, byte[] file);
    void moveFile(String sourceFileDir, String targetDir) throws IOException;
}
