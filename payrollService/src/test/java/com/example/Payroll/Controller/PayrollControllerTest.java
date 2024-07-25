package com.example.Payroll.Controller;

import com.example.Payroll.Service.PayrollService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PayrollController.class)
public class PayrollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayrollService payrollService;

    @Test
    public void testCalculatePayroll() throws Exception {
        double hourlyRate = 30.0;
        double hoursWorked = 50.0;
        double grossPay = 1500.0;
        double federalIncomeTax = 150.0;
        double stateIncomeTax = 75.0;
        double socialSecurityTax = 93.0;
        double medicareTax = 21.75;
        double healthInsurance = 200.0;
        double retirementContributions = 45.0;
        double totalDeductions = federalIncomeTax + stateIncomeTax + socialSecurityTax + medicareTax + healthInsurance + retirementContributions;
        double netPay = grossPay - totalDeductions;

        when(payrollService.calculateGrossPay(hourlyRate, hoursWorked)).thenReturn(grossPay);
        when(payrollService.calculateFederalIncomeTax(grossPay)).thenReturn(federalIncomeTax);
        when(payrollService.calculateStateIncomeTax(grossPay)).thenReturn(stateIncomeTax);
        when(payrollService.calculateSocialSecurityTax(grossPay)).thenReturn(socialSecurityTax);
        when(payrollService.calculateMedicareTax(grossPay)).thenReturn(medicareTax);
        when(payrollService.calculateHealthInsurance(grossPay)).thenReturn(healthInsurance);
        when(payrollService.calculateRetirementContributions(grossPay)).thenReturn(retirementContributions);
        when(payrollService.calculateDeductions(grossPay)).thenReturn(totalDeductions);
        when(payrollService.calculateNetPay(grossPay, totalDeductions)).thenReturn(netPay);

        mockMvc.perform(get("/api/payroll/calculate")
                        .param("hourlyRate", String.valueOf(hourlyRate))
                        .param("hoursWorked", String.valueOf(hoursWorked)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grossPay").value(grossPay))
                .andExpect(jsonPath("$.federalIncomeTax").value(federalIncomeTax))
                .andExpect(jsonPath("$.stateIncomeTax").value(stateIncomeTax))
                .andExpect(jsonPath("$.socialSecurityTax").value(socialSecurityTax))
                .andExpect(jsonPath("$.medicareTax").value(medicareTax))
                .andExpect(jsonPath("$.healthInsurance").value(healthInsurance))
                .andExpect(jsonPath("$.retirementContributions").value(retirementContributions))
                .andExpect(jsonPath("$.totalDeductions").value(totalDeductions))
                .andExpect(jsonPath("$.netPay").value(netPay));
    }
}
