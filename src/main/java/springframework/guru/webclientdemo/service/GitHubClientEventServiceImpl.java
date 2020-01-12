package springframework.guru.webclientdemo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import springframework.guru.webclientdemo.domain.Github;

@Service
public class GitHubClientEventServiceImpl implements GitHubClientEventService{

    private final WebClient webClient;
    private static final String OMDB_MIME_TYPE = "application/json";
    private static final String OMDB_API_BASE_URL = "https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc";
    private static final String USER_AGENT = "Spring 5 WebClient";

    public GitHubClientEventServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(OMDB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, OMDB_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }

    @Override
    public Mono<Github> getGithubRepo() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Github.class);
    }
}
