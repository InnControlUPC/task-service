package com.github.inncontrol.task.interfaces.rest.transforms;

import com.github.inncontrol.task.domain.model.queries.GetAllTaskFromDatesForManagerQuery;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskFromDatesForManagerResource;

import java.time.Instant;
import java.util.Date;

public class GetAllTaskFromDateForManagerQueryFromResourceAssembler {
    private static Date parseDate(String date) {
        return Date.from(Instant.parse(date));
    }

    public static GetAllTaskFromDatesForManagerQuery toQueryFromResource(GetAllTaskFromDatesForManagerResource resource) {
        return new GetAllTaskFromDatesForManagerQuery(
                resource.managerEmail(),
                parseDate(resource.startDate()),
                parseDate(resource.endDate())
        );
    }
}
