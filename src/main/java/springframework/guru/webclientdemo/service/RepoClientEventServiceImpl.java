package springframework.guru.webclientdemo.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springframework.guru.webclientdemo.domain.Github;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springframework.guru.webclientdemo.domain.GitLab;
@Service
public class RepoClientEventServiceImpl implements RepoClientEventService {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";


    public RepoClientEventServiceImpl() {

    }

    @Override
    public ResponseEntity<Github> getGithubRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        Github github = restTemplate.getForObject(GITHUB_BASE_URL +"?q="+q+"&sort=stars&order=desc", Github.class);

        return  new ResponseEntity<>(github, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GitLab[]> getGitLabRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        GitLab[] gitLab = restTemplate.getForObject("https://gitlab.com/api/v4/search?scope=projects&search=flight", GitLab[].class);

        return  new ResponseEntity<>(gitLab, HttpStatus.OK);
    }
}
