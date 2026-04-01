package com.example.financialcalculator.entity;

import jakarta.persistence.*;

@Entity
public class FinanceEntry {
    public enum EntryType {
        income,
        expense
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double amount;
    @Enumerated(EnumType.STRING)
    private EntryType type;
    private Integer durationDays;
    private Integer intervalDays;

    public FinanceEntry() {}

    public FinanceEntry(String title, Double amount, EntryType type, Integer durationDays, Integer intervalDays) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.durationDays = durationDays;
        this.intervalDays = intervalDays;
    }

    // getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Double getAmount() { return amount; }
    public EntryType getType() { return type; }
    public Integer getDurationDays() { return durationDays; }
    public Integer getIntervalDays() { return intervalDays; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(EntryType type) { this.type = type; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }
    public void setIntervalDays(Integer intervalDays) { this.intervalDays = intervalDays; }
}
