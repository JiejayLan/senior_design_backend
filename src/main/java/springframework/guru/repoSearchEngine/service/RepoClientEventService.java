package springframework.guru.repoSearchEngine.service;

import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.dto.RepoSearchDto;
import java.util.ArrayList;

public interface RepoClientEventService {
    GithubRepoDto getGithubRepo(String q);
    GitlabRepoDto[] getGitLabRepo(String q);
    ArrayList<RepoSearchDto> getRepo(String q);
}

