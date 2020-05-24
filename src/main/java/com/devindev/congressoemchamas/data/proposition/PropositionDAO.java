package com.devindev.congressoemchamas.data.proposition;

import com.devindev.congressoemchamas.data.politician.Politician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropositionDAO extends JpaRepository<Proposition, Long> {

    List<Proposition> findAllByPolitician(Politician politician);
}
