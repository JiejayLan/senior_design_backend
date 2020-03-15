package springframework.guru.repoSearchEngine.service.githubApiService;
import org.springframework.http.HttpEntity;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.gitlubCommit.GithubCommit;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;

import java.util.ArrayList;

public interface GithubApiService {
    GithubSearchDto searchGithubRepo(String searchKey);
    GithubItem acquireSingleRepo(String path);
    ArrayList<String> getRepoCommits(String path, int page);
    ArrayList<String> extractDateString(GithubCommit[] githubCommits);
    HttpEntity<String> setUpHttpEntity(String token);
}
