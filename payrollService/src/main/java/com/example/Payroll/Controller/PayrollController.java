//
//package com.example.Payroll.Controller;
//
//import com.example.Payroll.Service.PayrollService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
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
//    public PayrollResponse calculatePayroll(@RequestParam double hourlyRate,
//                                            @RequestParam double hoursWorked) {
//        double grossPay = payrollService.calculateGrossPay(hourlyRate, hoursWorked);
//        double deductions = payrollService.calculateDeductions(grossPay);
//        double netPay = payrollService.calculateNetPay(grossPay, deductions);
//
//        return new PayrollResponse(grossPay, deductions, netPay);
//    }
//
//    public static class PayrollResponse {
//        private double grossPay;
//        private double deductions;
//        private double netPay;
//
//        public PayrollResponse(double grossPay, double deductions, double netPay) {
//            this.grossPay = grossPay;
//            this.deductions = deductions;
//            this.netPay = netPay;
//        }
//
//        public double getGrossPay() {
//            return grossPay;
//        }
//
//        public double getDeductions() {
//            return deductions;
//        }
//
//        public double getNetPay() {
//            return netPay;
//        }
//    }
//}
package com.example.Payroll.Controller;

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
            @RequestParam(required = true, defaultValue = "0") double hoursWorked) {

        // Validate inputs
        if (hourlyRate < 0 || hoursWorked < 0) {
            return ResponseEntity.badRequest().body(new PayrollResponse("Invalid input values"));
        }

        try {
            double grossPay = payrollService.calculateGrossPay(hourlyRate, hoursWorked);
            double deductions = payrollService.calculateDeductions(grossPay);
            double netPay = payrollService.calculateNetPay(grossPay, deductions);

            return ResponseEntity.ok(new PayrollResponse(grossPay, deductions, netPay));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PayrollResponse("Error calculating payroll"));
        }
    }

    public static class PayrollResponse {
        private double grossPay;
        private double deductions;
        private double netPay;
        private String errorMessage;

        public PayrollResponse(double grossPay, double deductions, double netPay) {
            this.grossPay = grossPay;
            this.deductions = deductions;
            this.netPay = netPay;
            this.errorMessage = null;
        }

        public PayrollResponse(String errorMessage) {
            this.grossPay = 0;
            this.deductions = 0;
            this.netPay = 0;
            this.errorMessage = errorMessage;
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

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
