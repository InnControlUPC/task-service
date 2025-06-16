/*package com.inncontrol.platform.inncontroltaskservice.shared.application.internal.outboundedservices.acl;

import com.inncontrol.platform.inncontroltaskservice.iam.interfaces.acl.IamContextFacade;
import com.inncontrol.platform.inncontroltaskservice.profiles.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalUserService {

    private final IamContextFacade iamContextFacade;

    public ExternalUserService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<UserId> fetchUserIdByUsername(String username) {
        var userId = iamContextFacade.fetchUserIdByUsername(username);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

    public Optional<UserId> createUser(String username, String password) {
        var userId = iamContextFacade.createUser(username, password);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

}
*/