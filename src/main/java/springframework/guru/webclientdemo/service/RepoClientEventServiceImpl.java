package springframework.guru.webclientdemo.service;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import springframework.guru.webclientdemo.domain.GithubItem;
import springframework.guru.webclientdemo.domain.GithubSearchResponse;
import org.springframework.web.client.RestTemplate;
import springframework.guru.webclientdemo.domain.GitLabSearchResponse;
import springframework.guru.webclientdemo.domain.Repo;
import java.util.ArrayList;

@Service
public class RepoClientEventServiceImpl implements RepoClientEventService {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    private static final String GITLAB_BASE_URL = "https://gitlab.com/api/v4/search";

    public RepoClientEventServiceImpl() {
    }

    @Override
    public GithubSearchResponse getGithubRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        GithubSearchResponse githubSearchResponse = restTemplate.getForObject(GITHUB_BASE_URL +"?q="+q+"&sort=stars&order=desc", GithubSearchResponse.class);

        return githubSearchResponse;
    }

    @Override
    public GitLabSearchResponse[] getGitLabRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", "y4iEVLmaqqU8Mge8fmUD");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<GitLabSearchResponse[]> gitLabRepo = restTemplate.exchange(GITLAB_BASE_URL + "?scope=projects&search=" + q,
                HttpMethod.GET, entity,
                GitLabSearchResponse[].class);
        GitLabSearchResponse[] gitlab = gitLabRepo.getBody();
        return gitlab;
    }

    @Override
    public Repo[] getRepo(String q){
        Repo[] results = new Repo[20];
        GithubSearchResponse githubResult = getGithubRepo(q);
        ArrayList<GithubItem> githubItems = githubResult.getItems();
        for (int i = 0; i < 10; i++){
            GithubItem githubItem= githubItems.get(i);
            results[i] = new Repo(githubItem.getWatchers_count(), githubItem.getStar_count(),"","");
        }

        GitLabSearchResponse[] gitlabResults = getGitLabRepo(q);
        for (int i = 10; i < 20; i++){
            GitLabSearchResponse gitlabResult= gitlabResults[i];
            results[i] = new Repo(gitlabResult.getForks_count(), gitlabResult.getStar_count(),"","");
        }

        return results;
    }
}
