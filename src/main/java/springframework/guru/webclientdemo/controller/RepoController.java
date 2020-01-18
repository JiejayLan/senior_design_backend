package springframework.guru.webclientdemo.controller;
import springframework.guru.webclientdemo.domain.GitLab;
import springframework.guru.webclientdemo.service.RepoClientEventService;
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
    private RepoClientEventService repoClientEventService;

    @Autowired
    public RepoController(RepoClientEventService repoClientEventService){
        this.repoClientEventService = repoClientEventService;
    }

    @GetMapping("/search")
    public ResponseEntity<GitLab[]> getGithubRepo(@RequestParam String q) {
        return repoClientEventService.getGitLabRepo(q);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
