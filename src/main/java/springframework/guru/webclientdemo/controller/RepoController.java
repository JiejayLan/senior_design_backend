package springframework.guru.webclientdemo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import springframework.guru.webclientdemo.domain.Github;
import springframework.guru.webclientdemo.service.GitHubClientEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("/api/v1")
public class RepoController {
    private static final Logger logger = LoggerFactory.getLogger(RepoController.class);
    private GitHubClientEventService gitHubClientEventService;

    @Autowired
    public RepoController(GitHubClientEventService gitHubClientEventService){
        this.gitHubClientEventService=gitHubClientEventService;
    }

    @GetMapping("/github")
    public ResponseEntity<Github> getGithubRepo(@RequestParam String q) {
        return gitHubClientEventService.getGithubRepo(q);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
