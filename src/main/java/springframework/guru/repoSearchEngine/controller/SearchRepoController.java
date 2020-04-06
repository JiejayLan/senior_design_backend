package springframework.guru.repoSearchEngine.controller;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import springframework.guru.repoSearchEngine.dto.response.RepoSearchItem;
import springframework.guru.repoSearchEngine.service.repoSearchService.RepoSearchService;
import springframework.guru.repoSearchEngine.service.repoDetailService.RepoDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@Validated
@RestController
@RequestMapping("/api/v1/search")
public class SearchRepoController {
    private static final Logger logger = LoggerFactory.getLogger(SearchRepoController.class);
    private RepoSearchService repoSearchService;
    private RepoDetailService repoDetailService;

    @Autowired
    public SearchRepoController(RepoSearchService repoSearchService,
                                RepoDetailService repoDetailService){
        this.repoDetailService = repoDetailService;
        this.repoSearchService = repoSearchService;
    }

    @GetMapping(value="")
    public ResponseEntity<ArrayList<RepoSearchItem>> searchRepos(
            @Valid @Pattern(regexp = "(.|\\s)*\\S(.|\\s)*",message = "Invalid SearchKey Param Value")
            @RequestParam String searchKey) {
        try{
            ArrayList<RepoSearchItem> results = repoSearchService.searchRepo(searchKey);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
