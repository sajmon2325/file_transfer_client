package com.opensourcedev.fileTransferClient.application_handler;

import com.opensourcedev.fileTransferClient.file_manager_utils.FileManager;
import com.opensourcedev.fileTransferClient.model.Report;
import com.opensourcedev.fileTransferClient.web_client.FileTransferWebClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Slf4j
@Service
public class WebClientHandler {

    @Value("${folder.root.folder}")
    private String rootDir;
    @Value("${folder.destdir.salary}")
    private String salaryDir;
    @Value("${folder.destdir.attendance}")
    private String attendanceDir;
    @Value("${folder.destdir.performance}")
    private String performanceDir;

    private final FileTransferWebClient webClient;
    private final FileManager fileManager;
    private static final String RETURN_MESSAGE = "[+]File created successfully...";

    @Autowired
    public WebClientHandler(FileTransferWebClient webClient, FileManager fileManager) {
        this.webClient = webClient;
        this.fileManager = fileManager;
    }

    public void processHelpFile(){
        String helpDocument = webClient.getSomeHelp();
        if(helpDocument != null){
            fileManager.createFile(rootDir, "HELP.txt", helpDocument.getBytes());
        }else {
            log.debug("[!]File is empty...");
        }
    }

    /*
        Don't use this method normally. It will store all documents into root folder
        This method is suitable when you need to do some sort of backup. Than all files
        on server will ne downloaded with metadata and stored into root folder
     */
    public void processAllFiles(){
        Report allDocuments = webClient.getAllFilesFromAllDirs();
        processData(allDocuments,rootDir);
    }

    public void processSalaryFiles(){
        Report abilitDocument = webClient.getAllSalaryFiles();
        processData(abilitDocument, salaryDir);
    }

    public void processAttendanceFiles(){
        Report adsDocument = webClient.getAllAttendanceFiles();
        processData(adsDocument, attendanceDir);
    }

    public void processPerformanceFiles(){
        Report agDruckDocument = webClient.getAllPerformanceFiles();
        processData(agDruckDocument, performanceDir);
    }

    private void processData(Report document, String dir){
        List<HashMap<String, byte[]>> files = new ArrayList<>(document.getFilesFromDir());

        if(!files.isEmpty()){
            files.forEach(file -> {
                for (Map.Entry<String, byte[]> fileMap : file.entrySet()) {
                    String fileName = fileMap.getKey();
                    byte[] fileBody = fileMap.getValue();

                    fileManager.createFile(dir, fileName, fileBody);
                    log.debug(RETURN_MESSAGE);
                }
            });
        }else {
            log.debug("[!]Empty List...");
        }
    }
}
