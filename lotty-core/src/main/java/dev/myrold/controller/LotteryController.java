package dev.myrold.controller;

import com.github.f4b6a3.tsid.Tsid;
import com.sun.source.doctree.SeeTree;

import java.util.List;
import java.util.stream.Stream;

import dev.myrold.api.Lottery;
import dev.myrold.api.create.CreateLottery;
import dev.myrold.service.RoleService;
import dev.myrold.service.LotteryService;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.config.SecurityConfigurationProperties;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/api/lotteries")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class LotteryController {

    @Property(name = SecurityConfigurationProperties.PREFIX + ".security-enabled")
    private final boolean securityEnabled;

    private final LotteryService lotteryService;
    private final RoleService roleService;

    @Get
    public Stream<Lottery> getLast(Authentication authentication) {
        if (roleService.isAdmin(authentication)) {
            return lotteryService.findLast();
        } else {
            return lotteryService.findLastForParticipant(authentication);
        }

    }

    @Get("/{id}")
    public HttpResponse<?> getLottery(Authentication authentication, @PathVariable String id) {
        if (securityEnabled && !lotteryService.isOwnedBy(authentication, id)) {
            return HttpResponse.badRequest(String.format("You do not own lottery %s", id));
        }

        return lotteryService.findOne(Tsid.from(id))
            .map(HttpResponse::ok)
            .orElseGet(HttpResponse::noContent);
    }

    @Post
    public HttpResponse<Lottery> createLottery(Authentication authentication, CreateLottery createRequest) {
        return lotteryService.createLottery(authentication, createRequest)
            .map(HttpResponse::created)
            .orElse(HttpResponse.badRequest());
    }

    @Delete("/{id}")
    public HttpResponse<String> deleteLottery(Authentication authentication, @PathVariable String id) {

        if (securityEnabled && !lotteryService.isOwnedBy(authentication, id)) {
            return HttpResponse.badRequest(String.format("You do not own lottery %s", id));
        }

        lotteryService.deleteLottery(id);
        return HttpResponse.ok(String.format("Lottery %s was deleted", id));
    }
}