package dev.myrold.controller;

import com.github.f4b6a3.tsid.Tsid;

import dev.myrold.api.Schedule;
import dev.myrold.api.create.CreateSchedule;
import dev.myrold.service.ScheduleService;
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

@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller("/api/schedule")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Get("/{id}")
    public HttpResponse<Schedule> getSchedule(@PathVariable String id) {
        return scheduleService.findOne(Tsid.from(id))
            .map(HttpResponse::ok)
            .orElse(HttpResponse.noContent());
    }

    @Post
    @SuppressWarnings("rawtypes")
    public HttpResponse createSchedule(CreateSchedule createSchedule) {
        return scheduleService.createSchedule(createSchedule)
            .map(schedule -> (HttpResponse) HttpResponse.created(schedule))
            .orElse(HttpResponse.badRequest("Could not create schedule"));
    }

}
