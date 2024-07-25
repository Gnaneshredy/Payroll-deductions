//
//package com.example.Payroll.Controller;
//
//import com.example.Payroll.Service.PayrollService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.ResponseEntity;
//
//@RestController
//@RequestMapping("/api/payroll")
//public class PayrollController {
//
//    private final PayrollService payrollService;
//
//    @Autowired
//    public PayrollController(PayrollService payrollService) {
//        this.payrollService = payrollService;
//    }
//
//    @GetMapping("/calculate")
//    public ResponseEntity<PayrollResponse> calculatePayroll(
//            @RequestParam(required = true, defaultValue = "0") double hourlyRate,
//            @RequestParam(required = true, defaultValue = "0") double hoursWorked) {
//
//        // Validate inputs
//        if (hourlyRate < 0 || hoursWorked < 0) {
//            return ResponseEntity.badRequest().body(new PayrollResponse("Invalid input values"));
//        }
//
//        try {
//            double grossPay = payrollService.calculateGrossPay(hourlyRate, hoursWorked);
//            double federalIncomeTax = payrollService.calculateFederalIncomeTax(grossPay);
//            double stateIncomeTax = payrollService.calculateStateIncomeTax(grossPay);
//            double socialSecurityTax = payrollService.calculateSocialSecurityTax(grossPay);
//            double medicareTax = payrollService.calculateMedicareTax(grossPay);
//            double healthInsurance = payrollService.calculateHealthInsurance(grossPay);
//            double retirementContributions = payrollService.calculateRetirementContributions(grossPay);
//            double totalDeductions = federalIncomeTax + stateIncomeTax + socialSecurityTax + medicareTax + healthInsurance + retirementContributions;
//            double netPay = payrollService.calculateNetPay(grossPay, totalDeductions);
//
//            return ResponseEntity.ok(new PayrollResponse(grossPay, federalIncomeTax, stateIncomeTax, socialSecurityTax, medicareTax, healthInsurance, retirementContributions, totalDeductions, netPay));
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(new PayrollResponse("Error calculating payroll"));
//        }
//    }
//
//    public static class PayrollResponse {
//        private double grossPay;
//        private double federalIncomeTax;
//        private double stateIncomeTax;
//        private double socialSecurityTax;
//        private double medicareTax;
//        private double healthInsurance;
//        private double retirementContributions;
//        private double totalDeductions;
//        private double netPay;
//        private String errorMessage;
//
//        public PayrollResponse(double grossPay, double federalIncomeTax, double stateIncomeTax, double socialSecurityTax, double medicareTax, double healthInsurance, double retirementContributions, double totalDeductions, double netPay) {
//            this.grossPay = grossPay;
//            this.federalIncomeTax = federalIncomeTax;
//            this.stateIncomeTax = stateIncomeTax;
//            this.socialSecurityTax = socialSecurityTax;
//            this.medicareTax = medicareTax;
//            this.healthInsurance = healthInsurance;
//            this.retirementContributions = retirementContributions;
//            this.totalDeductions = totalDeductions;
//            this.netPay = netPay;
//            this.errorMessage = null;
//        }
//
//        public PayrollResponse(String errorMessage) {
//            this.grossPay = 0;
//            this.federalIncomeTax = 0;
//            this.stateIncomeTax = 0;
//            this.socialSecurityTax = 0;
//            this.medicareTax = 0;
//            this.healthInsurance = 0;
//            this.retirementContributions = 0;
//            this.totalDeductions = 0;
//            this.netPay = 0;
//            this.errorMessage = errorMessage;
//        }
//
//        public double getGrossPay() {
//            return grossPay;
//        }
//
//        public double getFederalIncomeTax() {
//            return federalIncomeTax;
//        }
//
//        public double getStateIncomeTax() {
//            return stateIncomeTax;
//        }
//
//        public double getSocialSecurityTax() {
//            return socialSecurityTax;
//        }
//
//        public double getMedicareTax() {
//            return medicareTax;
//        }
//
//        public double getHealthInsurance() {
//            return healthInsurance;
//        }
//
//        public double getRetirementContributions() {
//            return retirementContributions;
//        }
//
//        public double getTotalDeductions() {
//            return totalDeductions;
//        }
//
//        public double getNetPay() {
//            return netPay;
//        }
//
//        public String getErrorMessage() {
//            return errorMessage;
//        }
//    }
//}
package com.example.Payroll.Controller;

import com.example.Payroll.Model.Payroll;
import com.example.Payroll.Service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<PayrollResponse> calculatePayroll(
            @RequestParam(required = true, defaultValue = "0") double hourlyRate,
            @RequestParam(required = true, defaultValue = "0") double hoursWorked,
            @RequestParam(required = true) int employeeId) {

        // Validate inputs
        if (hourlyRate < 0 || hoursWorked < 0) {
            return ResponseEntity.badRequest().body(new PayrollResponse("Invalid input values"));
        }

        try {
            double grossPay = payrollService.calculateGrossPay(hourlyRate, hoursWorked);
            double federalIncomeTax = payrollService.calculateFederalIncomeTax(grossPay);
            double stateIncomeTax = payrollService.calculateStateIncomeTax(grossPay);
            double socialSecurityTax = payrollService.calculateSocialSecurityTax(grossPay);
            double medicareTax = payrollService.calculateMedicareTax(grossPay);
            double healthInsurance = payrollService.calculateHealthInsurance(grossPay);
            double retirementContributions = payrollService.calculateRetirementContributions(grossPay);
            double totalDeductions = federalIncomeTax + stateIncomeTax + socialSecurityTax + medicareTax + healthInsurance + retirementContributions;
            double netPay = payrollService.calculateNetPay(grossPay, totalDeductions);

            Payroll payroll = new Payroll(employeeId, hourlyRate, hoursWorked, grossPay, federalIncomeTax, stateIncomeTax, socialSecurityTax, medicareTax, healthInsurance, retirementContributions, totalDeductions, netPay);
            payrollService.savePayroll(payroll);

            return ResponseEntity.ok(new PayrollResponse(grossPay, federalIncomeTax, stateIncomeTax, socialSecurityTax, medicareTax, healthInsurance, retirementContributions, totalDeductions, netPay));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PayrollResponse("Error calculating payroll"));
        }
    }

    public static class PayrollResponse {
        private double grossPay;
        private double federalIncomeTax;
        private double stateIncomeTax;
        private double socialSecurityTax;
        private double medicareTax;
        private double healthInsurance;
        private double retirementContributions;
        private double totalDeductions;
        private double netPay;
        private String errorMessage;

        public PayrollResponse(double grossPay, double federalIncomeTax, double stateIncomeTax, double socialSecurityTax, double medicareTax, double healthInsurance, double retirementContributions, double totalDeductions, double netPay) {
            this.grossPay = grossPay;
            this.federalIncomeTax = federalIncomeTax;
            this.stateIncomeTax = stateIncomeTax;
            this.socialSecurityTax = socialSecurityTax;
            this.medicareTax = medicareTax;
            this.healthInsurance = healthInsurance;
            this.retirementContributions = retirementContributions;
            this.totalDeductions = totalDeductions;
            this.netPay = netPay;
            this.errorMessage = null;
        }

        public PayrollResponse(String errorMessage) {
            this.grossPay = 0;
            this.federalIncomeTax = 0;
            this.stateIncomeTax = 0;
            this.socialSecurityTax = 0;
            this.medicareTax = 0;
            this.healthInsurance = 0;
            this.retirementContributions = 0;
            this.totalDeductions = 0;
            this.netPay = 0;
            this.errorMessage = errorMessage;
        }

        // Getters and Setters
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

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
