package com.jimmyatucla.betting.repositories;

// import com.jimmyatucla.betting.entities.Contract;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface ContractRepository extends JpaRepository<Contract, Long> {

// }



import com.jimmyatucla.betting.dtos.ContractWithBidsDTO;
import com.jimmyatucla.betting.entities.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(value = "WITH aggregated_bids AS (" +
                   "    SELECT " +
                   "        b.contract_id, " +
                   "        SUM(b.amount) FILTER (WHERE b.action = 'long') AS long_amount, " +
                   "        SUM(b.amount) FILTER (WHERE b.action = 'short') AS short_amount, " +
                   "        COUNT(*) FILTER (WHERE b.action = 'long') AS long_count, " +
                   "        COUNT(*) FILTER (WHERE b.action = 'short') AS short_count " +
                   "    FROM " +
                   "        bids b " +
                   "    GROUP BY " +
                   "        b.contract_id " +
                   ") " +
                   "SELECT " +
                   "    c.id, " +
                   "    c.assertion_text, " +
                   "    c.end_date, " + // Added field
                   "    a.long_amount, " +
                   "    a.short_amount, " +
                   "    a.long_count, " +
                   "    a.short_count " +
                   "    FROM " +
                   "    contracts c " +
                   "LEFT JOIN " +
                   "    aggregated_bids a " +
                   "ON " +
                   "    a.contract_id = c.id", nativeQuery = true)
    List<Map<String, Object>> findAllContractsWithBids();
}