package springframework.guru.repoSearchEngine.service.gitlabApiService;

import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

public interface GitlabApiService {
    GitlabRepoDto[] searchGitLabRepo(String q);
}
