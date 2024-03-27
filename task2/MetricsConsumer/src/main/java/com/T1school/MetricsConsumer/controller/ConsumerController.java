package com.T1school.MetricsConsumer.controller;

import com.T1school.MetricsConsumer.dto.MetricDto;
import com.T1school.MetricsConsumer.service.KafkaMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
    private final KafkaMessagingService service;

    @Autowired
    public ConsumerController(KafkaMessagingService service) {
        this.service = service;
    }

    @GetMapping("/metrics")
    public List<MetricDto> getLst() {
       return service.getList();
    }

    @GetMapping("/metrics/{id}")
    public MetricDto getById(@PathVariable(name = "id") int id) {
        return service.getById(id);
    }

    @GetMapping("metrics/filter")
    public List<MetricDto> getListAfterFiler(@RequestParam(name="minTimestamp",required = false)long timeStamp) {
        return service.getByFilter(timeStamp);
    }


}
