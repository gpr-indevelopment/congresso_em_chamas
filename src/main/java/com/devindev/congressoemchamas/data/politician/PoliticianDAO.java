package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PoliticianDAO extends JpaRepository<Politician, Long> {

    @Query(value = "select * from monthly_expense where politician_id = :politicianId and legislature_id = :legislatureId", nativeQuery = true)
    List<MonthlyExpense> findAllExpensesByPoliticianAndLegislature(@Param("politicianId") Long politicianId, @Param("legislatureId") Long legislatureId);
}
