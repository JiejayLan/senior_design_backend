package springframework.guru.repoSearchEngine.controller;
import org.springframework.http.HttpStatus;
import springframework.guru.repoSearchEngine.dto.RepoSearchItem;
import springframework.guru.repoSearchEngine.service.repoSearchService.RepoSearchService;
import springframework.guru.repoSearchEngine.service.repoDetailService.RepoDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class RepoController {
    private static final Logger logger = LoggerFactory.getLogger(RepoController.class);
    private RepoSearchService repoSearchService;
    private RepoDetailService repoDetailService;

    @Autowired
    public RepoController(RepoSearchService repoSearchService,
                          RepoDetailService repoDetailService){
        this.repoDetailService = repoDetailService;
        this.repoSearchService = repoSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<RepoSearchItem>> searchRepos(@RequestParam String searchKey) {
        ArrayList<RepoSearchItem> results = repoSearchService.searchRepo(searchKey);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public String getSingleRepo(@RequestParam String platform, @RequestParam String full_name) {
        return repoDetailService.acquireRepoDetail(platform, full_name);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
