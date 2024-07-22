package com.example.Payroll.Model;

public class PayrollResponse {
    private double grossPay;
    private double deductions;
    private double netPay;

    public PayrollResponse(double grossPay, double deductions, double netPay) {
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netPay = netPay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }
}
