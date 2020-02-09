package springframework.guru.repoSearchEngine.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;


@Service
public class GithubServiceImpl implements GithubService{
    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";

    public GithubServiceImpl() {
    }

    @Override
    public GithubRepoDto searchGithubRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        GithubRepoDto githubRepoDto = restTemplate.getForObject(GITHUB_BASE_URL +"?q="+q+"&sort=stars&order=desc", GithubRepoDto.class);

        return githubRepoDto;
    }


}
