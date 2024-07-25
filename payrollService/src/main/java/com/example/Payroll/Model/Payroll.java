package com.example.Payroll.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int employeeId;

    @Column(nullable = false)
    private double hourlyRate;

    @Column(nullable = false)
    private double hoursWorked;

    @Column(nullable = false)
    private double grossPay;

    @Column(nullable = false)
    private double federalIncomeTax;

    @Column(nullable = false)
    private double stateIncomeTax;

    @Column(nullable = false)
    private double socialSecurityTax;

    @Column(nullable = false)
    private double medicareTax;

    @Column(nullable = false)
    private double healthInsurance;

    @Column(nullable = false)
    private double retirementContributions;

    @Column(nullable = false)
    private double totalDeductions;

    @Column(nullable = false)
    private double netPay;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public Payroll() {
        // Default constructor
    }

    public Payroll(int employeeId, double hourlyRate, double hoursWorked, double grossPay, double federalIncomeTax, double stateIncomeTax, double socialSecurityTax, double medicareTax, double healthInsurance, double retirementContributions, double totalDeductions, double netPay) {
        this.employeeId = employeeId;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.grossPay = grossPay;
        this.federalIncomeTax = federalIncomeTax;
        this.stateIncomeTax = stateIncomeTax;
        this.socialSecurityTax = socialSecurityTax;
        this.medicareTax = medicareTax;
        this.healthInsurance = healthInsurance;
        this.retirementContributions = retirementContributions;
        this.totalDeductions = totalDeductions;
        this.netPay = netPay;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getFederalIncomeTax() {
        return federalIncomeTax;
    }

    public void setFederalIncomeTax(double federalIncomeTax) {
        this.federalIncomeTax = federalIncomeTax;
    }

    public double getStateIncomeTax() {
        return stateIncomeTax;
    }

    public void setStateIncomeTax(double stateIncomeTax) {
        this.stateIncomeTax = stateIncomeTax;
    }

    public double getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(double socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    public double getMedicareTax() {
        return medicareTax;
    }

    public void setMedicareTax(double medicareTax) {
        this.medicareTax = medicareTax;
    }

    public double getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(double healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public double getRetirementContributions() {
        return retirementContributions;
    }

    public void setRetirementContributions(double retirementContributions) {
        this.retirementContributions = retirementContributions;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
