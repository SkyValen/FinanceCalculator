package com.example.financialcalculator.controller;

import com.example.financialcalculator.DTO.FinanceSummary;
import com.example.financialcalculator.entity.FinanceEntry;
import com.example.financialcalculator.service.FinanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceService service;

    public FinanceController(FinanceService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<FinanceEntry> getAllEntries() {
        return service.findAll();
    }

    @PostMapping("/")
    public FinanceEntry addEntry(@RequestBody FinanceEntry entry){
        return service.save(entry);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUnit(@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/summary")
    public FinanceSummary getSummary() {
        return service.getSummary();
    }
}
