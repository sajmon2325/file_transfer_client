package com.opensourcedev.fileTransferClient.web_client;

import com.opensourcedev.fileTransferClient.model.Report;

public interface FileTransferWebClient {

    String getSomeHelp();
    Report getAllFilesFromAllDirs();
    Report getAllSalaryFiles();
    Report getAllAttendanceFiles();
    Report getAllPerformanceFiles();
}
