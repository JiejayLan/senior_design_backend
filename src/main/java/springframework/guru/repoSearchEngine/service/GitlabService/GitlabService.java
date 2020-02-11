package springframework.guru.repoSearchEngine.service.GitlabService;

import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

public interface GitlabService {
    GitlabRepoDto[] searchGitLabRepo(String q);
}
