package com.opensourcedev.fileTransferClient.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
@Component
public class Report {

    private List<HashMap<String, byte[]>> filesFromDir;

}
