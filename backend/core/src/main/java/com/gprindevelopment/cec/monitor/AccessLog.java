package com.gprindevelopment.cec.monitor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class AccessLog {

    @Id
    @GeneratedValue
    private Long id;

    private HttpMethod method;

    private String endpoint;

    private Timestamp timestamp;

    private Long processingTimeMillis;
}
