package com.example.financialcalculator.service;

import com.example.financialcalculator.DTO.FinanceSummary;
import com.example.financialcalculator.entity.FinanceEntry;
import com.example.financialcalculator.repository.FinanceEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {
    private final FinanceEntryRepository repository;

    public FinanceService(FinanceEntryRepository repository) { this.repository = repository; }

    public List<FinanceEntry> findAll() {
        return repository.findAll();
    }

    public FinanceEntry save(FinanceEntry entry) {
        return repository.save(entry);
    }

    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public double calculateTotal(FinanceEntry entry) {
        int interval = entry.getInterval();
        int duration = entry.getDuration();

        if (interval == 0) return 0;

        double Total = entry.getAmount() * Math.floor((double) duration / interval);
        if (entry.getType() == FinanceEntry.EntryType.expense) {
            return -Total;
        } else {
            return Total;
        }
    }

    public FinanceSummary getSummary() {
        List<FinanceEntry> entries = repository.findAll();

        double totalIncome = 0;
        double totalExpense = 0;

        for(FinanceEntry entry : entries){
            double impact = calculateTotal(entry);
            if (impact >= 0){
                totalIncome += impact;
            } else {
                totalExpense += Math.abs(impact);
            }
        }
        return new FinanceSummary(totalIncome, totalExpense);
    }
}
