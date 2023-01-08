package dev.myrold.controller;

import dev.myrold.api.UserInfo;
import dev.myrold.security.RoleConstants;
import dev.myrold.util.UserUtil;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("/api/user")
@ExecuteOn(TaskExecutors.IO)
@Secured({SecurityRule.IS_AUTHENTICATED, RoleConstants.ADMIN})
public class UserController {

    @Get
    public UserInfo getUser(Authentication authentication) {
        return UserUtil.getUserInfo(authentication);
    }
}
