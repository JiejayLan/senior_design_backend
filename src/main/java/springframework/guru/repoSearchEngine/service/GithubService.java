package springframework.guru.repoSearchEngine.service;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;

public interface GithubService {
    GithubRepoDto searchGithubRepo(String q);
}
