package com.inncontrol.platform.inncontroltaskservice.task.domain.model.queries;

import java.util.Date;

public record GetAllTaskFromDatesForEmployeeQuery(
        String employeeEmail,
        Date startDate,
        Date endDate
) {
}
