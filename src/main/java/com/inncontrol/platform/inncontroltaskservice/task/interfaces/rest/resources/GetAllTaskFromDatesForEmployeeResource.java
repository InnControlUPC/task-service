package com.inncontrol.platform.inncontroltaskservice.task.interfaces.rest.resources;

public record GetAllTaskFromDatesForEmployeeResource(
        String employeeEmail,
        String startDate,
        String endDate
) {
}
