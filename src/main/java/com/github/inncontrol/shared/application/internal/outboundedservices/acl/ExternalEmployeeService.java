package com.github.inncontrol.shared.application.internal.outboundedservices.acl;

import com.github.inncontrol.shared.domain.valueobjects.EmployeeId;
import com.github.inncontrol.shared.infrastructure.feign.EmployeeFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 6/21/24 @ 08:03
 */

@AllArgsConstructor
@Service
public class ExternalEmployeeService {

    private final EmployeeFeignClient employeeFeignClient;

    public Optional<EmployeeId> fetchEmployeeIdentifierByEmail(String email) {
        try {
            EmployeeId employeeId = employeeFeignClient.getEmployeeByEmail(email);
            return Optional.ofNullable(employeeId);
        } catch (Exception e) {
            System.err.println("Error fetching profile by email: " + e.getMessage());
            return Optional.empty();
        }
    }
}