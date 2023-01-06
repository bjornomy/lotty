package dev.myrold.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import dev.myrold.api.Lottery;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/api/lotteries")
public class LotteriesController {

    @Get
    public List<Lottery> getAllLotteries(@Nullable Principal principal) {
        return Collections.emptyList();
    }
}