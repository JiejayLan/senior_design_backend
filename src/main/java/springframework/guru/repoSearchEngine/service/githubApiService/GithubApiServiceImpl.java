package springframework.guru.repoSearchEngine.service.githubApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    private static String GITHUB_BASE_URL;

    public GithubApiServiceImpl(@Value("${GITHUB_BASE_URL}") String GITHUB_BASE_URL) {
        this.GITHUB_BASE_URL = GITHUB_BASE_URL;
    }

    @Override
    public GithubSearchDto searchGithubRepo(String q) {

        RestTemplate restTemplate = new RestTemplate();
        String request_url = GITHUB_BASE_URL +"/repositories?q="+q+"&sort=stars&order=desc&page=0";
        GithubSearchDto githubSearchDto = restTemplate.getForObject(request_url, GithubSearchDto.class);

        return githubSearchDto;
    }


}
