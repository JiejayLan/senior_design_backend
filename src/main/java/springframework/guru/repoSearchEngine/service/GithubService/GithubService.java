package springframework.guru.repoSearchEngine.service.GithubService;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;

public interface GithubService {
    GithubRepoDto searchGithubRepo(String q);
}
