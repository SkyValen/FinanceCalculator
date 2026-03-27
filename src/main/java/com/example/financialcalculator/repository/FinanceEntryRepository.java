package com.example.financialcalculator.repository;

import com.example.financialcalculator.entity.FinanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinanceEntryRepository extends JpaRepository <FinanceEntry, Long> {
    List <FinanceEntry> getByType(FinanceEntry.EntryType type);
}
