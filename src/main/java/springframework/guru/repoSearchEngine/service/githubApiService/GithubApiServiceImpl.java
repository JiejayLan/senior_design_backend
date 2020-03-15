package springframework.guru.repoSearchEngine.service.githubApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.gitlubCommit.GithubCommit;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;
import java.util.ArrayList;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    private static String GITHUB_BASE_URL;
    private HttpEntity<String> httpEntity;

    public GithubApiServiceImpl(@Value("${GITHUB_BASE_URL}") String GITHUB_BASE_URL,
                                @Value("${GITHUB_ACCESS_TOKEN}") String GITHUB_ACCESS_TOKEN) {
        this.GITHUB_BASE_URL = GITHUB_BASE_URL;
        this.httpEntity = setUpHttpEntity(GITHUB_ACCESS_TOKEN);
    }

    @Override
    public HttpEntity<String> setUpHttpEntity(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);
        return httpEntity;
    }

    @Override
    public GithubSearchDto searchGithubRepo(String searchKey) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url = GITHUB_BASE_URL +"/search/repositories?q="+searchKey+"&sort=stars&order=desc&page=0";
            ResponseEntity<GithubSearchDto> response = restTemplate.exchange(request_url, HttpMethod.GET, httpEntity, GithubSearchDto.class);
            GithubSearchDto githubSearchDto = response.getBody();
            return githubSearchDto;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public GithubItem acquireSingleRepo(String path){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url = GITHUB_BASE_URL +"/repos/" + path;
            ResponseEntity<GithubItem> response = restTemplate.exchange(request_url, HttpMethod.GET, httpEntity, GithubItem.class);
            GithubItem githubSearchDto = response.getBody();
            return githubSearchDto;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<String> getRepoCommits(String path, int page){
        RestTemplate restTemplate = new RestTemplate();
        String request_url = GITHUB_BASE_URL + "/repos/" + path + "/commits?page=" + page;
        ResponseEntity<GithubCommit[]> response = restTemplate.exchange(request_url, HttpMethod.GET, httpEntity, GithubCommit[].class);
        GithubCommit[] commits = response.getBody();
        return extractDateString(commits);
    }

    @Override
    public ArrayList<String> extractDateString(GithubCommit[] githubCommits){
        ArrayList<String> dates = new ArrayList<>();
        for(GithubCommit commit : githubCommits)
            dates.add(commit.getCommit().getAuthor().getDate());
        return dates;
    }
}
