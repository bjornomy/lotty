package dev.myrold.lotty.client.gitlab;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.Instant;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUser {
    private String login;
    private String name;
    private String email;
    private String avatarUrl;
    private String bio;
    private String blog;
    private String collaborators;
    private String company;
    private Instant createdAt;
    private long disk_usage;
    private String eventsUrl;
    private int followers;
    private String followersUrl;
    private int following;
    private String followingUrl;
    private String gistsUrl;
    private String gravatarId;
    private String hireable;
    private String htmlUrl;
    private long id;
    private String location;
    private String nodeId;
    private String organizationsUrl;
    private int ownedPrivateRepos;
    private GithubPlan plan;
    private int privateGists;
    private int publicGists;
    private int publicRepos;
    private String receivedEventsUrl;
    private String reposUrl;
    private boolean siteAdmin;
    private String starredUrl;
    private String subscriptionsUrl;
    private int totalPrivateRepos;
    private String twitterUsername;
    private boolean twoFactorAuthentication;
    private String type;
    private Instant updatedAt;
    private String url;
}