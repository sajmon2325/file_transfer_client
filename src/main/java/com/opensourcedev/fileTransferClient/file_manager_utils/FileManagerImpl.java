package com.opensourcedev.fileTransferClient.file_manager_utils;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Slf4j
@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
@Service
public class FileManagerImpl implements FileManager{

    @Override
    public boolean fileExists(String dir, String fileName) {
        String fullPath = dir + fileName;
        Path path = Paths.get(fullPath);

        if (Files.exists(path) && Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void createFile(String filePath, String fileName, byte[] file) {
        try {
            Path path = Paths.get(filePath + fileName);
            if (!fileExists(path.toString(),fileName)){
                Files.write(path, file);
            }else {
                log.debug("[!]File already exists!!..");
            }

        }catch (IOException e){
            log.error(e.getLocalizedMessage());
        }

        log.debug("[+]File saved successfully...");
    }

    @Override
    public void moveFile(String sourceFileDir, String targetDir) throws IOException {
        Path sourceDest = Paths.get(sourceFileDir);
        Path destDir = Paths.get(targetDir);        // must contain also name of the file

        Files.move(sourceDest, destDir.resolve(sourceDest.getFileName()), REPLACE_EXISTING);

        log.debug("[+]File moved to destination folder: " + destDir.toString());
    }

}
