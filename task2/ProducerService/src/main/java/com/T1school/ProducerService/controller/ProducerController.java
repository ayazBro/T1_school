package com.T1school.ProducerService.controller;

import com.T1school.ProducerService.domain.Metric;
import com.T1school.ProducerService.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/producer")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @PostMapping("/metrics")
    public ResponseEntity<Void> sendMetrics() {
        Metric metric=new Metric( System.currentTimeMillis(),
                                Runtime.getRuntime().totalMemory(),
                                Runtime.getRuntime().freeMemory()
        );
        log.info("Metrics are created in the system: {}", metric);
        producerService.sendMessage(metric);
        log.info("Metrics successfully sent to Kafka: {}",metric);
        return ResponseEntity.ok().build();
    }
}
