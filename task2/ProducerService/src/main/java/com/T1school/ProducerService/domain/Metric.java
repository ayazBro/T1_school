package com.T1school.ProducerService.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Metric {
    private long timestamp;
    private long totalMemory;
    private long freeMemory;
}
