package com.opensourcedev.fileTransferClient.web_client.web_client_configuration;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.tcp.TcpClient;

public interface WebClientConfigurator {

    TcpClient setupTcpClient();
    WebClient setupWebClient();
}
