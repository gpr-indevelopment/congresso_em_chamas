package com.devindev.congressoemchamas.politician;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticianDAO extends JpaRepository<Politician, Long> {
}
