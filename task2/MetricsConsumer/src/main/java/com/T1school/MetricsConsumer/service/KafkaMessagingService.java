package com.T1school.MetricsConsumer.service;

import com.T1school.MetricsConsumer.domain.Metric;
import com.T1school.MetricsConsumer.dto.MetricDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessagingService {

    private List<MetricDto> list=new ArrayList<>();
    @KafkaListener(topics = "metrics-topic",groupId ="ayaz-group", containerFactory = "ayazListenerContainerFactory")
    public void getMetric(Metric metric) {
        log.info("Message accepted {}", metric);
        if(list.isEmpty())
            list.add(new MetricDto(0,metric));
        else
            list.add(new MetricDto(list.size(),metric));

    }

    public List<MetricDto> getList() {
        return  list;
    }

    public MetricDto getById(int id) {
        for(MetricDto metric:list) {
            if(id==metric.getId())
                return metric;
        }
        return null;
    }

    public List<MetricDto> getByFilter(long timeStamp) {
        List<MetricDto> result=new ArrayList<>();
        for(MetricDto metric:list) {
            if(metric.getMetric().getTimestamp()>timeStamp)
                result.add(metric);
        }
        return result;
    }

}