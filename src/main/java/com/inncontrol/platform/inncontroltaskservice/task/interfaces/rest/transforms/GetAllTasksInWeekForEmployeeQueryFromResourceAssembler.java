package com.inncontrol.platform.inncontroltaskservice.task.interfaces.rest.transforms;

import com.inncontrol.platform.inncontroltaskservice.task.domain.model.queries.GetAllTaskInWeekForEmployeeQuery;
import com.inncontrol.platform.inncontroltaskservice.task.interfaces.rest.resources.GetAllTaskInWeekForEmployeeResource;

public class GetAllTasksInWeekForEmployeeQueryFromResourceAssembler {

    public static GetAllTaskInWeekForEmployeeQuery toQueryFromResource(GetAllTaskInWeekForEmployeeResource resource) {
        return new GetAllTaskInWeekForEmployeeQuery(
                resource.employeeEmail()
        );
    }
}
