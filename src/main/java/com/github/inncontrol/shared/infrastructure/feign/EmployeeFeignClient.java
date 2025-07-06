package com.github.inncontrol.shared.infrastructure.feign;

import com.github.inncontrol.shared.infrastructure.feign.dto.EmployeeResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "employees-service", configuration = EmployeeFeignConfig.class)
public interface EmployeeFeignClient {

    @GetMapping("api/v1/employees")
    EmployeeResource getEmployeeByEmail(@RequestParam String email);
}
