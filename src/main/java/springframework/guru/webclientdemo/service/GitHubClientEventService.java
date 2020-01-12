package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Mono;
import springframework.guru.webclientdemo.domain.Github;

public interface GitHubClientEventService {
    public Mono<Github> getGithubRepo();
}

