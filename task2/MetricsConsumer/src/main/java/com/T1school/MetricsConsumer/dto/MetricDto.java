package com.T1school.MetricsConsumer.dto;

import com.T1school.MetricsConsumer.domain.Metric;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetricDto {
    int id;
    Metric metric;
}
