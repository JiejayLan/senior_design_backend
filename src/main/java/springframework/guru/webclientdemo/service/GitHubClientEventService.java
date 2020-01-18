package springframework.guru.webclientdemo.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import springframework.guru.webclientdemo.domain.Github;

public interface GitHubClientEventService {
    public ResponseEntity<Github> getGithubRepo(String q);
}

