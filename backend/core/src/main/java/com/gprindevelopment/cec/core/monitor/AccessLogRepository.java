package com.gprindevelopment.cec.core.monitor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
