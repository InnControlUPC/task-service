package com.github.inncontrol.task.interfaces.rest.resources;

public record GetAllTaskFromDatesForManagerResource(String managerEmail,
                                                    String startDate,
                                                    String endDate) {
}
