package springframework.guru.repoSearchEngine.service.githubApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.gitlubCommit.GithubCommit;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;

import java.util.ArrayList;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    private static String GITHUB_BASE_URL;

    public GithubApiServiceImpl(@Value("${GITHUB_BASE_URL}") String GITHUB_BASE_URL) {
        this.GITHUB_BASE_URL = GITHUB_BASE_URL;
    }

    @Override
    public GithubSearchDto searchGithubRepo(String searchKey) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url = GITHUB_BASE_URL +"/search/repositories?q="+searchKey+"&sort=stars&order=desc&page=0";
            GithubSearchDto githubSearchDto = restTemplate.getForObject(request_url, GithubSearchDto.class);
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
            GithubItem githubSearchDto = restTemplate.getForObject(request_url, GithubItem.class);
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
        ResponseEntity<GithubCommit[]> responseEntity = restTemplate.getForEntity(request_url, GithubCommit[].class);
        GithubCommit[] commits = responseEntity.getBody();
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
