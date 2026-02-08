package com.threading;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SalesDataRepository extends JpaRepository<SalesData, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE sales_data", nativeQuery = true)
    void truncateTable();
}
