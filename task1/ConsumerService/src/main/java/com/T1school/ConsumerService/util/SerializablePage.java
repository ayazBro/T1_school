package com.T1school.ConsumerService.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@JsonIgnoreProperties("pageable")
public class SerializablePage<T> extends PageImpl<T> {
    public SerializablePage(List<T> content, int number, int size, long totalElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public SerializablePage(List<T> content) {
        super(content);
    }
}
