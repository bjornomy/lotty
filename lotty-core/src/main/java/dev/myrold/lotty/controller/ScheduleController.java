package dev.myrold.lotty.controller;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Set;

import dev.myrold.lotty.api.Schedule;
import dev.myrold.lotty.api.create.CreateSchedule;
import dev.myrold.lotty.security.RoleConstants;
import dev.myrold.lotty.service.ScheduleService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
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
@Controller("/api/schedules")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Get
    public Set<Schedule> mostUsed() {
        return scheduleService.findMostUsed();
    }

    @Get("/{id}")
    public HttpResponse<Schedule> getSchedule(@PathVariable String id) {
        return scheduleService.findOne(Tsid.from(id))
            .map(HttpResponse::ok)
            .orElse(HttpResponse.noContent());
    }

    @Post
    @SuppressWarnings("rawtypes")
    public HttpResponse createSchedule(CreateSchedule createSchedule) {
        log.info("Trying to create schedule");
        return scheduleService.createSchedule(createSchedule)
            .map(schedule -> (HttpResponse) HttpResponse.created(schedule))
            .orElse(HttpResponse.badRequest("Could not create schedule"));
    }

}
