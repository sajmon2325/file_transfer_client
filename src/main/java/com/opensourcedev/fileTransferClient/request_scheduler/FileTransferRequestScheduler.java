package com.opensourcedev.fileTransferClient.request_scheduler;

import com.opensourcedev.fileTransferClient.application_handler.WebClientHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileTransferRequestScheduler {

    private final WebClientHandler webClientHandler;


    @Autowired
    public FileTransferRequestScheduler(WebClientHandler webClientHandler) {
        this.webClientHandler = webClientHandler;
    }

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleHelpRequest(){
        webClientHandler.processHelpFile();
    }

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleAllFilesRequest(){
        webClientHandler.processAllFiles();
    }

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleSalaryRequest(){
        webClientHandler.processSalaryFiles();
    }

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleAttendanceRequest(){
        webClientHandler.processAttendanceFiles();
    }

    @Scheduled(cron = "0 * * ? * *")
    public void schedulePerformanceRequest(){
        webClientHandler.processPerformanceFiles();
    }

}
