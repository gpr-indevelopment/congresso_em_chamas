package com.gprindevelopment.cec.proposicoes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropositionDAO extends JpaRepository<Proposition, Long> {

    List<Proposition> findAllByPoliticianId(Long politicianId);
}
