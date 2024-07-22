package com.example.Payroll.Service;


//package com.example.payroll.Service;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayrollServiceTest {

    private final PayrollService payrollService = new PayrollService();

    @Test
    public void testCalculateGrossPay() {
        double grossPay = payrollService.calculateGrossPay(20.0, 40.0);
        assertEquals(800.0, grossPay);
    }

    @Test
    public void testCalculateDeductions() {
        double deductions = payrollService.calculateDeductions(800.0);
        assertEquals(160.0, deductions);
    }

    @Test
    public void testCalculateNetPay() {
        double netPay = payrollService.calculateNetPay(800.0, 160.0);
        assertEquals(640.0, netPay);
    }
}
