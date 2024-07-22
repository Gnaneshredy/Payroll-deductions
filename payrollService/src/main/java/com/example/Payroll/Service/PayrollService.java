package com.example.Payroll.Service;

import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    public double calculateGrossPay(double hourlyRate, double hoursWorked) {
        return hourlyRate * hoursWorked;
    }

    public double calculateDeductions(double grossPay) {
        return grossPay * 0.20;
    }

    public double calculateNetPay(double grossPay, double deductions) {
        return grossPay - deductions;
    }
}
