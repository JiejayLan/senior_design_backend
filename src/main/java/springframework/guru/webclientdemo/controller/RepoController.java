package springframework.guru.webclientdemo.controller;

import springframework.guru.webclientdemo.domain.Github;
import springframework.guru.webclientdemo.service.GitHubClientEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class RepoController {
    private static final Logger logger = LoggerFactory.getLogger(RepoController.class);
    private GitHubClientEventService gitHubClientEventService;

    @Autowired
    public RepoController(GitHubClientEventService gitHubClientEventService){
        this.gitHubClientEventService=gitHubClientEventService;
    }

//
//    @GetMapping("/movies/title/{name}")
//    public Mono<Movie> getMovieByTitle(@PathVariable String name) {
//        String apiKey = env.getProperty("app.api.key");
//        return movieClientService.searchMovieByTitle(apiKey, name);
//    }
//
//    @GetMapping("/movies/id/{imdbId}")
//    public Mono<Movie> getMovieById(@PathVariable String imdbId) {
//        return movieClientService.searchMovieById(env.getProperty("app.api.key"), imdbId);
//    }

    @GetMapping("/github")
    public Mono<Github> getGithubRepo() {
        return gitHubClientEventService.getGithubRepo();
    }



//    @GetMapping(value = "/movies/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<MovieEvent> getEvents() {
//        return movieClientEventService.getMovieEvents();
//    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
