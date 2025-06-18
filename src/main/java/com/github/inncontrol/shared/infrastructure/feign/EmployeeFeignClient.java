package com.github.inncontrol.shared.infrastructure.feign;

import com.github.inncontrol.shared.domain.valueobjects.EmployeeId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "employee-service", configuration = EmployeeFeignConfig.class)
public interface EmployeeFeignClient {

    @GetMapping("api/v1/employees")
    EmployeeId getEmployeeByEmail(@RequestParam String email);
}
