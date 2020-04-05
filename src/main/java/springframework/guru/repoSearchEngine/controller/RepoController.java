package springframework.guru.repoSearchEngine.controller;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebInputException;
import springframework.guru.repoSearchEngine.dto.models.RepoDetail;
import springframework.guru.repoSearchEngine.dto.models.RepoSearchItem;
import springframework.guru.repoSearchEngine.exception.ErrorMsg;
import springframework.guru.repoSearchEngine.exception.InternalException;
import springframework.guru.repoSearchEngine.service.repoSearchService.RepoSearchService;
import springframework.guru.repoSearchEngine.service.repoDetailService.RepoDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@Validated
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

    @GetMapping("/detail")
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

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorMsg> handleError(InternalException ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        ErrorMsg responseError = new ErrorMsg(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> handleError(Exception ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMsg responseError = new ErrorMsg(ex, status);
        if(ex instanceof ConstraintViolationException || ex instanceof ServerWebInputException){
            status = HttpStatus.BAD_REQUEST;
            responseError.setStatus(status);
        }
        return ResponseEntity.status(status).body(responseError);
    }
}
