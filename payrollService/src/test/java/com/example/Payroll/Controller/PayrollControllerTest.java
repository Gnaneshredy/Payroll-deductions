package com.example.Payroll.Controller;


//package com.example.payroll.Controller;

//import com.example.payroll.Service.PayrollService;
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
        when(payrollService.calculateGrossPay(20.0, 40.0)).thenReturn(800.0);
        when(payrollService.calculateDeductions(800.0)).thenReturn(160.0);
        when(payrollService.calculateNetPay(800.0, 160.0)).thenReturn(640.0);

        mockMvc.perform(get("/api/payroll/calculate")
                        .param("hourlyRate", "20.0")
                        .param("hoursWorked", "40.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grossPay").value(800.0))
                .andExpect(jsonPath("$.deductions").value(160.0))
                .andExpect(jsonPath("$.netPay").value(640.0));
    }

    private class PayrollService {
        public Object calculateGrossPay(double v, double v1) {
            return null;
        }

        public Object calculateDeductions(double v) {
            return null;
        }

        public Object calculateNetPay(double v, double v1) {
            return null;
        }
    }
}

