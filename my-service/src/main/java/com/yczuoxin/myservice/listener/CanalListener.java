package com.yczuoxin.myservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CanalListener implements CommandLineRunner {

    @Value("${canal.server.ip:localhost}")
    private String canalIp;

    @Value("${canal.server.port:3306}")
    private int port;

    @Value("${canal.server.destination:example}")
    private String destination;

    @Override
    public void run(String... args) throws Exception {
//        CanalConnectors.newSingleConnector()
    }
}
