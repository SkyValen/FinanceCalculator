package com.example.financialcalculator.DTO;

public class FinanceSummary {
    private double totalIncome;
    private double totalExpense;
    private double netResult;

    public FinanceSummary(double totalIncome, double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netResult = totalIncome - totalExpense;
    }

    // getters
    public double getTotalIncome() {
        return totalIncome;
    }
    public double getTotalExpense() {
        return totalExpense;
    }
    public double getNetResult() {
        return netResult;
    }
}
