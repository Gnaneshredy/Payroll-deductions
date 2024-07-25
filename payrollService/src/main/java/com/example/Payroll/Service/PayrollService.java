package com.example.Payroll.Service;

import com.example.Payroll.Model.Payroll;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    public double calculateGrossPay(double hourlyRate, double hoursWorked) {
        return hourlyRate * hoursWorked;
    }

    public double calculateDeductions(double grossPay) {
        return calculateFederalIncomeTax(grossPay) +
                calculateStateIncomeTax(grossPay) +
                calculateSocialSecurityTax(grossPay) +
                calculateMedicareTax(grossPay) +
                calculateHealthInsurance(grossPay) +
                calculateRetirementContributions(grossPay);
    }

    public double calculateFederalIncomeTax(double grossPay) {
        // Example federal income tax calculation (e.g., 10% tax rate)
        return grossPay * 0.10;
    }

    public double calculateStateIncomeTax(double grossPay) {
        // Example state income tax calculation (e.g., 5% tax rate)
        return grossPay * 0.05;
    }

    public double calculateSocialSecurityTax(double grossPay) {
        // Example social security tax calculation (e.g., 6.2% tax rate)
        return grossPay * 0.062;
    }

    public double calculateMedicareTax(double grossPay) {
        // Example Medicare tax calculation (e.g., 1.45% tax rate)
        return grossPay * 0.0145;
    }

    public double calculateHealthInsurance(double grossPay) {
        // Example health insurance calculation (e.g., flat rate)
        return 200.00;
    }

    public double calculateRetirementContributions(double grossPay) {
        // Example retirement contribution calculation (e.g., 3% of gross pay)
        return grossPay * 0.03;
    }

    public double calculateNetPay(double grossPay, double totalDeductions) {
        return grossPay - totalDeductions;
    }

    public void savePayroll(Payroll payroll) {
    }
}
