package springframework.guru.repoSearchEngine.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;


@Service
public class GithubServiceImpl implements GithubService{

    private static String GITHUB_BASE_URL;

    public GithubServiceImpl(@Value("${GITHUB_BASE_URL}") String GITHUB_BASE_URL) {
        this.GITHUB_BASE_URL = GITHUB_BASE_URL;
    }

    @Override
    public GithubRepoDto searchGithubRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        String request_url = GITHUB_BASE_URL +"?q="+q+"&sort=stars&order=desc";
        GithubRepoDto githubRepoDto = restTemplate.getForObject(request_url, GithubRepoDto.class);

        return githubRepoDto;
    }


}
