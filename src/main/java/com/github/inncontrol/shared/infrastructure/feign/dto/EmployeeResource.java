package com.github.inncontrol.shared.infrastructure.feign.dto;

import java.util.Date;

public record EmployeeResource(
Long employeeId,
Double salary,
Date initiationContract,
Date terminationContract,
Long profileId
) {
}
