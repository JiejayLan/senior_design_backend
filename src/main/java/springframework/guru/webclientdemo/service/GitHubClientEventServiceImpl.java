package springframework.guru.webclientdemo.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springframework.guru.webclientdemo.domain.Github;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubClientEventServiceImpl implements GitHubClientEventService{

    private static final String BASE_URL = "https://api.github.com/search/repositories\"";


    public GitHubClientEventServiceImpl() {

    }

    @Override
    public ResponseEntity<Github> getGithubRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        Github github = restTemplate.getForObject(BASE_URL +"?q="+q+"&sort=stars&order=desc", Github.class);

        return  new ResponseEntity<>(github, HttpStatus.OK);
    }
}
