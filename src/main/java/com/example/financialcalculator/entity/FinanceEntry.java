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
    private Integer duration;
    private Integer interval;

    public FinanceEntry() {}

    public FinanceEntry(String title, Double amount, EntryType type, Integer duration, Integer interval) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.duration = duration;
        this.interval = interval;
    }

    // getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Double getAmount() { return amount; }
    public EntryType getType() { return type; }
    public Integer getDuration() { return duration; }
    public Integer getInterval() { return interval; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(EntryType type) { this.type = type; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public void setInterval(Integer interval) { this.interval = interval; }
}
