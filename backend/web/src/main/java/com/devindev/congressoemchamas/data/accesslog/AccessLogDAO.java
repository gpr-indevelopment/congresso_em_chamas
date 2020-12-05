package com.devindev.congressoemchamas.data.accesslog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogDAO extends JpaRepository<AccessLog, Long> {
}
