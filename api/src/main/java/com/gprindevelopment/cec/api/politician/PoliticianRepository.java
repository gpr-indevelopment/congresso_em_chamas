package com.gprindevelopment.cec.api.politician;

import com.gprindevelopment.cec.api.expense.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoliticianRepository extends JpaRepository<Politician, Long> {

    @Query(value = "select * from monthly_expense where politician_id = :politicianId and legislature_id = :legislatureId", nativeQuery = true)
    List<MonthlyExpense> findAllExpensesByPoliticianAndLegislature(@Param("politicianId") Long politicianId, @Param("legislatureId") Long legislatureId);
}
