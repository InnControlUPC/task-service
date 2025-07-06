package com.github.inncontrol.shared.infrastructure.feign;

import com.github.inncontrol.shared.domain.valueobjects.EmployeeId;
import com.github.inncontrol.shared.infrastructure.feign.dto.EmployeeResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFeignClientAdapter {
    private final EmployeeFeignClient employeeFeignClient;

    public EmployeeFeignClientAdapter(EmployeeFeignClient employeeFeignClient) {
        this.employeeFeignClient = employeeFeignClient;
    }

    public EmployeeId getEmployeeIdByEmail(String email) {
        EmployeeResource employeeResource = employeeFeignClient.getEmployeeByEmail(email);
        return new EmployeeId(employeeResource.employeeId());
    }

}
