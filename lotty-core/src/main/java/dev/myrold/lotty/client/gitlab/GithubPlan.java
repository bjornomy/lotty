package dev.myrold.lotty.client.gitlab;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubPlan {
    private int collaborators;
    private String name;
    private int privateRepos;
    private long space;
}
