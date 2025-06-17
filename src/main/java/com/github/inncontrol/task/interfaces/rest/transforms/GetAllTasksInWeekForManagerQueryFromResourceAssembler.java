package com.github.inncontrol.task.interfaces.rest.transforms;

import com.github.inncontrol.task.domain.model.queries.GetAllTaskInWeekForManagerQuery;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskInWeekForManagerResource;

public class GetAllTasksInWeekForManagerQueryFromResourceAssembler {
    public static GetAllTaskInWeekForManagerQuery toQueryFromResource(GetAllTaskInWeekForManagerResource resource) {
        return new GetAllTaskInWeekForManagerQuery(
                resource.managerEmail()
        );
    }
}
