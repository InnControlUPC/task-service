package com.github.inncontrol.task.domain.model.queries;

import java.util.Date;

public record GetAllTaskFromDatesForManagerQuery(String managerEmail,
                                                 Date startDate,
                                                 Date endDate) {
}
