package com.inncontrol.platform.inncontroltaskservice.task.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmployeeIdentifier(Long employeeId) {
}
