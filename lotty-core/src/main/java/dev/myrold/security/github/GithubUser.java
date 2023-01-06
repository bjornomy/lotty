package dev.myrold.security.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUser {

    private String login;
    private String name;
    private String email;

}