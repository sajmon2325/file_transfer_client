package com.opensourcedev.fileTransferClient.web_client;

import com.opensourcedev.fileTransferClient.file_manager_utils.FileManager;
import com.opensourcedev.fileTransferClient.model.Report;
import com.opensourcedev.fileTransferClient.web_client.web_client_configuration.WebClientConfigurator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Slf4j
@Service
public class FileTransferWebClientImpl implements FileTransferWebClient{

    private WebClientConfigurator webClientConfigurator;
    private FileManager fileManager;
    public static final String CLIENT_ERROR = "[!]4xx Error occurred...";
    public static final String SERVER_ERROR = "[!]5xx Error occurred...";

    @Autowired
    public FileTransferWebClientImpl(WebClientConfigurator webClientConfigurator, FileManager fileManager) {
        this.webClientConfigurator = webClientConfigurator;
        this.fileManager = fileManager;
    }

    @Override
    public String getSomeHelp() {
        return webClientConfigurator.setupWebClient().get().uri("/help")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.debug(CLIENT_ERROR);
                    return Mono.error(new RuntimeException("4xx Error"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    log.debug(SERVER_ERROR);
                    return Mono.error(new RuntimeException("5xx Error"));
                })
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Report getAllFilesFromAllDirs() {
        return getDocument("/getAllFiles");
    }

    @Override
    public Report getAllSalaryFiles() {
        return getDocument("/getSalaryFiles");
    }

    @Override
    public Report getAllAttendanceFiles() {
        return getDocument("/getAttendanceFiles");
    }

    @Override
    public Report getAllPerformanceFiles() {
        return getDocument("/getPerformanceFiles");
    }


    private Report getDocument(String uri) {
        return webClientConfigurator.setupWebClient().get().uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.debug(CLIENT_ERROR);
                    return Mono.error(new RuntimeException("4xx Error"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    log.debug(CLIENT_ERROR);
                    return Mono.error(new RuntimeException("5xx Error"));
                })
                .bodyToMono(Report.class)
                .block();
    }
}
