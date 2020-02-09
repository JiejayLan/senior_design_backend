package springframework.guru.repoSearchEngine.service;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.dto.RepoSearchDto;
import java.util.ArrayList;

@Service
public class RepoClientEventServiceImpl implements RepoClientEventService {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    private static final String GITLAB_BASE_URL = "https://gitlab.com/api/v4/search";

    public RepoClientEventServiceImpl() {
    } 

    @Override
    public GithubRepoDto getGithubRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        GithubRepoDto githubRepoDto = restTemplate.getForObject(GITHUB_BASE_URL +"?q="+q+"&sort=stars&order=desc", GithubRepoDto.class);

        return githubRepoDto;
    }

    @Override
    public GitlabRepoDto[] getGitLabRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", "y4iEVLmaqqU8Mge8fmUD");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<GitlabRepoDto[]> gitLabRepo = restTemplate.exchange(GITLAB_BASE_URL + "?scope=projects&search=" + q,
                HttpMethod.GET, entity,
                GitlabRepoDto[].class);
        GitlabRepoDto[] gitlab = gitLabRepo.getBody();
        return gitlab;
    }

    @Override
    public ArrayList<RepoSearchDto> getRepo(String q){
        ArrayList<RepoSearchDto> results = new ArrayList<>();;
        GithubRepoDto githubResult = getGithubRepo(q);
        ArrayList<GithubItem> githubItems = githubResult.getItems();
        for (int i = 0; i < Math.min(githubItems.size(),10); i++){
            GithubItem githubItem= githubItems.get(i);
            results.add( new RepoSearchDto(githubItem.getWatchers_count(), githubItem.getStar_count(),"",""));
        }

        GitlabRepoDto[] gitlabResults = getGitLabRepo(q);
        for (int i = 0; i < Math.min(gitlabResults.length,10); i++){
            GitlabRepoDto gitlabResult= gitlabResults[i];
            results.add(new RepoSearchDto(gitlabResult.getForks_count(), gitlabResult.getStar_count(),"",""));
        }
        return results;
    }
}
