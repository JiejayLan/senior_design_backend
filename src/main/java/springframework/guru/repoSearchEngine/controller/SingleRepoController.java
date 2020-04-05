package springframework.guru.repoSearchEngine.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springframework.guru.repoSearchEngine.dto.models.RepoDetail;
import springframework.guru.repoSearchEngine.service.repoDetailService.RepoDetailService;
import springframework.guru.repoSearchEngine.service.repoSearchService.RepoSearchService;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("/api/v1/detail")
public class SingleRepoController {
    private static final Logger logger = LoggerFactory.getLogger(SearchRepoController.class);
    private RepoSearchService repoSearchService;
    private RepoDetailService repoDetailService;

    @Autowired
    public SingleRepoController(RepoSearchService repoSearchService,
                          RepoDetailService repoDetailService){
        this.repoDetailService = repoDetailService;
        this.repoSearchService = repoSearchService;
    }

    @GetMapping(value = "")
    public ResponseEntity<RepoDetail> getSingleRepo(
            @Valid @Pattern(regexp = "^(github|gitlab|bitbucket)$",message = "Invalid Platform Param Value")
            @RequestParam String platform,
            @Valid @Pattern(regexp = "(.|\\s)*\\S(.|\\s)*",message = "Invalid Full_name Param Value")
            @RequestParam String full_name) {
        try{
            RepoDetail repoDetail= repoDetailService.acquireRepoDetail(platform, full_name);
            return new ResponseEntity<>(repoDetail, HttpStatus.OK);
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
