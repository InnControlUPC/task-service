/* package com.inncontrol.platform.inncontroltaskservice.shared.application.internal.outboundedservices.acl;

import com.inncontrol.platform.inncontroltaskservice.employees.interfaces.acl.EmployeeContextFacade;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.valueobjects.EmployeeIdentifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 6/21/24 @ 08:03
 *
@Service
@AllArgsConstructor
public class ExternalEmployeeService {

    private final EmployeeContextFacade employeeContextFacade;
    private final ExternalProfileService externalProfileService;


    public Optional<EmployeeIdentifier> fetchEmployeeIdentifierByEmail(String email) {
        var profileID = externalProfileService.fetchProfileIdByEmail(email);
        if (profileID.isEmpty()) return Optional.empty();
        var employeeId = employeeContextFacade.fetchEmployeeIdByProfileId(profileID.get().profileId());
        if (employeeId == 0L) return Optional.empty();
        return Optional.of(new EmployeeIdentifier(employeeId));
    }
} */
