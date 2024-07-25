
package com.example.Payroll;

import com.example.Payroll.Model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Payroll, Long> {
}
