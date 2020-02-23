package springframework.guru.repoSearchEngine.service.githubApiService;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;

public interface GithubApiService {
    GithubSearchDto searchGithubRepo(String searchKey);
}
