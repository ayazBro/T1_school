package com.T1school.ProducerService.service;

import com.T1school.ProducerService.domain.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final KafkaTemplate<String, Metric> kafkaTemplate;
    @Autowired
    public ProducerService(KafkaTemplate<String, Metric> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(Metric metric) {
        kafkaTemplate.send("metrics-topic",metric);
    }
}
