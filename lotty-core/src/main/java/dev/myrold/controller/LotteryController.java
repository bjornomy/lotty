package dev.myrold.controller;

import com.github.f4b6a3.tsid.Tsid;

import java.security.Principal;
import java.util.List;

import dev.myrold.api.Lottery;
import dev.myrold.api.create.CreateLottery;
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
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/api/lotteries")
@Secured(SecurityRule.IS_ANONYMOUS)
public class LotteryController {

    @Property(name = "micronaut.security.enabled")
    private final boolean securityEnabled;

    private final LotteryService lotteryService;

    @Get
    public List<Lottery> getLast(Principal principal) {
        log.info("P: {}", principal);
        return lotteryService.findLast();
    }

    @Get("/{id}")
    public HttpResponse<?> getLottery(Principal principal, @PathVariable String id) {
        if (securityEnabled && !lotteryService.isOwnedBy(principal, id)) {
            return HttpResponse.badRequest(String.format("You do not own lottery %s", id));
        }

        return lotteryService.findOne(Tsid.from(id))
            .map(HttpResponse::ok)
            .orElseGet(HttpResponse::noContent);
    }

    @Post
    public HttpResponse<Lottery> createLottery(Principal principal, CreateLottery createRequest) {
        return lotteryService.createLottery(principal, createRequest)
            .map(HttpResponse::created)
            .orElse(HttpResponse.badRequest());
    }

    @Delete("/{id}")
    public HttpResponse<String> deleteLottery(Principal principal, @PathVariable String id) {

        if (securityEnabled && !lotteryService.isOwnedBy(principal, id)) {
            return HttpResponse.badRequest(String.format("You do not own lottery %s", id));
        }

        lotteryService.deleteLottery(id);
        return HttpResponse.ok(String.format("Lottery %s was deleted", id));
    }
}